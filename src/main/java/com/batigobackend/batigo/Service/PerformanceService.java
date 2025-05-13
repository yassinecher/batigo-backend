package com.batigobackend.batigo.Service;

import com.batigobackend.batigo.Model.FinancialData;
import com.batigobackend.batigo.PerformanceDTO.PerformanceDTO;
import com.batigobackend.batigo.Repository.FinancialDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PerformanceService {

    private static final Logger logger = LoggerFactory.getLogger(PerformanceService.class);
    private final FinancialDataRepository financialDataRepository;

    public PerformanceService(FinancialDataRepository financialDataRepository) {
        this.financialDataRepository = financialDataRepository;
    }

    public List<PerformanceDTO> getPerformanceDataForAllProjets() {
        // Modification clé ici - utilisation de la méthode avec jointure
        List<FinancialData> data = financialDataRepository.findAllWithProjet();


        if(data.isEmpty()) {
            logger.warn("⚠️ Aucune donnée financière trouvée dans la base !");
            return Collections.emptyList();
        }

        logger.info("📊 Données financières récupérées : {} entrées", data.size());


        Map<String, List<FinancialData>> groupedByProjet = data.stream()
                .filter(fd -> fd.getProjet() != null) // Sécurité supplémentaire
                .collect(Collectors.groupingBy(fd -> fd.getProjet().getNom()));

        List<PerformanceDTO> results = new ArrayList<>();

        for (Map.Entry<String, List<FinancialData>> entry : groupedByProjet.entrySet()) {
            PerformanceDTO dto = new PerformanceDTO();
            dto.setNomDuProjet(entry.getKey());

            List<Double> incomes = entry.getValue().stream()
                    .map(FinancialData::getIncome)
                    .collect(Collectors.toList());

            List<Double> expenses = entry.getValue().stream()
                    .map(FinancialData::getExpense)
                    .collect(Collectors.toList());

            dto.setIncomes(incomes);
            dto.setExpenses(expenses);

            double totalIncome = incomes.stream().mapToDouble(Double::doubleValue).sum();
            double totalExpense = expenses.stream().mapToDouble(Double::doubleValue).sum();
            dto.setBeneficeNet(totalIncome - totalExpense);

            double score = totalIncome == 0 ? 0 : (totalIncome - totalExpense) / totalIncome;
            dto.setScoreDePerformance(score);

            results.add(dto);
        }

        logger.debug("🎯 Résultats générés : {} projets", results.size());
        return results;
    }
}