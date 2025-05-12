package com.batigobackend.batigo.Controller;

import com.batigobackend.batigo.PerformanceDTO.PerformanceDTO;
import com.batigobackend.batigo.Service.PerformanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/finance")
public class FinancialController {

    private final PerformanceService performanceService;

    public FinancialController(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    @GetMapping("/performance")
    public ResponseEntity<List<PerformanceDTO>> getAllProjetPerformances() {

        List<PerformanceDTO> performanceDTOs = performanceService.getPerformanceDataForAllProjets();
        return ResponseEntity.ok(performanceDTOs);
    }
}
