package org.selbowgreaser.employeeservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "manager")
public class Manager {

    @Id
    @Column(name = "employee_id")
    private long employeeId;

    @Column(name = "department")
    private String department;

    @Column(name = "responsibility")
    private String responsibility;
}
