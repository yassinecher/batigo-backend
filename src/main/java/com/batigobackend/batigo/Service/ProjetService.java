package com.batigobackend.batigo.Service;

import com.batigobackend.batigo.Entity.Livrable;
import com.batigobackend.batigo.Entity.Projet;
import com.batigobackend.batigo.Model.ProjetDTO;
import com.batigobackend.batigo.Repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjetService implements IProjectService {

    private final ProjetRepository projetRepository;

    public ProjetService(ProjetRepository projetRepository) {
        this.projetRepository = projetRepository;
    }


    public List<Projet> findAll() {
        return  projetRepository.findAll();
    }

    @Override
    public Projet findById(int id) {
        return  projetRepository.findById(id).get();
    }

    @Override
    public Projet add(Projet projet) {
        return  projetRepository.save(projet);
    }

    @Override
    public void delete(int id) {
         projetRepository.deleteById(id);

    }

    @Override
    public Projet edit(Projet projet) {
        return projetRepository.save(projet);    }

    public List<ProjetDTO> getAllProjects(String etat, String projetType, Boolean archived) {
        List<Projet> projects = projetRepository.findAll();
        // Apply filters
        if (etat != null) {
            projects = projects.stream()
                    .filter(p -> p.getEtat().toString().equalsIgnoreCase(etat))
                    .collect(Collectors.toList());
        }
        if (projetType != null) {
            projects = projects.stream()
                    .filter(p -> p.getProjetType().toString().equalsIgnoreCase(projetType))
                    .collect(Collectors.toList());
        }
        if (archived != null) {
            projects = projects.stream()
                    .filter(p -> p.isArchived() == archived)
                    .collect(Collectors.toList());
        }

        return projects.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ProjetDTO getProjectById(Integer id) {
        Projet projet = projetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        return toDTO(projet);
    }

    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private IncomeService incomeService;
    private ProjetDTO toDTO(Projet projet) {
        ProjetDTO dto = new ProjetDTO();
        dto.setId(projet.getId());
        dto.setNom(projet.getNom());
        dto.setDescription(projet.getDescription());
        dto.setDateDebut(projet.getDateDebut());
        dto.setDateFinPrevue(projet.getDateFinPrevue());
        dto.setDateFinReelle(projet.getDateFinReelle());
        dto.setBudget(projet.getBudget());
        dto.setLastUpdated(projet.getLastUpdated());
        dto.setScheduleStatus(projet.getScheduleStatus());
        dto.setEtat(projet.getEtat());
        dto.setResponsable(projet.getResponsable());
        dto.setArchived(projet.isArchived());
        dto.setApproved(projet.isApproved());
        dto.setProgress(projet.getProgress());
        dto.setExpectedProgress(projet.getExpectedProgress());
        dto.setTerminate(projet.isTerminate());
        dto.setProjetType(projet.getProjetType());
        dto.setAdresse(projet.getAdresse());
        // Map livrables and carts (simplified)
        dto.setLivrables(projet.getLivrables().stream()
                .map(l -> new Livrable() {{ setId(l.getId()); setNom(l.getNom()); }})
                .collect(Collectors.toList()));

        // Calculate totals
        dto.setTotalExpenses(expenseService.getTotalExpensesForProject(projet.getId()));
        dto.setTotalIncomes(incomeService.getTotalIncomesForProject(projet.getId()));
        return dto;
    }
}

