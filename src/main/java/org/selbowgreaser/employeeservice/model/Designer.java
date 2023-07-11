package org.selbowgreaser.employeeservice.model;

import jakarta.persistence.*;
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
