package org.selbowgreaser.employeeservice.repository;

import org.selbowgreaser.employeeservice.model.Engineer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineerRepository extends JpaRepository<Engineer, Long> {

}
