package com.batigobackend.batigo.Controller;

import com.batigobackend.batigo.Entity.Expense;
import com.batigobackend.batigo.Model.ExpenseRequest;
import com.batigobackend.batigo.Repository.ExpenseRepository;
import com.batigobackend.batigo.Service.ExpenseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Expense")
@RestController
@RequestMapping("/expense")
@CrossOrigin(origins = "http://localhost:4200")
public class ExpenseController {

    private final ExpenseService expenseService;
    private final ExpenseRepository expenseRepository;

    public ExpenseController(ExpenseService expenseService, ExpenseRepository expenseRepository) {
        this.expenseService = expenseService;
        this.expenseRepository = expenseRepository;
    }

    @PostMapping("/add")
    public Expense add(@RequestBody ExpenseRequest e) {

        return expenseService.add(e);
    }



    @GetMapping
    public List<Expense> index() {
        return expenseService.findAll();
    }



    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/edit/{id}")
    public ResponseEntity<Expense> edit(@PathVariable int id, @RequestBody Expense expense) {
        Optional<Expense> existingExpense = expenseRepository .findById(id);

        if (existingExpense.isPresent()) {
            Expense updated = expenseService.edit(expense);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        expenseService.delete(id);

    }


    @GetMapping("/{id}")
    public Expense show(@PathVariable int id) {
        return expenseService.findById(id);
    }














}
