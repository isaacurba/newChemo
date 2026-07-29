package ng.pharmacy.service;

import ng.pharmacy.data.repositories.UserRepoImpl;
import ng.pharmacy.data.repositories.UserRepository;
import ng.pharmacy.dto.request.LoginChemistRequest;
import ng.pharmacy.dto.request.RegisterChemistRequest;
import ng.pharmacy.dto.response.LoginChemistResponse;
import ng.pharmacy.dto.response.RegisterChemistResponse;
import ng.pharmacy.exceptions.InvalidPasswordException;
import ng.pharmacy.exceptions.UserExistException;
import ng.pharmacy.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceImplTest {

    private UserRepository userRepo;
    private AuthService authService;
    private RegisterChemistRequest request;

    @BeforeEach
    public void setUp() {
        userRepo = new UserRepoImpl();
        authService = new AuthServiceImp();
        request = new RegisterChemistRequest();
        userRepo.clear();

    }

    @Test
    public void registerChemistCountIs1_Test() {
        request.setUserName("isaacurban0");
        request.setPassword("5231");
        request.setFullName("Isaac Urban");

        RegisterChemistResponse savedUser = authService.registerChemist(request);

        assertEquals("isaacurban0", savedUser.getUserName());
        assertEquals("Isaac Urban", savedUser.getFullName());
        assertEquals(1, userRepo.count());
    }

    @Test
    public void create2userCountIsTwo_Test(){
        request.setUserName("isaacurban0");
        request.setPassword("5231");
        request.setFullName("Isaac Urban");
        RegisterChemistResponse savedUser = authService.registerChemist(request);

        assertEquals("isaacurban0", savedUser.getUserName());
        assertEquals("Isaac Urban", savedUser.getFullName());
        assertEquals(1, userRepo.count());

        RegisterChemistRequest request2 = new RegisterChemistRequest();
        request2.setUserName("zicco");
        request2.setPassword("5231");
        request2.setFullName("Isaac Boluwatife");
        RegisterChemistResponse savedUser2 = authService.registerChemist(request2);

        assertEquals("isaacurban0", savedUser.getUserName());
        assertEquals("Isaac Urban", savedUser.getFullName());
        assertEquals("zicco", savedUser2.getUserName());
        assertEquals("Isaac Boluwatife", savedUser2.getFullName());
        assertEquals(2, userRepo.count());
    }

    @Test
    public void create2DuplicateUserThrowErrorCountIs1_Test() {

        request.setUserName("isaacurban0");
        request.setPassword("5231");
        request.setFullName("Isaac Urban");

        RegisterChemistResponse savedUser = authService.registerChemist(request);

        assertEquals("isaacurban0", savedUser.getUserName());
        assertEquals("Isaac Urban", savedUser.getFullName());
        assertEquals(1, userRepo.count());

        RegisterChemistRequest request2 = new RegisterChemistRequest();
        request2.setUserName("isaacurban0");
        request2.setPassword("5231");
        request2.setFullName("Isaac Boluwatife");

        assertThrows(UserExistException.class, () -> authService.registerChemist(request2));
        assertEquals(1, userRepo.count());
    }

    @Test
    public void create1UserAndLoginUser_Test() throws InvalidPasswordException {
        request.setUserName("isaacurban0");
        request.setPassword("5231");
        request.setFullName("Isaac Urban");

        authService.registerChemist(request);

        LoginChemistRequest login = new LoginChemistRequest();
        login.setUserName("isaacurban0");
        login.setPassword("5231");

        LoginChemistResponse response = authService.loginChemist(login);

        assertTrue(response.isLoggedIn());
        assertEquals("isaacurban0", response.getUserName());
    }

    @Test
    public void registerLoginWithWrongPasswordThrowsException_Test() {
        request.setUserName("isaacurban0");
        request.setPassword("5231");
        request.setFullName("Isaac Urban");

        authService.registerChemist(request);

        LoginChemistRequest login = new LoginChemistRequest();
        login.setUserName("isaacurban0");
        login.setPassword("wrongPassword");

        assertThrows(InvalidPasswordException.class, () -> authService.loginChemist(login));
    }

    @Test
    public void loginWithInvalidPasswordThrowsException_Test() {
        LoginChemistRequest login = new LoginChemistRequest();
        login.setUserName("isaacurban0");
        login.setPassword("5231");

        assertThrows(UserNotFoundException.class, () -> authService.loginChemist(login));
    }









}