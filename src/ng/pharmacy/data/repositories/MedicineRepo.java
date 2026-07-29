package ng.pharmacy.data.repositories;

import ng.pharmacy.data.models.Medicine;
import java.util.List;

public interface MedicineRepo {

    Medicine save(Medicine medicine);
    Medicine findById(int id);
    List<Medicine> findAll();
    Medicine searchByBrandName(String brandName);
    Medicine searchByGenericName(String genericName);
    void deleteById(int id);
    int count();
    void clear();

}
