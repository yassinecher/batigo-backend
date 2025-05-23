package com.batigobackend.batigo.Repository;

import com.batigobackend.batigo.Entity.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetRepository extends JpaRepository<Projet, Integer> {
    List<Projet> findAll();
    List<Projet> findByArchivedFalse(); // Returns only active projects
    List<Projet> findByArchivedTrue();  // Returns only archived projects
    List<Projet> findByArchivedFalseAndApprovedTrue(); // ✅ Show only approved projects
    List<Projet> findByApprovedFalse(); // ✅ Show pending projects (for admin)



    List<Projet> findByNomContainingIgnoreCase(String nom);

    Projet findByNom(String nom);
}
