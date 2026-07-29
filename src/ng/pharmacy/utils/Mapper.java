package ng.pharmacy.utils;

import ng.pharmacy.data.models.User;
import ng.pharmacy.dto.request.LoginChemistRequest;
import ng.pharmacy.dto.request.RegisterChemistRequest;
import ng.pharmacy.dto.response.LoginChemistResponse;
import ng.pharmacy.dto.response.RegisterChemistResponse;

public class Mapper {

    public static User mapToUser(RegisterChemistRequest request) {
        User user = new User();
        user.setUsername(request.getUserName());
        user.setPassword(request.getPassword());
        user.setFullName(request.getFullName());
        return user;
    }

    public static RegisterChemistResponse mapToUserResponse(User user) {
        RegisterChemistResponse response = new RegisterChemistResponse();
        response.setFullName(user.getFullName());
        response.setUserName(user.getUsername());
        return response;
    }

    public static LoginChemistResponse mapLoginToUserResponse(User user) {
        LoginChemistResponse response = new LoginChemistResponse();
        response.setUserName(user.getUsername());
        response.setLoggedIn(user.isLoggedIn());
        return response;
    }
}