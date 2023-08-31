package org.selbowgreaser.employeeservice.api;

import org.selbowgreaser.employeeservice.model.entity.position.Engineer;
import org.selbowgreaser.employeeservice.model.request.EmployeeRequestDto;
import org.selbowgreaser.employeeservice.model.request.EngineerDto;

import java.util.List;
import java.util.Map;

public interface EngineerService {

    void createEngineer(EmployeeRequestDto<?> employeeRequestDto, Map<String, String> errors);

    List<Engineer> findAll();

    Engineer findEngineerById(Long id);

    void updateEngineer(Long id, EngineerDto updatedEngineerDto, Map<String, String> errors);

    void deleteEngineerById(Long id);

    boolean isExist(Long id);
}