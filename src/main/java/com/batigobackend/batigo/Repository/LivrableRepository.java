package com.batigobackend.batigo.Repository;

import com.batigobackend.batigo.Entity.Livrable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LivrableRepository extends JpaRepository<Livrable, Long> {
    List<Livrable> findByProjetId(Long projetId);


}
