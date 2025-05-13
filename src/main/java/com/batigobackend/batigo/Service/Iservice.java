package com.batigobackend.batigo.Service;



import com.batigobackend.batigo.Entity.Income;
import com.batigobackend.batigo.Model.IncomeRequest;

import java.util.List;

public interface Iservice {

    List<Income> findAll();

    Income findById(int id);



    Income add(IncomeRequest i);

    void delete(int id);

    Income edit(Income income);


}
