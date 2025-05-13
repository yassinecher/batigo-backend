package com.batigobackend.batigo.Service;



import com.batigobackend.batigo.Entity.Expense;
import com.batigobackend.batigo.Model.ExpenseRequest;

import java.util.List;

public interface Eservice {

    List<Expense> findAll();

    Expense findById(int id);

    Expense add(ExpenseRequest expense);

    void delete(int id);

    Expense edit(Expense expense);

}
