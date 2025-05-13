package com.batigobackend.batigo.Controller;

import com.batigobackend.batigo.Entity.Income;
import com.batigobackend.batigo.Model.IncomeRequest;
import com.batigobackend.batigo.Repository.IncomeRepository;
import com.batigobackend.batigo.Service.IncomeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Income")
@RestController
@RequestMapping("/income")
@CrossOrigin(origins = "http://localhost:4200")
public class IncomeController {

    private final IncomeService incomeService;
    private final IncomeRepository incomeRepository;

    public IncomeController(IncomeService incomeService, IncomeRepository incomeRepository) {
        this.incomeService = incomeService;
        this.incomeRepository = incomeRepository;
    }

    @PostMapping("/add")
    public Income add(@RequestBody IncomeRequest e) {
        return incomeService.add(e);
    }



    @GetMapping
    public List<Income> index() {
        return incomeService.findAll();
    }



    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/edit/{id}")
    public ResponseEntity<Income> edit(@PathVariable int id, @RequestBody Income income) {
        Optional<Income> existingIncome = incomeRepository .findById(id);

        if (existingIncome.isPresent()) {
            Income updated = incomeService.edit(income);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        incomeService.delete(id);

    }


    @GetMapping("/{id}")
    public Income show(@PathVariable int id) {
        return incomeService.findById(id);
    }














}
