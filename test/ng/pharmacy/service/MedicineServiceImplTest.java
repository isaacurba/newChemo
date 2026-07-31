package ng.pharmacy.service;

import ng.pharmacy.data.repositories.MedicineRepo;
import ng.pharmacy.data.repositories.MedicineRepoImpl;
import ng.pharmacy.data.repositories.UserRepoImpl;
import ng.pharmacy.data.repositories.UserRepository;
import ng.pharmacy.dto.request.MedicineServiceRequests.AddMedicineRequest;
import ng.pharmacy.dto.request.authServiceRequests.LoginChemistRequest;
import ng.pharmacy.dto.request.authServiceRequests.RegisterChemistRequest;
import ng.pharmacy.dto.response.medicineServiceResponse.AddMedicineResponse;
import ng.pharmacy.exceptions.InvalidPasswordException;
import ng.pharmacy.exceptions.UserNotLoggedInException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MedicineServiceImplTest {

    private MedicineRepo medicineRepo;
    private MedicineService medicineService;
    private AddMedicineRequest medicineRequest;
    private AuthService authService;

    private void registerChemist() {

        RegisterChemistRequest request = new RegisterChemistRequest();

        request.setUserName("username");
        request.setPassword("password");
        request.setFullName("fullName");

        authService.registerChemist(request);
    }

    private void loginChemist() throws InvalidPasswordException {

        LoginChemistRequest request = new LoginChemistRequest();

        request.setUserName("username");
        request.setPassword("password");

        authService.loginChemist(request);
    }

    private void registerAndLoginChemist() throws InvalidPasswordException {
        registerChemist();
        loginChemist();
    }

    @BeforeEach
    public void setUp() {
        UserRepository userRepo = new UserRepoImpl();
        medicineRepo = new MedicineRepoImpl();

        authService = new AuthServiceImp(userRepo);
        medicineService = new MedicineServiceImpl(medicineRepo, userRepo);

        medicineRequest = new AddMedicineRequest();

        userRepo.clear();
        medicineRepo.clear();
    }

    @Test
    public void addMedicineCountIs1_Test() throws InvalidPasswordException {

        registerAndLoginChemist();

        medicineRequest.setUserName("username");
        medicineRequest.setBrandName("Panadol");
        medicineRequest.setGenericName("Paracetamol");
        medicineRequest.setStrength(500);
        medicineRequest.setDosageForm("Tablet");
        medicineRequest.setManufacturer("GSK");
        medicineRequest.setBatchNumber(12345);
        medicineRequest.setManufactureDate(LocalDate.of(2025, 1, 1));
        medicineRequest.setExpiryDate(LocalDate.of(2028, 1, 1));
        medicineRequest.setUnitPrice(500);
        medicineRequest.setQuantityInStock(100);

        AddMedicineResponse response = medicineService.addMedicine(medicineRequest);

        assertEquals(1, medicineRepo.count());
        assertEquals("Panadol", response.getBrandName());
        assertEquals("Paracetamol", response.getGenericName());
        assertEquals(100, response.getQuantityInStock());
    }
    @Test
    public void add2MedicinesCountIs2_Test() throws InvalidPasswordException {

        registerAndLoginChemist();

        medicineRequest.setUserName("username");
        medicineRequest.setBrandName("Panadol");
        medicineRequest.setGenericName("Paracetamol");
        medicineRequest.setStrength(500);
        medicineRequest.setDosageForm("Tablet");
        medicineRequest.setManufacturer("GSK");
        medicineRequest.setBatchNumber(12345);
        medicineRequest.setManufactureDate(LocalDate.of(2025, 1, 1));
        medicineRequest.setExpiryDate(LocalDate.of(2028, 1, 1));
        medicineRequest.setUnitPrice(500);
        medicineRequest.setQuantityInStock(100);

        AddMedicineRequest medicineRequest2 = new AddMedicineRequest();

        medicineRequest2.setUserName("username");
        medicineRequest2.setBrandName("Motrin");
        medicineRequest2.setGenericName("Ibuprofen");
        medicineRequest2.setStrength(500);
        medicineRequest2.setDosageForm("Tablet");
        medicineRequest2.setManufacturer("GSK");
        medicineRequest2.setBatchNumber(123456);
        medicineRequest2.setManufactureDate(LocalDate.of(2025, 1, 1));
        medicineRequest2.setExpiryDate(LocalDate.of(2028, 1, 1));
        medicineRequest2.setUnitPrice(500);
        medicineRequest2.setQuantityInStock(50);

        AddMedicineResponse response = medicineService.addMedicine(medicineRequest);
        AddMedicineResponse response2 = medicineService.addMedicine(medicineRequest2);

        assertEquals(2, medicineRepo.count());
        assertEquals("Panadol", response.getBrandName());
        assertEquals("Ibuprofen", response2.getGenericName());
        assertEquals(100, response.getQuantityInStock());
    }

    @Test
    public void addMedicineWithoutLoginThrowsException_Test() {

        assertThrows(UserNotLoggedInException.class, () -> medicineService.addMedicine(medicineRequest));
    }



}