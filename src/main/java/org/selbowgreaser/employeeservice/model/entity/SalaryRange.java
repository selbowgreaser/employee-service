package org.selbowgreaser.employeeservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.selbowgreaser.employeeservice.model.type.Position;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "salary_range")
public class SalaryRange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    private Position position;

    @Column(name = "min_salary")
    private BigDecimal minSalary;

    @Column(name = "max_salary")
    private BigDecimal maxSalary;
}
