package com.batigobackend.batigo.Service;

import com.batigobackend.batigo.Entity.Incidents;

import java.util.List;

public interface Iserviceinc {

    List<Incidents> findAll();

    Incidents findById(int id);

    Incidents add(Incidents Incidents);

    void delete(int id);

    Incidents edit(int id,Incidents Incidents);
}
