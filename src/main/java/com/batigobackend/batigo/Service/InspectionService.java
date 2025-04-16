package com.batigobackend.batigo.Service;

import com.batigobackend.batigo.Entity.Incidents;
import com.batigobackend.batigo.Entity.Inspections;
import com.batigobackend.batigo.Repository.IncidentsRepository;
import com.batigobackend.batigo.Repository.InspectionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InspectionService implements Iserviceinsp{



    private InspectionsRepository inspectionsRepository;
    private IncidentsRepository incidentsRepository;

    @Autowired
    public InspectionService(InspectionsRepository inspectionsRepository, IncidentsRepository incidentsRepository) {
        this.inspectionsRepository = inspectionsRepository;
        this.incidentsRepository = incidentsRepository;
    }
    @Override
    public List<Inspections> findAll() {
        return this.inspectionsRepository.findAll();
    }

        public List<Inspections> findAllByIncidentId(int incidentId) {
            return inspectionsRepository.findByIncidentId(incidentId);
        }

    @Override
    public Inspections findById(int id) {
        return this.inspectionsRepository.findById(id).orElse(null);
    }

    @Override
    public Inspections add(Inspections inspection, int incidentId) {
        // Vérifier si l'incident existe
        Incidents incident = this.incidentsRepository.findById(incidentId)
                .orElseThrow(() -> new RuntimeException("Incident avec l'ID " + incidentId + " non trouvé"));

        // Associer l'incident à l'inspection
        inspection.setIncidents(incident);

        return inspectionsRepository.save(inspection);
    }
    @Override
    public void delete(int id) {
            this.inspectionsRepository.deleteById(id);
    }

    @Override
    public Inspections edit(int id, Inspections updatedInspection) {
        Inspections existingInspection = this.inspectionsRepository.findById(id).orElse(null);

        if (existingInspection != null) {
            existingInspection.setResponsable(updatedInspection.getResponsable());
            existingInspection.setObjet(updatedInspection.getObjet());
            existingInspection.setDateInspection(updatedInspection.getDateInspection());
            existingInspection.setResultat(updatedInspection.getResultat());

            // ✅ Conserve le lien avec l’incident pour éviter perte ou suppression
            existingInspection.setIncidents(updatedInspection.getIncidents());

            return this.inspectionsRepository.save(existingInspection);
        } else {
            return null;
        }
    }

}
