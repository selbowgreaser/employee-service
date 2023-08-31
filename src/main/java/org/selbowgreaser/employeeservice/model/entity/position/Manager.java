package org.selbowgreaser.employeeservice.model.entity.position;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.selbowgreaser.employeeservice.model.entity.Employee;

@Data
@Entity
@Table(name = "manager")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @JsonIgnore
    private Employee employee;

    @Column(name = "department")
    private String department;

    @Column(name = "responsibility")
    private String responsibility;
}
