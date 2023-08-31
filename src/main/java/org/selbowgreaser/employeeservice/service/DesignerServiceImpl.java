package org.selbowgreaser.employeeservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.selbowgreaser.employeeservice.model.entity.Employee;
import org.selbowgreaser.employeeservice.model.entity.position.Designer;
import org.selbowgreaser.employeeservice.model.request.DesignerDto;
import org.selbowgreaser.employeeservice.model.request.EmployeeRequestDto;
import org.selbowgreaser.employeeservice.repository.DesignerRepository;
import org.selbowgreaser.employeeservice.api.DesignerService;
import org.selbowgreaser.employeeservice.repository.EmployeeRepository;
import org.selbowgreaser.employeeservice.util.validator.EmployeeValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DesignerServiceImpl implements DesignerService {

    private final ModelMapper modelMapper;
    private final DesignerRepository designerRepository;
    private final EmployeeValidator employeeValidator;

    @Override
    public void createDesigner(EmployeeRequestDto<?> employeeRequestDto, Map<String, String> errors) {
        log.info("Creating new designer");

        Employee employee = modelMapper.map(employeeRequestDto, Employee.class);

        Designer designer = modelMapper.map(employeeRequestDto.getPositionInformation(), Designer.class);

        employeeValidator.validate(employee, errors);

        if (errors.isEmpty()) {
            designer.setEmployee(employee);
            designerRepository.save(designer);
            log.info("Designer created successfully");
        }

    }

    @Override
    public List<Designer> findAll() {
        log.info("Fetching all designers");
        return designerRepository.findAll();
    }

    @Override
    public Designer findDesignerById(Long id) {
        log.info("Fetching designer by id: {}", id);
        return designerRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void updateDesigner(Long id, DesignerDto updatedDesignerDto, Map<String, String> errors) {
        log.info("Updating designer with id: {}", id);

        Designer previousDesigner = findDesignerById(id);

        Designer updatedDesigner = modelMapper.map(updatedDesignerDto, Designer.class);

        updatedDesigner.setId(id);
        updatedDesigner.setEmployee(previousDesigner.getEmployee());

        designerRepository.save(updatedDesigner);

        log.info("Designer updated successfully");
    }

    @Override
    public void deleteDesignerById(Long id) {
        log.info("Deleting designer with id: {}", id);

        if (!isExist(id)) {
            throw new IllegalArgumentException();
        }

        designerRepository.deleteById(id);

        log.info("Designer deleted successfully");
    }

    @Override
    public boolean isExist(Long id) {
        return designerRepository.existsById(id);
    }
}
