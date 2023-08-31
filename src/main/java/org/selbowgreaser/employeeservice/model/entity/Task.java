package org.selbowgreaser.employeeservice.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRawValue;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(name = "description")
    private String description;

    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @ManyToOne
    private Employee employee;

}
