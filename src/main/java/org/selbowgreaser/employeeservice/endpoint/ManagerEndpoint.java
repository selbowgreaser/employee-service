package org.selbowgreaser.employeeservice.endpoint;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.selbowgreaser.employeeservice.builder.SuccessResultBuilder;
import org.selbowgreaser.employeeservice.model.Manager;
import org.selbowgreaser.employeeservice.service.api.ManagerService;
import org.selbowgreaser.soap.api.employee_service.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class ManagerEndpoint {

    private static final String NAMESPACE_URI = "http://www.selbowgreaser.org/soap/api/employee-service";

    private final ManagerService managerService;
    private final ModelMapper modelMapper;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAdditionalInfoManagerByIdRequest")
    @ResponsePayload
    public GetAdditionalInfoManagerByIdResponse getManager(@RequestPayload GetAdditionalInfoManagerByIdRequest request) {
        GetAdditionalInfoManagerByIdResponse response = new GetAdditionalInfoManagerByIdResponse();

        Manager manager = managerService.findManagerById(request.getId());

        response.setAdditionalInfoManager(mapManagerToManagerRequest(manager));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addAdditionalInfoManagerRequest")
    @ResponsePayload
    public AddAdditionalInfoManagerResponse addManager(@RequestPayload AddAdditionalInfoManagerRequest request) {
        AddAdditionalInfoManagerResponse response = new AddAdditionalInfoManagerResponse();

        Manager manager = mapManagerRequestToManager(request.getAdditionalInfoManager());

        System.out.println(manager.toString());

        managerService.saveManager(manager);

        response.setResult(SuccessResultBuilder.buildSuccessResult(200));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateAdditionalInfoManagerRequest")
    @ResponsePayload
    public UpdateAdditionalInfoManagerResponse updateManager(@RequestPayload UpdateAdditionalInfoManagerRequest request) {
        UpdateAdditionalInfoManagerResponse response = new UpdateAdditionalInfoManagerResponse();

        Manager manager = mapManagerRequestToManager(request.getAdditionalInfoManager());

        managerService.updateManager(manager);

        response.setResult(SuccessResultBuilder.buildSuccessResult(200));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteAdditionalInfoManagerByIdRequest")
    @ResponsePayload
    public DeleteAdditionalInfoManagerByIdResponse deleteManager(@RequestPayload DeleteAdditionalInfoManagerByIdRequest request) {
        DeleteAdditionalInfoManagerByIdResponse response = new DeleteAdditionalInfoManagerByIdResponse();

        managerService.deleteManagerById(request.getId());

        response.setResult(SuccessResultBuilder.buildSuccessResult(200));

        return response;
    }

    private Manager mapManagerRequestToManager(AdditionalInfoManagerRequest managerRequest) {
        Manager manager = new Manager();
        manager.setEmployeeId(managerRequest.getEmployeeId());
        manager.setDepartment(managerRequest.getDepartment());
        manager.setResponsibility(managerRequest.getResponsibility());
        return manager;
    }

    private AdditionalInfoManagerRequest mapManagerToManagerRequest(Manager manager) {
        return modelMapper.map(manager, AdditionalInfoManagerRequest.class);
    }
}
