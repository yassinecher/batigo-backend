package com.batigobackend.batigo.Service;
import org.springframework.http.HttpHeaders;

import com.batigobackend.batigo.ProjetFinanciereDTO.ProjetFinanciereDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Map;

@Service
public class PredictionService {

    private final RestTemplate restTemplate = new RestTemplate();

    public double predictBudget(ProjetFinanciereDTO projet) {
        String url = "http://localhost:8000/predict-budget";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> payload = Map.of(
                "type_projet", projet.getTypeProjet(),
                "budget_estime", projet.getBudgetEstime(),
                "duree_estimee", projet.getDureeEstimee(),
                "incident_qualite", projet.getIncidentQualite(),
                "incident_securite", projet.getIncidentSecurite(),
                "materiaux_defectueux", projet.getMateriauxDefectueux(),
                "conditions_meteo", projet.getConditionsMeteo()
        );

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

        return (Double) response.getBody().get("budget_reel_prevu");
    }
}

