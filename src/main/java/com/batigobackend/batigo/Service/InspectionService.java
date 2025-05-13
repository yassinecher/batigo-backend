package com.batigobackend.batigo.Service;

import com.batigobackend.batigo.Entity.Incidents;
import com.batigobackend.batigo.Entity.Inspections;
import com.batigobackend.batigo.Repository.IncidentsRepository;
import com.batigobackend.batigo.Repository.InspectionsRepository;
import com.batigobackend.batigo.event.NewInspectionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InspectionService implements Iserviceinsp {

    private final InspectionsRepository inspectionsRepository;
    private final IncidentsRepository incidentsRepository;
    private final ApplicationEventPublisher publisher;

    @Autowired
    public InspectionService(
            InspectionsRepository inspectionsRepository,
            IncidentsRepository incidentsRepository,
            ApplicationEventPublisher publisher
    ) {
        this.inspectionsRepository = inspectionsRepository;
        this.incidentsRepository = incidentsRepository;
        this.publisher = publisher;
    }

    @Override
    public List<Inspections> findAll() {
        return inspectionsRepository.findAll();
    }

    public List<Inspections> findAllByIncidentId(int incidentId) {
        return inspectionsRepository.findByIncidentId(incidentId);
    }

    @Override
    public Inspections findById(int id) {
        return inspectionsRepository.findById(id).orElse(null);
    }

    @Override
    public Inspections add(Inspections inspection, int incidentId) {
        Incidents incident = incidentsRepository.findById(incidentId)
                .orElseThrow(() -> new RuntimeException("Incident avec l'ID " + incidentId + " non trouvé"));

        inspection.setIncidents(incident);
        Inspections savedInspection = inspectionsRepository.save(inspection);

        // ✅ Publier l'événement
        publisher.publishEvent(new NewInspectionEvent(this, savedInspection));

        return savedInspection;
    }

    @Override
    public void delete(int id) {
        inspectionsRepository.deleteById(id);
    }

    @Override
    public Inspections edit(int id, Inspections updatedInspection) {
        Inspections existingInspection = inspectionsRepository.findById(id).orElse(null);

        if (existingInspection != null) {
            existingInspection.setResponsable(updatedInspection.getResponsable());
            existingInspection.setObjet(updatedInspection.getObjet());
            existingInspection.setDateInspection(updatedInspection.getDateInspection());
            existingInspection.setResultat(updatedInspection.getResultat());
            existingInspection.setIncidents(updatedInspection.getIncidents());

            return inspectionsRepository.save(existingInspection);
        } else {
            return null;
        }
    }
}