package com.batigobackend.batigo.Service;

import com.batigobackend.batigo.Entity.Workflow;
import com.batigobackend.batigo.Repository.WorkflowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkflowService {
    @Autowired
    private WorkflowRepository workflowRepository;


    private String aiModelEndpoint;

    public List<Workflow> getAllWorkflows() {
        return workflowRepository.findAll();
    }

    public Workflow createWorkflow(Workflow workflow) {
        return workflowRepository.save(workflow);
    }

    public void deleteWorkflow(Long id) {
        workflowRepository.deleteById(id);
    }
}
