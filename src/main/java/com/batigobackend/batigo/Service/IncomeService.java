package com.batigobackend.batigo.Service;



import com.batigobackend.batigo.Entity.Expense;
import com.batigobackend.batigo.Entity.Income;
import com.batigobackend.batigo.Entity.Projet;
import com.batigobackend.batigo.Model.IncomeRequest;
import com.batigobackend.batigo.Repository.IncomeRepository;
import com.batigobackend.batigo.Repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
 public class IncomeService implements Iservice {
    private final IncomeRepository incomeRepository;
    private final ProjetRepository projetRepository;


    @Autowired
    public IncomeService(IncomeRepository incomeRepository, ProjetRepository projetRepository) {
        this.incomeRepository = incomeRepository;
        this.projetRepository = projetRepository;
    }


    @Override
    public Income add(IncomeRequest e) {
        Income income = new Income();
        income.setAmount(e.getAmount());
        income.setDate(e.getDate());
        income.setSource(e.getSource());
        Projet projet = projetRepository.getById(e.getProjetId());
        income.setProjet(projet);
        System.out.println(income.getProjet().getId());
        return incomeRepository.save(income);
    }

    @Override
    public void delete(int id) {
        incomeRepository.deleteById(id);

    }
    @Override
    public  Income edit(Income income) {
      Income income1 = incomeRepository.save(income);
        return income1;
    }

    @Override
    public List<Income> findAll() {
        return incomeRepository.findAll();
    }

    @Override
    public Income findById(int id) {
        return incomeRepository.findById(id).get();
    }



}
