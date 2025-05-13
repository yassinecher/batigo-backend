package com.batigobackend.batigo.Interfaces;

import com.batigobackend.batigo.Entity.Projet;

import java.util.List;
import java.util.Optional;

public interface ProjetInterface {
    Projet creerProjet(Projet projet);
    List<Projet> listerProjets();
    Optional<Projet> obtenirProjetParId(Long id);
    Projet mettreAJourProjet(Long id, Projet projet);
    void supprimerProjet(Long id);
}
