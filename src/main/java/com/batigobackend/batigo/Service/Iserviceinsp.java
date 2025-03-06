package com.batigobackend.batigo.Service;

import com.batigobackend.batigo.Entity.Inspections;

import java.util.List;

public interface Iserviceinsp {

    List<Inspections> findAll();

    Inspections findById(int id);

    Inspections add(Inspections inspection, int incidentId);

    void delete(int id);

    Inspections edit(int id,Inspections Inspections);
}
