package ng.pharmacy.data.repositories;

import ng.pharmacy.data.models.Medicine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MedicineRepoImplTest {

    private MedicineRepo medicineRepo;
    private Medicine medicine;

    @BeforeEach
    public void setUp() {
        medicineRepo = new MedicineRepoImpl();
        medicine = new Medicine();
        medicineRepo.clear();
    }

    @Test
    public void create1MedicineSaveAndCountIs1_Test() {

        medicine.setBrandName("Panadol");
        medicine.setGenericName("Paracetamol");

        medicineRepo.save(medicine);

        assertEquals(1, medicineRepo.count());
    }

    @Test
    public void create2MedicineSaveAndCountIs2_Test() {

        Medicine medicine1 = new Medicine();
        medicine1.setBrandName("Panadol");
        medicine1.setGenericName("Paracetamol");

        Medicine medicine2 = new Medicine();
        medicine2.setBrandName("Amoxil");
        medicine2.setGenericName("Amoxicillin");

        medicineRepo.save(medicine1);
        medicineRepo.save(medicine2);

        assertEquals(2, medicineRepo.count());
    }

    @Test
    public void create1MedicineSaveFindById_Test() {

        medicine.setBrandName("Panadol");
        medicine.setGenericName("Paracetamol");

        medicineRepo.save(medicine);

        Medicine found = medicineRepo.findById(1);

        assertEquals("Panadol", found.getBrandName());
        assertEquals("Paracetamol", found.getGenericName());
    }

    @Test
    public void findMedicineThatDoesNotExistReturnsNull_Test() {

        Medicine found = medicineRepo.findById(1);

        assertNull(found);
    }

    @Test
    public void create2MedicinesFindAllReturns2_Test() {
        Medicine medicine1 = new Medicine();
        medicine1.setBrandName("Panadol");
        medicine1.setGenericName("Paracetamol");

        Medicine medicine2 = new Medicine();
        medicine2.setBrandName("Amoxil");
        medicine2.setGenericName("Amoxicillin");

        medicineRepo.save(medicine1);
        medicineRepo.save(medicine2);

        List<Medicine> medicines = medicineRepo.findAll();

        assertEquals(2, medicines.size());
    }

    @Test
    public void create1MedicineSearchByBrandName_Test() {

        medicine.setBrandName("Panadol");
        medicine.setGenericName("Paracetamol");

        medicineRepo.save(medicine);

        Medicine found = medicineRepo.searchByBrandName("Panadol");

        assertEquals("Panadol", found.getBrandName());
        assertEquals("Paracetamol", found.getGenericName());
    }

    @Test
    public void searchForMedicineBrandThatDoesNotExistReturnsNull_Test() {

        Medicine found = medicineRepo.searchByBrandName("Panadol");

        assertNull(found);
    }

    @Test
    public void create1MedicineSearchByGenericName_Test() {

        medicine.setBrandName("Panadol");
        medicine.setGenericName("Paracetamol");

        medicineRepo.save(medicine);

        Medicine found = medicineRepo.searchByGenericName("Paracetamol");

        assertEquals("Paracetamol", found.getGenericName());
        assertEquals("Panadol", found.getBrandName());
    }

    @Test
    public void create2MedicinesDelete1ByIdCountIs1_Test() {

        Medicine medicine1 = new Medicine();
        medicine1.setBrandName("Panadol");
        medicine1.setGenericName("Paracetamol");

        Medicine medicine2 = new Medicine();
        medicine2.setBrandName("Amoxil");
        medicine2.setGenericName("Amoxicillin");

        medicineRepo.save(medicine1);
        medicineRepo.save(medicine2);

        medicineRepo.deleteById(1);

        assertEquals(1, medicineRepo.count());
        assertNull(medicineRepo.findById(1));
        assertNotNull(medicineRepo.findById(2));
    }


}