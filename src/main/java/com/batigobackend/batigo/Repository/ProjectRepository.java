package com.batigobackend.batigo.Repository;

import com.batigobackend.batigo.Entity.Cart;
import com.batigobackend.batigo.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByProjectName(String projectName);
}
