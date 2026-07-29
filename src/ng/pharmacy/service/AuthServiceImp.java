package ng.pharmacy.service;

import ng.pharmacy.data.models.User;
import ng.pharmacy.data.repositories.UserRepoImpl;
import ng.pharmacy.dto.request.LoginChemistRequest;
import ng.pharmacy.dto.request.RegisterChemistRequest;
import ng.pharmacy.dto.response.LoginChemistResponse;
import ng.pharmacy.dto.response.RegisterChemistResponse;
import ng.pharmacy.exceptions.InvalidPasswordException;
import ng.pharmacy.exceptions.UserExistException;
import ng.pharmacy.exceptions.UserNotFoundException;
import ng.pharmacy.utils.Mapper;

public class AuthServiceImp implements AuthService {

    private final UserRepoImpl userRepo = new UserRepoImpl();

    @Override
    public RegisterChemistResponse registerChemist(RegisterChemistRequest request) {
        if (userRepo.findByUsername(request.getUserName().toLowerCase()) != null) {
            throw new UserExistException("username " + request.getUserName() + " already exists");
        }
        request.setPassword(request.getPassword());
        request.setUserName(request.getUserName());
        request.setFullName(request.getFullName());

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




}
