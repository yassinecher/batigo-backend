package com.batigobackend.batigo.Repository;

  import com.batigobackend.batigo.Entity.Income;
  import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

  import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Integer> {
  List<Income> findByProjetId(Long projetId);


}
