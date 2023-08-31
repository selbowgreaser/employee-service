package org.selbowgreaser.employeeservice.repository;

import org.selbowgreaser.employeeservice.model.entity.position.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
}
