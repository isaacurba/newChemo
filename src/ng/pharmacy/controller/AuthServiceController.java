package ng.pharmacy.controller;

import ng.pharmacy.data.repositories.UserRepository;
import ng.pharmacy.dto.request.authServiceRequests.LoginChemistRequest;
import ng.pharmacy.dto.request.authServiceRequests.LogoutChemistRequest;
import ng.pharmacy.dto.request.authServiceRequests.RegisterChemistRequest;
import ng.pharmacy.dto.response.authServiceResponse.LoginChemistResponse;
import ng.pharmacy.dto.response.authServiceResponse.LogoutChemistResponse;
import ng.pharmacy.dto.response.authServiceResponse.RegisterChemistResponse;
import ng.pharmacy.exceptions.InvalidPasswordException;
import ng.pharmacy.service.AuthService;
import ng.pharmacy.service.AuthServiceImp;

public class AuthServiceController {
    private final AuthService authService;
    private final UserRepository userRepository;

    public AuthServiceController(AuthService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    public RegisterChemistResponse registerChemist(RegisterChemistRequest request){
        return authService.registerChemist(request);
    }

    public LoginChemistResponse loginChemist(LoginChemistRequest request) throws InvalidPasswordException {
        return authService.loginChemist(request);
    }

    public LogoutChemistResponse logoutChemist(LogoutChemistRequest request){
        return authService.logoutChemist(request);
    }
}
