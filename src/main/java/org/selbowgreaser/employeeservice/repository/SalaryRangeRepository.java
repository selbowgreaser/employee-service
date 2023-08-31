package org.selbowgreaser.employeeservice.repository;

import org.selbowgreaser.employeeservice.model.entity.SalaryRange;
import org.selbowgreaser.employeeservice.model.type.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalaryRangeRepository extends JpaRepository<SalaryRange, Long> {

    Optional<SalaryRange> findByPosition(Position position);
}
