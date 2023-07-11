package org.selbowgreaser.employeeservice.repository;

import org.selbowgreaser.employeeservice.model.Designer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignerRepository extends JpaRepository<Designer, Long> {
}
