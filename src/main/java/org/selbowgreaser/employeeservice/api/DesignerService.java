package org.selbowgreaser.employeeservice.api;

import org.selbowgreaser.employeeservice.model.entity.position.Designer;
import org.selbowgreaser.employeeservice.model.request.DesignerDto;
import org.selbowgreaser.employeeservice.model.request.EmployeeRequestDto;

import java.util.List;
import java.util.Map;

public interface DesignerService {

    void createDesigner(EmployeeRequestDto<?> employeeRequestDto, Map<String, String> errors);

    List<Designer> findAll();

    Designer findDesignerById(Long id);

    void updateDesigner(Long id, DesignerDto updatedDesignerDto, Map<String, String> errors);

    void deleteDesignerById(Long id);

    boolean isExist(Long id);
}
