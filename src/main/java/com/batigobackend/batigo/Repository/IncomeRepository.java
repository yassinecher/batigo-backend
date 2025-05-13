package com.batigobackend.batigo.Repository;

import com.batigobackend.batigo.Entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Integer> {
  List<Income> findByProjetId(Long projetId);
  @Query("SELECT COALESCE(SUM(i.amount), 0) FROM Income i WHERE i.projet.id = :projetId")
  BigDecimal sumAmountByProjetId(@Param("projetId") Long projetId);

}
