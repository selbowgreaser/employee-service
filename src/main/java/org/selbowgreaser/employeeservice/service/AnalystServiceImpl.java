package org.selbowgreaser.employeeservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.selbowgreaser.employeeservice.api.AnalystService;
import org.selbowgreaser.employeeservice.model.entity.Employee;
import org.selbowgreaser.employeeservice.model.entity.position.Analyst;
import org.selbowgreaser.employeeservice.model.request.AnalystDto;
import org.selbowgreaser.employeeservice.model.request.EmployeeRequestDto;
import org.selbowgreaser.employeeservice.repository.AnalystRepository;
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
public class AnalystServiceImpl implements AnalystService {

    private final ModelMapper modelMapper;
    private final AnalystRepository analystRepository;
    private final EmployeeValidator employeeValidator;

    @Override
    public void createAnalyst(EmployeeRequestDto<?> employeeRequestDto, Map<String, String> errors) {

        log.info("Creating new analyst");

        Employee employee = modelMapper.map(employeeRequestDto, Employee.class);

        Analyst analyst = modelMapper.map(employeeRequestDto.getPositionInformation(), Analyst.class);

        employeeValidator.validate(employee, errors);

        if (errors.isEmpty()) {
            analyst.setEmployee(employee);
            analystRepository.save(analyst);
            log.info("Analyst created successfully");
        }

    }

    @Override
    public List<Analyst> findAll() {
        log.info("Fetching all analysts");
        return analystRepository.findAll();
    }

    @Override
    public Analyst findAnalystById(Long id) {
        log.info("Fetching analyst by id: {}", id);
        return analystRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void updateAnalyst(Long id, AnalystDto updateAnalystDto, Map<String, String> errors) {

        log.info("Updating analyst with id: {}", id);

        Analyst previousAnalyst = findAnalystById(id);

        Analyst updatedAnalyst = modelMapper.map(updateAnalystDto, Analyst.class);

        updatedAnalyst.setId(id);
        updatedAnalyst.setEmployee(previousAnalyst.getEmployee());

        analystRepository.save(updatedAnalyst);

        log.info("Analyst updated successfully");
    }

    @Override
    public void deleteAnalystById(Long id) {
        log.info("Deleting analyst with id: {}", id);

        if (!isExist(id)) {
            throw new IllegalArgumentException();
        }

        log.info("Analyst deleted successfully");

        analystRepository.deleteById(id);
    }

    @Override
    public boolean isExist(Long id) {
        return analystRepository.existsById(id);
    }
}
