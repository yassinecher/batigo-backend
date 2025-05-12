package com.batigobackend.batigo.Service;


 import com.batigobackend.batigo.Entity.Expense;
 import com.batigobackend.batigo.Entity.Projet;
 import com.batigobackend.batigo.Model.ExpenseRequest;
 import com.batigobackend.batigo.Repository.ExpenseRepository;
 import com.batigobackend.batigo.Repository.ProjetRepository;
 import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ExpenseService implements Eservice {
    private final ExpenseRepository expenseRepository;
    private final ProjetRepository projetRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository, ProjetRepository projetRepository) {
        this.expenseRepository = expenseRepository;
        this.projetRepository = projetRepository;
    }
    @Override
    public List<Expense> findAll() {
        return expenseRepository.findAll();

    }

    @Override
    public Expense findById(int id) {
        return expenseRepository.findById(id).get();
    }

    @Override
    public Expense add(ExpenseRequest e) {
        Expense expense = new Expense();
        expense.setAmount(e.getAmount());
        expense.setDate(e.getDate());
        expense.setSource(e.getSource());
        Projet projet = projetRepository.getById(e.getProjetId());
        expense.setProjet(projet);
        System.out.println(expense.getProjet().getId());
        return expenseRepository.save(expense);
    }

    @Override
    public void delete(int id) {
        expenseRepository.deleteById(id);

    }

    @Override
    public Expense edit(Expense expense) {
        return expenseRepository.save(expense);
    }
}
