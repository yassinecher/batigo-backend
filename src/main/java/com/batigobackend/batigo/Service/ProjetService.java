package com.batigobackend.batigo.Service;

import com.batigobackend.batigo.Entity.Projet;
import com.batigobackend.batigo.Repository.ProjetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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


}
