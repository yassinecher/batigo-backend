package com.batigobackend.batigo.Repository;

 import com.batigobackend.batigo.Entity.Expense;
  import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 import java.util.List;

@Repository
public interface ExpenseRepository  extends JpaRepository<Expense, Integer> {
 List<Expense> findByProjetId(Long projetId);


}
