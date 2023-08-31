package org.selbowgreaser.employeeservice.model.entity.position;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.selbowgreaser.employeeservice.model.entity.Employee;

@Data
@Entity
@Table(name = "analyst")
public class Analyst {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    @JsonIgnore
    private Employee employee;

    @Column(name = "notation")
    private String notation;

    @Column(name = "business_domain")
    private String businessDomain;

    @Column(name = "requirements_gathering_technique")
    private String requirementGatheringTechnique;
}
