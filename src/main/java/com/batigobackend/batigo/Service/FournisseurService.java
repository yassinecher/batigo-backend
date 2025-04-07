package com.batigobackend.batigo.Service;


import java.util.List;
import java.util.Optional;

import com.batigobackend.batigo.Entity.Fournisseur;
import com.batigobackend.batigo.Repository.FournisseurRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor

public class FournisseurService implements FournisseurServiceInterface {

    @Autowired
    private FournisseurRepository fournisseurRepository;

    @Override
    public Fournisseur addFournisseur(Fournisseur fournisseur) {
        return fournisseurRepository.save(fournisseur);
    }

    @Override
    public Fournisseur updateFournisseur(Fournisseur fournisseur) {
        return fournisseurRepository.save(fournisseur);
    }

    @Override
    public void deleteFournisseur(Long idF) {
        fournisseurRepository.deleteById(idF);
    }

    @Override
    public List<Fournisseur> getAllFournisseurs() {
        return fournisseurRepository.findAll();
    }

    @Override
    public Fournisseur getFournisseurById(Long id) {
        Optional<Fournisseur> optional = fournisseurRepository.findById(id);
        return optional.orElse(null);
    }

}



