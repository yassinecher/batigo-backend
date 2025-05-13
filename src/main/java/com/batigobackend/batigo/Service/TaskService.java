package com.batigobackend.batigo.Service;

import com.batigobackend.batigo.Entity.Task;
import com.batigobackend.batigo.Entity.Workflow;
import com.batigobackend.batigo.Repository.TaskRepository;
import com.batigobackend.batigo.Repository.WorkflowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository ;
    @Autowired
    private WorkflowRepository workflowRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task createTask(Task taske) {


        return taskRepository.findById(Long.getLong("0")).map(task -> {
            task.setTitle(taske.getTitle());
            task.setDescription(taske.getDescription());
            task.setStatus(taske.getStatus());
            task.setDateEnd(taske.getDateEnd());
            task.setDateStart(taske.getDateStart());
            return taskRepository.save(task);
        }).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task updateTask(Long id, Task taskDetails) {
        return taskRepository.findById(id).map(task -> {
            task.setTitle(taskDetails.getTitle());
            task.setDescription(taskDetails.getDescription());
            task.setStatus(taskDetails.getStatus());
            task.setDateEnd(taskDetails.getDateEnd());
            task.setDateStart(taskDetails.getDateStart());
            return taskRepository.save(task);
        }).orElseThrow(() -> new RuntimeException("Task not found"));
    }


    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
    public List<Task> getTasksByWorkflow(Long workflowId) {
        Workflow workflow = workflowRepository.findById(workflowId)
                .orElseThrow(() -> new RuntimeException("Workflow not found"));
        return taskRepository.findByWorkflowId(workflowId);
    }

    public Task addTaskToWorkflow(Long workflowId, Task task) {
        Workflow workflow = workflowRepository.findById(workflowId)
                .orElseThrow(() -> new RuntimeException("Workflow not found"));
        task.setWorkflow(workflow);
        return taskRepository.save(task);
    }
}