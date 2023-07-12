package org.selbowgreaser.employeeservice.service;

import lombok.RequiredArgsConstructor;
import org.selbowgreaser.employeeservice.model.Employee;
import org.selbowgreaser.employeeservice.repository.EmployeeRepository;
import org.selbowgreaser.employeeservice.service.api.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void updateEmployee(Employee updatedEmployee) {
        employeeRepository.save(updatedEmployee);
    }

    @Override
    public void deleteEmployeeById(Long id) {

        if (!isExist(id)) {
            throw new IllegalArgumentException();
        }

        employeeRepository.deleteById(id);
    }

    @Override
    public boolean isExist(Long id) {
        return employeeRepository.existsById(id);
    }
}
