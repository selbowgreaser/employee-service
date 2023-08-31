package org.selbowgreaser.employeeservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.selbowgreaser.employeeservice.api.EngineerService;
import org.selbowgreaser.employeeservice.model.entity.Employee;
import org.selbowgreaser.employeeservice.model.entity.position.Engineer;
import org.selbowgreaser.employeeservice.model.request.EmployeeRequestDto;
import org.selbowgreaser.employeeservice.model.request.EngineerDto;
import org.selbowgreaser.employeeservice.repository.EmployeeRepository;
import org.selbowgreaser.employeeservice.repository.EngineerRepository;
import org.selbowgreaser.employeeservice.util.validator.EmployeeValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EngineerServiceImpl implements EngineerService {

    private final ModelMapper modelMapper;
    private final EngineerRepository engineerRepository;
    private final EmployeeValidator employeeValidator;

    @Override
    public void createEngineer(EmployeeRequestDto<?> employeeRequestDto, Map<String, String> errors) {
        log.info("Creating new engineer");

        Employee employee = modelMapper.map(employeeRequestDto, Employee.class);

        Engineer engineer = modelMapper.map(employeeRequestDto.getPositionInformation(), Engineer.class);

        employeeValidator.validate(employee, errors);

        if (errors.isEmpty()) {
            engineer.setEmployee(employee);
            engineerRepository.save(engineer);
            log.info("Engineer created successfully");
        }

    }

    @Override
    public List<Engineer> findAll() {
        log.info("Fetching all engineers");

        return engineerRepository.findAll();
    }

    @Override
    public Engineer findEngineerById(Long id) {
        log.info("Fetching engineer by id: {}", id);

        return engineerRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void updateEngineer(Long id, EngineerDto updatedEngineerDto, Map<String, String> errors) {
        log.info("Updating engineer with id: {}", id);

        Engineer previousEngineer = findEngineerById(id);

        Engineer updatedEngineer = modelMapper.map(updatedEngineerDto, Engineer.class);

        updatedEngineer.setId(id);
        updatedEngineer.setEmployee(previousEngineer.getEmployee());

        engineerRepository.save(updatedEngineer);
        log.info("Engineer updated successfully");
    }

    @Override
    public void deleteEngineerById(Long id) {
        log.info("Deleting engineer with id: {}", id);

        if (!isExist(id)) {
            throw new IllegalArgumentException();
        }

        engineerRepository.deleteById(id);
        log.info("Engineer deleted successfully");
    }

    @Override
    public boolean isExist(Long id) {
        return engineerRepository.existsById(id);
    }
}
