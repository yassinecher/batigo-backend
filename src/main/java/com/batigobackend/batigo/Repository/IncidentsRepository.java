package com.batigobackend.batigo.Repository;

import com.batigobackend.batigo.Entity.Incidents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentsRepository extends JpaRepository<Incidents, Integer> {
}
