package org.selbowgreaser.employeeservice.repository;

import org.selbowgreaser.employeeservice.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Modifying
    @Query(value = "DELETE FROM employee WHERE id = (SELECT id FROM employee ORDER BY RANDOM() LIMIT 1)", nativeQuery = true)
    void deleteRandomEmployee();
}
