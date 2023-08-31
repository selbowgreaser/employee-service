package org.selbowgreaser.employeeservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.selbowgreaser.employeeservice.model.entity.Employee;
import org.selbowgreaser.employeeservice.model.entity.position.Developer;
import org.selbowgreaser.employeeservice.model.request.DeveloperDto;
import org.selbowgreaser.employeeservice.model.request.EmployeeRequestDto;
import org.selbowgreaser.employeeservice.repository.DeveloperRepository;
import org.selbowgreaser.employeeservice.api.DeveloperService;
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
public class DeveloperServiceImpl implements DeveloperService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final DeveloperRepository developerRepository;
    private final EmployeeValidator employeeValidator;

    @Override
    public void createDeveloper(EmployeeRequestDto<?> employeeRequestDto, Map<String, String> errors) {
        log.info("Creating new developer");

        Employee employee = modelMapper.map(employeeRequestDto, Employee.class);

        Developer developer = modelMapper.map(employeeRequestDto.getPositionInformation(), Developer.class);

        employeeValidator.validate(employee, errors);

        if (errors.isEmpty()) {
            developer.setEmployee(employee);
            developerRepository.save(developer);
            log.info("Developer created successfully");
        }

    }

    @Override
    public List<Developer> findAll() {
        log.info("Fetching all developers");
        return developerRepository.findAll();
    }

    @Override
    public Developer findDeveloperById(Long id) {
        log.info("Fetching developer by id: {}", id);

        return developerRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void updateDeveloper(Long id, DeveloperDto updatedDeveloperDto, Map<String, String> errors) {
        log.info("Updating developer with id: {}", id);

        Developer previousDeveloper = findDeveloperById(id);

        Developer updatedDeveloper = modelMapper.map(updatedDeveloperDto, Developer.class);

        updatedDeveloper.setId(id);
        updatedDeveloper.setEmployee(previousDeveloper.getEmployee());

        developerRepository.save(updatedDeveloper);

        log.info("Developer updated successfully");
    }

    @Override
    public void deleteDeveloperById(Long id) {
        log.info("Deleting developer with id: {}", id);

        if (!isExist(id)) {
            throw new IllegalArgumentException();
        }

        developerRepository.deleteById(id);

        log.info("Developer deleted successfully");
    }

    @Override
    public boolean isExist(Long id) {
        return developerRepository.existsById(id);
    }
}
