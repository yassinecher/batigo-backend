package com.batigobackend.batigo.Service;

import com.batigobackend.batigo.Entity.Incidents;
import com.batigobackend.batigo.Repository.IncidentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncidentService implements Iserviceinc {
        private final IncidentsRepository incidentsRepository;

        @Autowired
        public IncidentService(IncidentsRepository incidentsRepository) {
            this.incidentsRepository = incidentsRepository;
        }

        @Override
        public List<Incidents> findAll() {
            return this.incidentsRepository.findAll();
        }

        @Override
        public Incidents findById(int id) {
            return this.incidentsRepository.findById(id).orElse(null);
        }

        @Override
        public Incidents add(Incidents incident) {
            return this.incidentsRepository.save(incident);
        }

        @Override
        public void delete(int id) {
            this.incidentsRepository.deleteById(id);
        }

        @Override
        public Incidents edit(int id, Incidents incident) {
            return incidentsRepository.findById(id).map(existingIncident -> {
                existingIncident.setDescription(incident.getDescription());
                existingIncident.setGravite(incident.getGravite());
                existingIncident.setEtat(incident.getEtat());
                existingIncident.setResponsable(incident.getResponsable());
                return incidentsRepository.save(existingIncident);
            }).orElseThrow(() -> new RuntimeException("Incident non trouvé avec ID : " + id));
        }
    }
