package com.batigobackend.batigo.Service;

import com.batigobackend.batigo.Entity.Incidents;
import com.batigobackend.batigo.Repository.IncidentsRepository;
import com.batigobackend.batigo.event.NewIncidentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidentService implements Iserviceinc {

    private final IncidentsRepository incidentsRepository;
    private final ApplicationEventPublisher publisher;

    @Autowired
    public IncidentService(IncidentsRepository incidentsRepository, ApplicationEventPublisher publisher) {
        this.incidentsRepository = incidentsRepository;
        this.publisher = publisher;
    }

    @Override
    public List<Incidents> findAll() {
        return incidentsRepository.findAll();
    }

    @Override
    public Incidents findById(int id) {
        return incidentsRepository.findById(id).orElse(null);
    }

    @Override
    public Incidents add(Incidents incident) {
        Incidents savedIncident = incidentsRepository.save(incident);

        // ✅ Publier l'événement
        publisher.publishEvent(new NewIncidentEvent(this, savedIncident));

        return savedIncident;
    }

    @Override
    public void delete(int id) {
        incidentsRepository.deleteById(id);
    }

    @Override
    public Incidents edit(int id, Incidents incident) {
        return incidentsRepository.findById(id).map(existingIncident -> {
            existingIncident.setDescription(incident.getDescription());
            existingIncident.setGravite(incident.getGravite());
            existingIncident.setEtat(incident.getEtat());
            return incidentsRepository.save(existingIncident);
        }).orElseThrow(() -> new RuntimeException("Incident non trouvé avec ID : " + id));
    }
}
