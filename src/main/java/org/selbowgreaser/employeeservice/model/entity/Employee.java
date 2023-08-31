package org.selbowgreaser.employeeservice.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.selbowgreaser.employeeservice.model.entity.position.*;
import org.selbowgreaser.employeeservice.model.type.Education;
import org.selbowgreaser.employeeservice.model.type.EmploymentStatus;
import org.selbowgreaser.employeeservice.model.type.Gender;
import org.selbowgreaser.employeeservice.model.type.Position;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    private Position position;

    @Column(name = "department")
    private String department;

    @Column(name = "email")
    private String email;

    @Column(name = "salary")
    private BigDecimal salary;

    @Column(name = "birth_date")
    @JsonProperty("birth_date")
    private LocalDate birthDate;

    @Column(name = "education")
    @Enumerated(EnumType.STRING)
    private Education education;

    @Column(name = "employment_status")
    @Enumerated(EnumType.STRING)
    @JsonProperty("employment_status")
    private EmploymentStatus employmentStatus;

    @OneToOne(mappedBy = "employee", fetch = FetchType.LAZY)
    private Manager manager;

    @OneToOne(mappedBy = "employee", fetch = FetchType.LAZY)
    private Developer developer;

    @OneToOne(mappedBy = "employee", fetch = FetchType.LAZY)
    private Engineer engineer;

    @OneToOne(mappedBy = "employee", fetch = FetchType.LAZY)
    private Analyst analyst;

    @OneToOne(mappedBy = "employee", fetch = FetchType.LAZY)
    private Designer designer;

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private List<Task> tasks;
}
