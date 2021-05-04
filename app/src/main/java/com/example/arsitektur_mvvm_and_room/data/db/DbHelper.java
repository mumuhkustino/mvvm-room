package com.example.arsitektur_mvvm_and_room.data.db;

import com.example.arsitektur_mvvm_and_room.data.db.model.Hospital;
import com.example.arsitektur_mvvm_and_room.data.db.model.Medicine;

import java.util.List;
import io.reactivex.Observable;

public interface DbHelper {

    Observable<Long> insertHospital(Hospital hospital);

    Observable<Long> insertMedicine( Medicine medicine);

    Observable<Boolean> deleteHospital(Hospital hospital);

    Observable<Boolean> deleteMedicine(Medicine medicine);

    Observable<Hospital> loadHospital(Hospital hospital);

    Observable<Medicine> loadMedicine(Medicine medicine);

    Observable<List<Hospital>> getAllHospital();

    Observable<List<Medicine>> getAllMedicine();

    Observable<List<Medicine>> getMedicineForHospitalId(Long hospitalId);

    Observable<Boolean> isHospitalEmpty();

    Observable<Boolean> isMedicineEmpty();

//    Observable<Boolean> isDiseaseEmpty();

//    Observable<Boolean> isSymptomEmpty();

    Observable<Boolean> saveHospital(Hospital hospital);

    Observable<Boolean> saveMedicine(Medicine medicine);

    Observable<Boolean> saveHospitalList(List<Hospital> hospitalList);

    Observable<Boolean> saveMedicineList(List<Medicine> medicineList);

}
