package com.batigobackend.batigo.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data  // This includes @Getter and @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(indexes = {
        @Index(name = "idx_task_status", columnList = "status"),

})
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5, max = 100)
    private String title;

    @NotNull
    @Size(min = 10, max = 500)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @PastOrPresent
    private LocalDateTime dateInit;

    @PastOrPresent
    private LocalDateTime dateModif;
    @Column(name = "date_start")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateStart;

    @Column(name = "date_end")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateEnd;


    @PrePersist
    protected void onCreate() {
        this.dateInit = LocalDateTime.now();
        this.dateModif = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.dateModif = LocalDateTime.now();
    }
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "workflow_id")
    @JsonIgnore
    private Workflow workflow;
    // Pour l'optimisation IA
 
    @ElementCollection
    @CollectionTable(name = "task_dependencies", joinColumns = @JoinColumn(name = "task_id"))
    private List<Long> dependencies = new ArrayList<>(); // IDs des tâches prérequises
    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String lastModifiedBy;

}
