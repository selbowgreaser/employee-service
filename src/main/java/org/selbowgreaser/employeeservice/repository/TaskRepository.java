package org.selbowgreaser.employeeservice.repository;

import org.selbowgreaser.employeeservice.model.entity.Employee;
import org.selbowgreaser.employeeservice.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findByEmployee(Employee employee);
}
