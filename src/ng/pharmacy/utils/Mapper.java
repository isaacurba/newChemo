package ng.pharmacy.utils;

import ng.pharmacy.data.models.Medicine;
import ng.pharmacy.data.models.User;
import ng.pharmacy.dto.request.MedicineServiceRequests.AddMedicineRequest;
import ng.pharmacy.dto.request.authServiceRequests.RegisterChemistRequest;
import ng.pharmacy.dto.response.authServiceResponse.LoginChemistResponse;
import ng.pharmacy.dto.response.authServiceResponse.LogoutChemistResponse;
import ng.pharmacy.dto.response.authServiceResponse.RegisterChemistResponse;
import ng.pharmacy.dto.response.medicineServiceResponse.AddMedicineResponse;

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

    public static LogoutChemistResponse mapLogoutToUserResponse(User user){
        LogoutChemistResponse response = new LogoutChemistResponse();
        response.setLoggedIn(user.isLoggedIn());
        return response;
    }

    public static Medicine mapToMedicine(AddMedicineRequest request) {

        Medicine medicine = new Medicine();

        medicine.setBrandName(request.getBrandName());
        medicine.setGenericName(request.getGenericName());
        medicine.setStrength(request.getStrength());
        medicine.setDosageForm(request.getDosageForm());
        medicine.setManufacturer(request.getManufacturer());
        medicine.setBatchNumber(request.getBatchNumber());
        medicine.setManufactureDate(request.getManufactureDate());
        medicine.setExpiryDate(request.getExpiryDate());
        medicine.setUnitPrice(request.getUnitPrice());
        medicine.setQuantityInStock(request.getQuantityInStock());

        return medicine;
    }

    public static AddMedicineResponse mapToMedicineResponse(Medicine medicine) {

        AddMedicineResponse response = new AddMedicineResponse();

        response.setId(medicine.getId());
        response.setBrandName(medicine.getBrandName());
        response.setGenericName(medicine.getGenericName());
        response.setQuantityInStock(medicine.getQuantityInStock());

        return response;
    }
}