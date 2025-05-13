package com.batigobackend.batigo.Service;




import com.batigobackend.batigo.Entity.Projet;

import java.util.List;
public interface IProjectService {

    List<Projet> findAll();

    Projet findById(int id);

    Projet add(Projet projet);

    void delete(int id);

    Projet edit(Projet projet);

}
