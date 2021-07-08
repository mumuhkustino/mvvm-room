package com.example.arsitektur_mvvm_and_room.data.db;

import com.example.arsitektur_mvvm_and_room.data.db.model.Hospital;
import com.example.arsitektur_mvvm_and_room.data.db.model.Medicine;

import java.util.List;

import io.reactivex.Flowable;

public interface DbHelper {

    Flowable<Boolean> insertHospital(Hospital hospital);

    Flowable<Boolean> insertMedicine(Medicine medicine);

    Flowable<Boolean> deleteHospital(Hospital hospital);

    Flowable<Boolean> deleteMedicine(Medicine medicine);

    Flowable<Hospital> loadHospital(Hospital hospital);

    Flowable<Medicine> loadMedicine(Medicine medicine);

    Flowable<List<Hospital>> getAllHospital();

    Flowable<List<Hospital>> getAllHospital(Long numOfData);

    Flowable<List<Medicine>> getAllMedicine();

    Flowable<List<Medicine>> getAllMedicine(Long numOfData);

    Flowable<List<Medicine>> getMedicinesForHospitalId(Long hospitalId);

    Flowable<Boolean> saveHospital(Hospital hospital);

    Flowable<Boolean> saveMedicine(Medicine medicine);

}
