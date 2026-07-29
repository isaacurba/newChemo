package ng.pharmacy.controller;

import ng.pharmacy.dto.request.authServiceRequests.RegisterChemistRequest;
import ng.pharmacy.dto.response.authServiceResponse.RegisterChemistResponse;
import ng.pharmacy.service.AuthService;
import ng.pharmacy.service.AuthServiceImp;

public class AuthServiceController {
    private final AuthService authService = new AuthServiceImp();

    public RegisterChemistResponse registerChemist(RegisterChemistRequest request){
        return authService.registerChemist(request);
    }
}
