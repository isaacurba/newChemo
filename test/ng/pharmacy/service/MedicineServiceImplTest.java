package ng.pharmacy.service;

import ng.pharmacy.data.repositories.MedicineRepo;
import ng.pharmacy.data.repositories.MedicineRepoImpl;
import ng.pharmacy.dto.request.MedicineServiceRequests.AddMedicineRequest;
import ng.pharmacy.dto.response.medicineServiceResponse.AddMedicineResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MedicineServiceImplTest {

    private MedicineRepo medicineRepo;
    private MedicineService medicineService;
    private AddMedicineRequest request;

    @BeforeEach
    public void setUp() {

        medicineRepo = new MedicineRepoImpl();
        medicineService = new MedicineServiceImpl(medicineRepo);
        request = new AddMedicineRequest();

        medicineRepo.clear();
    }

    @Test
    public void addMedicineCountIs1_Test() {

        request.setBrandName("Panadol");
        request.setGenericName("Paracetamol");
        request.setStrength(500);
        request.setDosageForm("Tablet");
        request.setManufacturer("GSK");
        request.setBatchNumber(12345);
        request.setManufactureDate(LocalDate.of(2025, 1, 1));
        request.setExpiryDate(LocalDate.of(2028, 1, 1));
        request.setUnitPrice(500);
        request.setQuantityInStock(100);

        AddMedicineResponse response = medicineService.addMedicine(request);

        assertEquals(1, medicineRepo.count());
        assertEquals("Panadol", response.getBrandName());
        assertEquals("Paracetamol", response.getGenericName());
        assertEquals(100, response.getQuantityInStock());
    }

    @Test
    public void add2MedicinesCountIs2_Test() {

        request.setBrandName("Panadol");
        request.setGenericName("Paracetamol");
        request.setStrength(500);
        request.setDosageForm("Tablet");
        request.setManufacturer("GSK");
        request.setBatchNumber(12345);
        request.setManufactureDate(LocalDate.of(2025, 1, 1));
        request.setExpiryDate(LocalDate.of(2028, 1, 1));
        request.setUnitPrice(500);
        request.setQuantityInStock(100);

        AddMedicineRequest request2 = new AddMedicineRequest();

        request2.setBrandName("Motrin");
        request2.setGenericName("Ibuprofen");
        request2.setStrength(500);
        request2.setDosageForm("Tablet");
        request2.setManufacturer("GSK");
        request2.setBatchNumber(123456);
        request2.setManufactureDate(LocalDate.of(2025, 1, 1));
        request2.setExpiryDate(LocalDate.of(2028, 1, 1));
        request2.setUnitPrice(500);
        request2.setQuantityInStock(50);

        AddMedicineResponse response = medicineService.addMedicine(request);
        AddMedicineResponse response2 = medicineService.addMedicine(request2);

        assertEquals(2, medicineRepo.count());
        assertEquals("Panadol", response.getBrandName());
        assertEquals("Ibuprofen", response2.getGenericName());
        assertEquals(100, response.getQuantityInStock());
    }

}