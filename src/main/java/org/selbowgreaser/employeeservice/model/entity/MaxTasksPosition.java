package org.selbowgreaser.employeeservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.selbowgreaser.employeeservice.model.type.Position;

@Data
@Entity
@Table(name = "max_tasks_position")
public class MaxTasksPosition {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    private Position position;

    @Column(name = "max_tasks")
    private Integer maxTasks;
}
