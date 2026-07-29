package ng.pharmacy.service;

import ng.pharmacy.dto.request.LoginChemistRequest;
import ng.pharmacy.dto.request.RegisterChemistRequest;
import ng.pharmacy.dto.response.LoginChemistResponse;
import ng.pharmacy.dto.response.RegisterChemistResponse;
import ng.pharmacy.exceptions.InvalidPasswordException;

public interface AuthService {
    RegisterChemistResponse registerChemist(RegisterChemistRequest request);
    LoginChemistResponse loginChemist(LoginChemistRequest request) throws InvalidPasswordException;
    LogOutUserResponse logoutChemist(LogoutChemsistRequest request);
}