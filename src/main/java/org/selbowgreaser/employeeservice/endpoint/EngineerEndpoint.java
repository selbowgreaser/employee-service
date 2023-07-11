package org.selbowgreaser.employeeservice.endpoint;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.selbowgreaser.employeeservice.builder.SuccessResultBuilder;
import org.selbowgreaser.employeeservice.model.Engineer;
import org.selbowgreaser.employeeservice.service.api.IEngineerService;
import org.selbowgreaser.soap.api.employee_service.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
@RequiredArgsConstructor
public class EngineerEndpoint {

    private static final String NAMESPACE_URI = "http://www.selbowgreaser.org/soap/api/employee-service";

    private final IEngineerService engineerService;
    private final ModelMapper modelMapper;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAdditionalInfoEngineerByIdRequest")
    @ResponsePayload
    public GetAdditionalInfoEngineerByIdResponse getEngineer(@RequestPayload GetAdditionalInfoEngineerByIdRequest request) {
        GetAdditionalInfoEngineerByIdResponse response = new GetAdditionalInfoEngineerByIdResponse();

        Engineer engineer = engineerService.findEngineerById(request.getId());

        response.setAdditionalInfoEngineer(mapEngineerToEngineerRequest(engineer));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addAdditionalInfoEngineerRequest")
    @ResponsePayload
    public AddAdditionalInfoEngineerResponse addEngineer(@RequestPayload AddAdditionalInfoEngineerRequest request) {
        AddAdditionalInfoEngineerResponse response = new AddAdditionalInfoEngineerResponse();

        Engineer engineer = mapEngineerRequestToEngineer(request.getAdditionalInfoEngineer());

        engineerService.saveEngineer(engineer);

        response.setResult(SuccessResultBuilder.buildSuccessResult(200));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateAdditionalInfoEngineerRequest")
    @ResponsePayload
    public UpdateAdditionalInfoEngineerResponse updateEngineer(@RequestPayload UpdateAdditionalInfoEngineerRequest request) {
        UpdateAdditionalInfoEngineerResponse response = new UpdateAdditionalInfoEngineerResponse();

        Engineer engineer = mapEngineerRequestToEngineer(request.getAdditionalInfoEngineer());

        engineerService.updateEngineer(engineer);

        response.setResult(SuccessResultBuilder.buildSuccessResult(200));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteAdditionalInfoEngineerByIdRequest")
    @ResponsePayload
    public DeleteAdditionalInfoEngineerByIdResponse deleteEngineer(@RequestPayload DeleteAdditionalInfoEngineerByIdRequest request) {
        DeleteAdditionalInfoEngineerByIdResponse response = new DeleteAdditionalInfoEngineerByIdResponse();

        engineerService.deleteEngineerById(request.getId());

        response.setResult(SuccessResultBuilder.buildSuccessResult(200));

        return response;
    }

    private Engineer mapEngineerRequestToEngineer(AdditionalInfoEngineerRequest engineerRequest) {
        return modelMapper.map(engineerRequest, Engineer.class);
    }

    private AdditionalInfoEngineerRequest mapEngineerToEngineerRequest(Engineer engineer) {
        return modelMapper.map(engineer, AdditionalInfoEngineerRequest.class);
    }
}
