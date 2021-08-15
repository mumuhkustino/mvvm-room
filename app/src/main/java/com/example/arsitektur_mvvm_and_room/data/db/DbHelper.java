package com.example.arsitektur_mvvm_and_room.data.db;

import com.example.arsitektur_mvvm_and_room.data.db.model.Hospital;
import com.example.arsitektur_mvvm_and_room.data.db.model.Medicine;

import java.util.List;

import io.reactivex.Flowable;

public interface DbHelper {

    Flowable<Boolean> insertHospitals(List<Hospital> hospitals);

    Flowable<Boolean> insertMedicines(List<Medicine> medicines);

    Flowable<Boolean> deleteHospitals(List<Hospital> hospitals);

    Flowable<Boolean> deleteMedicines(List<Medicine> medicines);

    Flowable<Hospital> loadHospital(Hospital hospital);

    Flowable<Medicine> loadMedicine(Medicine medicine);

    Flowable<List<Hospital>> getAllHospital();

    Flowable<List<Hospital>> getAllHospital(Long numOfData);

    Flowable<List<Medicine>> getAllMedicine();

    Flowable<List<Medicine>> getAllMedicine(Long numOfData);

    Flowable<List<Medicine>> getMedicinesForHospitalId(Long hospitalId);

    Flowable<Boolean> saveHospitals(List<Hospital> hospitals);

    Flowable<Boolean> saveMedicines(List<Medicine> medicines);

}
