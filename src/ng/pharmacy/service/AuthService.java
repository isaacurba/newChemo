package ng.pharmacy.service;

import ng.pharmacy.dto.request.authServiceRequests.LoginChemistRequest;
import ng.pharmacy.dto.request.authServiceRequests.LogoutChemistRequest;
import ng.pharmacy.dto.request.authServiceRequests.RegisterChemistRequest;
import ng.pharmacy.dto.response.authServiceResponse.LoginChemistResponse;
import ng.pharmacy.dto.response.authServiceResponse.LogoutChemistResponse;
import ng.pharmacy.dto.response.authServiceResponse.RegisterChemistResponse;
import ng.pharmacy.exceptions.InvalidPasswordException;

public interface AuthService {
    RegisterChemistResponse registerChemist(RegisterChemistRequest request);
    LoginChemistResponse loginChemist(LoginChemistRequest request) throws InvalidPasswordException;
    LogoutChemistResponse logoutChemist(LogoutChemistRequest request);
}