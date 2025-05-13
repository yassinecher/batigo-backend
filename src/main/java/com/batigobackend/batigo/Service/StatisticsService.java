package com.batigobackend.batigo.Service;

import com.batigobackend.batigo.Entity.Expense;
import com.batigobackend.batigo.Entity.Income;
import com.batigobackend.batigo.Repository.ExpenseRepository;
import com.batigobackend.batigo.Repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private ExpenseRepository expenseRepository;


    public List<Income> getIncomesByProject(Long projetId) {
        return incomeRepository.findByProjetId(projetId);
    }


    public List<Expense> getExpensesByProject(Long projetId) {
        return expenseRepository.findByProjetId(projetId);
    }
    public BigDecimal calculateTotalIncomes(Long projetId) {
        List<Income> incomes = getIncomesByProject(projetId);
        return incomes.stream()
                .map(income -> BigDecimal.valueOf(income.getAmount().doubleValue()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calculateTotalExpenses(Long projetId) {
        List<Expense> expenses = getExpensesByProject(projetId);
        return expenses.stream()
                .map(expense -> BigDecimal.valueOf(expense.getAmount().doubleValue()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }




    @Transactional(readOnly = true)
    public Map<String, Object> getProjectStatistics(Long projetId) {
        BigDecimal totalIncomes = calculateTotalIncomes(projetId);
        BigDecimal totalExpenses = calculateTotalExpenses(projetId);
        BigDecimal balance = totalIncomes.subtract(totalExpenses);

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalIncomes", totalIncomes);
        stats.put("totalExpenses", totalExpenses);
        stats.put("balance", balance);

        return stats;
    }
}
