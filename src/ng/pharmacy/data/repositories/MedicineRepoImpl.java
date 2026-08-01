package ng.pharmacy.data.repositories;

import ng.pharmacy.data.models.Medicine;

import java.util.ArrayList;
import java.util.List;

public class MedicineRepoImpl implements MedicineRepo{

    private int nextId = 0;
    private static final List<Medicine> medicines = new ArrayList<>();

    @Override
    public Medicine save(Medicine medicine) {
        medicine.setId(++nextId);
        medicines.add(medicine);
        return medicine;
    }

    @Override
    public Medicine findById(int id) {
        for (Medicine medicine : medicines) {
            if (medicine.getId() == id) return medicine;
        }
        return null;
    }

    @Override
    public List<Medicine> findAll() {
        return medicines;
    }

    @Override
    public List<Medicine> searchByBrandName(String brandName) {
        List<Medicine> foundMedicines = new ArrayList<>();
        for (Medicine medicine : medicines) {
            if (medicine.getBrandName().toLowerCase().contains(brandName.toLowerCase()))
                foundMedicines.add(medicine);
        }
        return foundMedicines;
    }

    @Override
    public List<Medicine> searchByGenericName(String genericName) {
        List<Medicine> foundMedicines = new ArrayList<>();
        for (Medicine medicine : medicines) {
            if (medicine.getBrandName().toLowerCase().contains(genericName.toLowerCase()))
                foundMedicines.add(medicine);
        }
        return foundMedicines;
    }

    @Override
    public void deleteById(int id) {
        Medicine medicine =  findById(id);
        if (medicine == null) return;
        medicines.remove(medicine);
    }

    @Override
    public int count() {
        return medicines.size();
    }

    @Override
    public void clear() {
        medicines.clear();
    }
}
