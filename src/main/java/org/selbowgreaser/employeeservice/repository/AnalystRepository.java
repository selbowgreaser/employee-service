package org.selbowgreaser.employeeservice.repository;

import org.selbowgreaser.employeeservice.model.Analyst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalystRepository extends JpaRepository<Analyst, Long> {
}
