package com.batigobackend.batigo.Entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Data  // This includes @Getter and @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @FutureOrPresent
    private LocalDateTime dateMax;
    @PrePersist
    protected void onCreate() {
        this.dateInit = LocalDateTime.now();
        this.dateModif = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.dateModif = LocalDateTime.now();
    }
    @ManyToOne
    @JoinColumn(name = "workflow_id")
    private Workflow workflow;
}
