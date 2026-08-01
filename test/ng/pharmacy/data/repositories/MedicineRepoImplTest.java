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


    // ⭐ NEW/CHANGED
    // Search should return a LIST because multiple medicines can match.
    @Test
    public void create1MedicineSearchByBrandName_Test() {

        medicine.setBrandName("Panadol");
        medicine.setGenericName("Paracetamol");

        medicineRepo.save(medicine);

        List<Medicine> found = medicineRepo.searchByBrandName("Pa");

        assertEquals(1, found.size());
        assertEquals("Panadol", found.get(0).getBrandName());
        assertEquals("Paracetamol", found.get(0).getGenericName());
    }


    // ⭐ NEW/CHANGED
    // "Pa" can match multiple medicines.
    @Test
    public void create2MedicinesSearchByBrandNameReturns2_Test() {

        Medicine medicine1 = new Medicine();
        medicine1.setBrandName("Panadol");
        medicine1.setGenericName("Paracetamol");

        Medicine medicine2 = new Medicine();
        medicine2.setBrandName("Paracetamol Syrup");
        medicine2.setGenericName("Paracetamol");

        medicineRepo.save(medicine1);
        medicineRepo.save(medicine2);

        List<Medicine> found = medicineRepo.searchByBrandName("Pa");

        assertEquals(2, found.size());
    }


    // ⭐ NEW/CHANGED
    // No match should return an EMPTY LIST, not null.
    @Test
    public void searchForMedicineBrandThatDoesNotExistReturnsEmptyList_Test() {

        List<Medicine> found = medicineRepo.searchByBrandName("UnknownMedicine");

        assertTrue(found.isEmpty());
    }


    // ⭐ NEW/CHANGED
    // Generic name search also returns a LIST.
    @Test
    public void create1MedicineSearchByGenericName_Test() {

        medicine.setBrandName("Panadol");
        medicine.setGenericName("Paracetamol");

        medicineRepo.save(medicine);

        List<Medicine> found = medicineRepo.searchByGenericName("Para");

        assertEquals(1, found.size());
        assertEquals("Paracetamol", found.get(0).getGenericName());
        assertEquals("Panadol", found.get(0).getBrandName());
    }


    // ⭐ NEW/CHANGED
    // Multiple medicines can match the generic name.
    @Test
    public void create2MedicinesSearchByGenericNameReturns2_Test() {

        Medicine medicine1 = new Medicine();
        medicine1.setBrandName("Panadol");
        medicine1.setGenericName("Paracetamol");

        Medicine medicine2 = new Medicine();
        medicine2.setBrandName("Paracetamol Syrup");
        medicine2.setGenericName("Paracetamol");

        medicineRepo.save(medicine1);
        medicineRepo.save(medicine2);

        List<Medicine> found =
                medicineRepo.searchByGenericName("Para");

        assertEquals(2, found.size());
    }


    // ⭐ NEW/CHANGED
    // No matching generic name returns an empty list.
    @Test
    public void searchForMedicineGenericNameThatDoesNotExistReturnsEmptyList_Test() {

        List<Medicine> found =
                medicineRepo.searchByGenericName("Ibuprofen");

        assertTrue(found.isEmpty());
    }


    // ⭐ NEW
    // Search should not care about uppercase/lowercase.
    @Test
    public void searchByBrandNameIsCaseInsensitive_Test() {

        medicine.setBrandName("Panadol");
        medicine.setGenericName("Paracetamol");

        medicineRepo.save(medicine);

        List<Medicine> found =
                medicineRepo.searchByBrandName("pan");

        assertEquals(1, found.size());
        assertEquals("Panadol", found.get(0).getBrandName());
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