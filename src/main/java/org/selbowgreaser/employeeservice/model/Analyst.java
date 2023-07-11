package org.selbowgreaser.employeeservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "analyst")
public class Analyst {

    @Id
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "notation")
    private String notation;

    @Column(name = "business_domain")
    private String businessDomain;

    @Column(name = "requirements_gathering_technique")
    private String requirementGatheringTechnique;
}
