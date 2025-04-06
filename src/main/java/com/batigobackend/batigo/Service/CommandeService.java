package com.batigobackend.batigo.Service;

import com.batigobackend.batigo.Entity.Commande;
import com.batigobackend.batigo.Entity.User;
import com.batigobackend.batigo.Repository.CommandeRepository;
import com.batigobackend.batigo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class CommandeService implements ICommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public List<Commande> retrieveAllCommandes() {
        return commandeRepository.findAll();
    }

    @Override
    public Commande retrieveCommande(Long commandeId) {
        return commandeRepository.findById(commandeId)
                .orElseThrow(() -> new RuntimeException("Commande not found with ID: " + commandeId));
    }

    @Override
    public Commande addCommande(Commande commande) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + username));

        commande.setUser(user);
        Commande savedCommande = commandeRepository.save(commande);

        // Send email after saving
        String to = "wajdizekri100@gmail.com";
        String subject = "Nouvelle Commande Ajoutée";
        String body = "Une nouvelle commande a été ajoutée par " + username + ".\n"
                + "Détails de la commande :\n"
                + "ID: " + savedCommande.getIdcommande() + "\n"
                + "Details: " + savedCommande.getDetails() + "\n"
                + "Utilisateur: " + user.getEmail() + "\n"
                + "Merci de vérifier le système.";

        emailService.sendEmail(to, subject, body);

        return savedCommande;
    }

    @Override
    public Commande modifyCommande(Commande commande) {
        Commande existingCommande = commandeRepository.findById(commande.getIdcommande())
                .orElseThrow(() -> new RuntimeException("Commande not found with ID: " + commande.getIdcommande()));

        if (commande.getUser() != null) {
            User user = userRepository.findById(commande.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + commande.getUser().getId()));
            existingCommande.setUser(user);
        }

        existingCommande.setDetails(commande.getDetails());
        existingCommande.setOrderdate(commande.getOrderdate());
        existingCommande.setStatus(commande.getStatus());

        return commandeRepository.save(existingCommande);
    }

    @Override
    public void removeCommande(Long commandeId) {
        if (!commandeRepository.existsById(commandeId)) {
            throw new RuntimeException("Commande not found with ID: " + commandeId);
        }
        commandeRepository.deleteById(commandeId);
    }

    @Override
    public byte[] generateCommandePdf(Long commandeId) {
        Commande commande = retrieveCommande(commandeId);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // Add title
        document.add(new Paragraph("Commande Details")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(20));

        // Add commande details
        document.add(new Paragraph("ID: " + commande.getIdcommande()));
        document.add(new Paragraph("Details: " + commande.getDetails()));
        document.add(new Paragraph("Order Date: " + commande.getOrderdate()));
        document.add(new Paragraph("Status: " + commande.getStatus().toString())); // Convert Status to String
        document.add(new Paragraph("User: " + commande.getUser().getEmail()));

        document.close();

        return outputStream.toByteArray();
    }

    @Override
    public byte[] generateAllCommandesPdf() {
        List<Commande> commandes = retrieveAllCommandes();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // Add title
        document.add(new Paragraph("All Commandes")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(20));

        // Create a table with 5 columns
        Table table = new Table(5);
        table.addHeaderCell("ID");
        table.addHeaderCell("Details");
        table.addHeaderCell("Order Date");
        table.addHeaderCell("Status");
        table.addHeaderCell("User");

        // Add rows to the table
        for (Commande commande : commandes) {
            table.addCell(commande.getIdcommande().toString());
            table.addCell(commande.getDetails());
            table.addCell(commande.getOrderdate().toString());
            table.addCell(commande.getStatus().toString()); // Convert Status to String
            table.addCell(commande.getUser().getEmail());
        }

        document.add(table);
        document.close();

        return outputStream.toByteArray();
    }

    @Override
    public Integer getCommandeCount() {
        return Math.toIntExact(commandeRepository.count());
    }
}