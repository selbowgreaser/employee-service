package org.selbowgreaser.employeeservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "engineer")
public class Engineer {

    @Id
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "programming_language")
    private String programmingLanguage;

    @Column(name = "tool")
    private String tool;

    @Column(name = "certification")
    private String certification;
}
