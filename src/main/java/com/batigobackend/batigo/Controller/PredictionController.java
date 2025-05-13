package com.batigobackend.batigo.Controller;

import com.batigobackend.batigo.ProjetFinanciereDTO.ProjetFinanciereDTO;
import com.batigobackend.batigo.Service.PredictionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prediction")
public class PredictionController {

    private final PredictionService predictionService;

    public PredictionController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @PostMapping("/budget")
    public ResponseEntity<Double> predictBudget(@RequestBody ProjetFinanciereDTO projet) {
        double prediction = predictionService.predictBudget(projet);
        return ResponseEntity.ok(prediction);
    }
}
