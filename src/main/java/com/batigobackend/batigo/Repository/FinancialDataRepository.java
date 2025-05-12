package com.batigobackend.batigo.Repository;



import com.batigobackend.batigo.Model.FinancialData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FinancialDataRepository extends JpaRepository<FinancialData, Long> {
    @Query("SELECT fd FROM FinancialData fd JOIN FETCH fd.projet")
    List<FinancialData> findAllWithProjet();
}

