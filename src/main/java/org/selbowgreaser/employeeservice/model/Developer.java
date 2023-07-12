package org.selbowgreaser.employeeservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "developer")
public class Developer {

    @Id
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "framework")
    private String framework;

    @Column(name = "database_skill")
    private String databaseSkill;

    @Column(name = "version_control")
    private String versionControl;
}
