package com.batigobackend.batigo.Interfaces;

import com.batigobackend.batigo.Entity.Livrable;

import java.util.List;
import java.util.Optional;

public interface LivrableInterface {
    Livrable creerLivrable(Livrable livrable);
    List<Livrable> listerLivrables();
    Optional<Livrable> obtenirLivrableParId(Long id);
    // Livrable mettreAJourLivrable(Long id, Livrable livrable);
    void supprimerLivrable(Long id);
}
