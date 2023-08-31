package org.selbowgreaser.employeeservice.repository;

import org.selbowgreaser.employeeservice.model.entity.position.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
