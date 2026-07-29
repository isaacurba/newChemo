package ng.pharmacy.service;

import ng.pharmacy.data.models.Medicine;
import ng.pharmacy.data.repositories.MedicineRepo;
import ng.pharmacy.dto.request.MedicineServiceRequests.AddMedicineRequest;
import ng.pharmacy.dto.response.medicineServiceResponse.AddMedicineResponse;
import ng.pharmacy.utils.Mapper;

public class MedicineServiceImpl implements  MedicineService {

    private final MedicineRepo medicineRepo;

    public MedicineServiceImpl(MedicineRepo medicineRepo) {
        this.medicineRepo = medicineRepo;
    }

    @Override
    public AddMedicineResponse addMedicine(AddMedicineRequest request) {
        Medicine medicine = Mapper.mapToMedicine(request);
        Medicine savedMedicine = medicineRepo.save(medicine);
        return Mapper.mapToMedicineResponse(savedMedicine);
    }


}
