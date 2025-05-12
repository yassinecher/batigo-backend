package com.batigobackend.batigo.Repository;

import com.batigobackend.batigo.Entity.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetRepository extends JpaRepository<Projet, Integer> {
    List<Projet> findAll();
}
