package ng.pharmacy.data.repositories;

import ng.pharmacy.data.models.Medicine;

import java.util.ArrayList;
import java.util.List;

public class MedicineRepoImpl implements MedicineRepo{

    private int nextId = 0;
    private static List<Medicine> medicines = new ArrayList<Medicine>();

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
    public Medicine searchByBrandName(String brandName) {
        for  (Medicine medicine : medicines) {
            if (medicine.getBrandName().equalsIgnoreCase(brandName)) return medicine;
        }
        return null;
    }

    @Override
    public Medicine searchByGenericName(String genericName) {
        for (Medicine medicine : medicines) {
            if  (medicine.getGenericName().equalsIgnoreCase(genericName)) return medicine;
        }
        return null;
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
