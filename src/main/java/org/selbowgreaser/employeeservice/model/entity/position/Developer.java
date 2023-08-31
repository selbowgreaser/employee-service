package org.selbowgreaser.employeeservice.model.entity.position;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.selbowgreaser.employeeservice.model.entity.Employee;

@Data
@Entity
@Table(name = "developer")
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @JsonIgnore
    private Employee employee;

    @Column(name = "framework")
    private String framework;

    @Column(name = "database_skill")
    private String databaseSkill;

    @Column(name = "version_control")
    private String versionControl;
}
