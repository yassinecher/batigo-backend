package com.batigobackend.batigo.Repository;

import com.batigobackend.batigo.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ExpenseRepository  extends JpaRepository<Expense, Integer> {
 List<Expense> findByProjetId(Long projetId);
 @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e WHERE e.projet.id = :projetId")
 BigDecimal   sumAmountByProjetId(@Param("projetId") Long projetId);

}
