package com.batigobackend.batigo.Service;


import com.batigobackend.batigo.Entity.Fournisseur;

import java.util.List;
import java.util.Optional;

public interface FournisseurServiceInterface {
    Fournisseur addFournisseur(Fournisseur fournisseur);
    Fournisseur updateFournisseur(Fournisseur fournisseur);
    void deleteFournisseur(Long idF);
    List<Fournisseur> getAllFournisseurs(); // au cas où tu veuilles lister aussi
    Fournisseur getFournisseurById(Long id);
}
