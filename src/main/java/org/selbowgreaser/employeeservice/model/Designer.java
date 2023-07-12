package org.selbowgreaser.employeeservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "designer")
public class Designer {

    @Id
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "design_tool")
    private String designTool;

    @Column(name = "ux_skill")
    private String usSkill;
}
