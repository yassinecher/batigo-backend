package com.batigobackend.batigo.Repository;

 import com.batigobackend.batigo.Entity.Fournisseur;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {
}
