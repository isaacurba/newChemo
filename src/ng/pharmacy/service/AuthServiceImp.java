package ng.pharmacy.service;

import ng.pharmacy.data.models.User;
import ng.pharmacy.data.repositories.UserRepoImpl;
import ng.pharmacy.data.repositories.UserRepository;
import ng.pharmacy.dto.request.authServiceRequests.LoginChemistRequest;
import ng.pharmacy.dto.request.authServiceRequests.LogoutChemistRequest;
import ng.pharmacy.dto.request.authServiceRequests.RegisterChemistRequest;
import ng.pharmacy.dto.response.authServiceResponse.LoginChemistResponse;
import ng.pharmacy.dto.response.authServiceResponse.LogoutChemistResponse;
import ng.pharmacy.dto.response.authServiceResponse.RegisterChemistResponse;
import ng.pharmacy.exceptions.InvalidPasswordException;
import ng.pharmacy.exceptions.UserExistException;
import ng.pharmacy.exceptions.UserNotFoundException;
import ng.pharmacy.utils.Mapper;

public class AuthServiceImp implements AuthService {

    private final UserRepoImpl userRepo;

    public AuthServiceImp(UserRepository userRepo) {
        this.userRepo = new UserRepoImpl();
    }

    @Override
    public RegisterChemistResponse registerChemist(RegisterChemistRequest request) {

        String username = request.getUserName().toLowerCase();

        if (userRepo.findByUsername(username) != null) {
            throw new UserExistException("Username " + request.getUserName() + " already exists");
        }

        request.setUserName(username);

        User user = Mapper.mapToUser(request);
        User savedUser = userRepo.save(user);

        return Mapper.mapToUserResponse(savedUser);
    }


    @Override
    public LoginChemistResponse loginChemist(LoginChemistRequest request) throws InvalidPasswordException {
        User user = userRepo.findByUsername(request.getUserName());
        if (user == null) throw new UserNotFoundException(STR."UserInvalidPasswordException with \{request.getUserName()} does not exist");
        if (!user.getPassword().equals(request.getPassword())) throw new InvalidPasswordException();
        user.setIsLoggedIn(true);
        userRepo.save(user);
        return Mapper.mapLoginToUserResponse(user);
    }

    @Override
    public LogoutChemistResponse logoutChemist(LogoutChemistRequest request) {

        User user = userRepo.findByUsername(request.getUsername());

        if (user == null) throw new UserNotFoundException(STR."User with \{request.getUsername()} does not exist");

        user.setIsLoggedIn(false);

        return Mapper.mapLogoutToUserResponse(user);
    }


}
