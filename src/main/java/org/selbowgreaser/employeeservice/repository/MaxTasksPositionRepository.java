package org.selbowgreaser.employeeservice.repository;

import org.selbowgreaser.employeeservice.model.entity.MaxTasksPosition;
import org.selbowgreaser.employeeservice.model.type.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaxTasksPositionRepository extends JpaRepository<MaxTasksPosition, Long> {

    MaxTasksPosition findByPosition(Position position);
}
