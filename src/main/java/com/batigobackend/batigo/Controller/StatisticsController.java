package com.batigobackend.batigo.Controller;

import com.batigobackend.batigo.Entity.Expense;
import com.batigobackend.batigo.Entity.Income;
import com.batigobackend.batigo.Service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    // Endpoint pour récupérer les statistiques d'un projet
    @GetMapping("/projet/{projetId}")
    public ResponseEntity<Map<String, Object>> getProjectStatistics(@PathVariable Long projetId) {
        Map<String, Object> stats = statisticsService.getProjectStatistics(projetId);
        return ResponseEntity.ok(stats);
    }

    // Endpoint pour récupérer les revenus d'un projet
    @GetMapping("/income/{projetId}")
    public ResponseEntity<List<Income>> getIncomesByProjet(@PathVariable Long projetId) {
        List<Income> incomes = statisticsService.getIncomesByProject(projetId);
        return ResponseEntity.ok(incomes);
    }

    // Endpoint pour récupérer les dépenses d'un projet
    @GetMapping("/expenses/{projectId}")
    public ResponseEntity<List<Expense>> getExpensesByProjet(@PathVariable Long projetId) {
        List<Expense> expenses = statisticsService.getExpensesByProject(projetId);
        return ResponseEntity.ok(expenses);
    }
}

