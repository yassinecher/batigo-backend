package com.batigobackend.batigo.Repository;

import com.batigobackend.batigo.Entity.Task;
import com.batigobackend.batigo.Entity.Workflow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByWorkflow(Workflow workflow);
}