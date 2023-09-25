package org.selbowgreaser.employeeservice.model.entity.position;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.selbowgreaser.employeeservice.model.entity.Employee;

@Data
@Entity
@Table(name = "engineer") //todo ошибка в названии
public class Engineer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @JsonIgnore
    private Employee employee;

    @Column(name = "programming_language")
    private String programmingLanguage;

    @Column(name = "tool")
    private String tool;

    @Column(name = "certification")
    private String certification;
}
