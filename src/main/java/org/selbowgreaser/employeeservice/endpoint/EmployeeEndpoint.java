package org.selbowgreaser.employeeservice.endpoint;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.selbowgreaser.employeeservice.builder.ErrorResultBuilder;
import org.selbowgreaser.employeeservice.builder.SuccessResultBuilder;
import org.selbowgreaser.employeeservice.model.Employee;
import org.selbowgreaser.employeeservice.service.api.EmployeeService;
import org.selbowgreaser.employeeservice.util.EmployeeValidator;
import org.selbowgreaser.employeeservice.util.XMLGregorianCalendarUtil;
import org.selbowgreaser.soap.api.employee_service.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;
import java.util.stream.Collectors;

@Endpoint
@RequiredArgsConstructor
public class EmployeeEndpoint {

    private static final String NAMESPACE_URI = "http://www.selbowgreaser.org/soap/api/employee-service";

    private final EmployeeValidator employeeValidator;
    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeesRequest")
    @ResponsePayload
    public GetEmployeesResponse getEmployees() {
        GetEmployeesResponse response = new GetEmployeesResponse();

        List<Employee> employees = employeeService.findAll();

        response.getEmployee().addAll(mapEmployeesToEmployeesResponse(employees));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeeByIdRequest")
    @ResponsePayload
    public GetEmployeeByIdResponse getEmployee(@RequestPayload GetEmployeeByIdRequest request) {
        GetEmployeeByIdResponse response = new GetEmployeeByIdResponse();

        response.setEmployee(mapEmployeeToEmployeeResponse(employeeService.findEmployeeById(request.getId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addEmployeeRequest")
    @ResponsePayload
    public AddEmployeeResponse addEmployee(@RequestPayload AddEmployeeRequest request) {
        AddEmployeeResponse response = new AddEmployeeResponse();

        Employee employee = mapEmployeeRequestToEmployee(request.getEmployee());

        List<String> errors = employeeValidator.validate(employee);

        if (!errors.isEmpty()) {
            response.setResult(ErrorResultBuilder.buildErrorResult(400, errors));
            return response;
        }

        employeeService.saveEmployee(employee);

        response.setResult(SuccessResultBuilder.buildSuccessResult(200));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateEmployeeRequest")
    @ResponsePayload
    public UpdateEmployeeResponse updateEmployee(@RequestPayload UpdateEmployeeRequest request) {
        UpdateEmployeeResponse response = new UpdateEmployeeResponse();

        Employee employee = mapEmployeeRequestToEmployee(request.getEmployee());

        List<String> errors = employeeValidator.validate(employee);

        if (!errors.isEmpty()) {
            response.setResult(ErrorResultBuilder.buildErrorResult(400, errors));
            return response;
        }

        employeeService.updateEmployee(employee);

        response.setResult(SuccessResultBuilder.buildSuccessResult(200));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteEmployeeByIdRequest")
    @ResponsePayload
    public DeleteEmployeeByIdResponse deleteEmployee(@RequestPayload DeleteEmployeeByIdRequest request) {
        DeleteEmployeeByIdResponse response = new DeleteEmployeeByIdResponse();

        employeeService.deleteEmployeeById(request.getId());

        response.setResult(SuccessResultBuilder.buildSuccessResult(200));

        return response;
    }

    private Employee mapEmployeeRequestToEmployee(EmployeeRequest employeeRequest) {
        Employee employee = modelMapper.map(employeeRequest, Employee.class);
        employee.setBirthDate(XMLGregorianCalendarUtil.mapXMLGregorianCalendarToLocalDate(employeeRequest.getBirthDate()));
        return employee;
    }

    private EmployeeResponse mapEmployeeToEmployeeResponse(Employee employee) {
        EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
        employeeResponse.setBirthDate(XMLGregorianCalendarUtil.mapLocalDateToXMLGregorianCalendar(employee.getBirthDate()));
        return employeeResponse;
    }

    private List<EmployeeResponse> mapEmployeesToEmployeesResponse(List<Employee> employees) {
        return employees.stream()
                .map(this::mapEmployeeToEmployeeResponse)
                .collect(Collectors.toList());
    }
}
