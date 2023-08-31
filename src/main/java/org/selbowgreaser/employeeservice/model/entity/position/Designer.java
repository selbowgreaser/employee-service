package org.selbowgreaser.employeeservice.model.entity.position;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.selbowgreaser.employeeservice.model.entity.Employee;

@Data
@Entity
@Table(name = "designer")
public class Designer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @JsonIgnore
    private Employee employee;

    @Column(name = "design_tool")
    private String designTool;

    @Column(name = "ux_skill")
    private String usSkill;
}
