package com.batigobackend.batigo.Service;

import com.batigobackend.batigo.Entity.Cart;
import com.batigobackend.batigo.Entity.Projet;
import com.batigobackend.batigo.Model.CartDTO;
import com.batigobackend.batigo.Repository.CartRepository;
import com.batigobackend.batigo.Repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CartService implements ICartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProjetRepository projectRepository;

    @Override
    public List<Cart> addCart(List<Cart> cart, String projectName) {
        Projet project = projectRepository.findByNom(projectName);
        if (project == null) {
            project = new Projet();
            project.setNom(projectName); // Set the project name
            projectRepository.save(project); // Save the new project
        }
        Projet finalProject = project;
        cart.forEach(cartItem -> cartItem.setProject(finalProject));
        return cartRepository.saveAll(cart);
    }

    @Override
    public List<CartDTO> getAllCarts() {
        List<Cart> carts = cartRepository.findAll();

        // Group carts by project
        Map<String, List<Cart>> groupedByProject = carts.stream()
                .collect(Collectors.groupingBy(cart -> cart.getProject().getNom()));

        // Create CartDTO for each group and combine product names
        List<CartDTO> result = new ArrayList<>();

        for (Map.Entry<String, List<Cart>> entry : groupedByProject.entrySet()) {
            String projectName = entry.getKey();
            List<String> itemNames = entry.getValue().stream()
                    .map(Cart::getName)  // Assuming Cart has a method getProductName()
                    .collect(Collectors.toList());
            String combinedNames = String.join(", ", itemNames);
            result.add(new CartDTO(projectName, combinedNames));  // Use CartDTO for structured response
        }

        return result;    }
}
