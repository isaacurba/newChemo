package ng.pharmacy.service;

import ng.pharmacy.data.models.Medicine;
import ng.pharmacy.dto.request.MedicineServiceRequests.AddMedicineRequest;
import ng.pharmacy.dto.response.medicineServiceResponse.AddMedicineResponse;

public interface MedicineService {
    AddMedicineResponse addMedicine(AddMedicineRequest request);
}
