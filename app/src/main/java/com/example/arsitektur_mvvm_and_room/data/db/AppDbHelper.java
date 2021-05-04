package com.example.arsitektur_mvvm_and_room.data.db;

import com.example.arsitektur_mvvm_and_room.data.db.model.Hospital;
import com.example.arsitektur_mvvm_and_room.data.db.model.Medicine;

import java.util.List;
import java.util.Observable;

import javax.inject.Inject;

public class AppDbHelper implements DbHelper{

    private final AppDatabase mAppDatabase;

    @Inject
    public AppDbHelper(AppDatabase appDatabase){
        this.mAppDatabase = appDatabase;
    }

    @Override
    public io.reactivex.Observable<Long> insertHospital(Hospital hospital) {
        return null;
    }

    @Override
    public io.reactivex.Observable<Long> insertMedicine(Medicine medicine) {
        return null;
    }

    @Override
    public io.reactivex.Observable<Boolean> deleteHospital(Hospital hospital) {
        return null;
    }

    @Override
    public io.reactivex.Observable<Boolean> deleteMedicine(Medicine medicine) {
        return null;
    }

    @Override
    public io.reactivex.Observable<Hospital> loadHospital(Hospital hospital) {
        return null;
    }

    @Override
    public io.reactivex.Observable<Medicine> loadMedicine(Medicine medicine) {
        return null;
    }

    @Override
    public io.reactivex.Observable<List<Hospital>> getAllHospital() {
        return null;
    }

    @Override
    public io.reactivex.Observable<List<Medicine>> getAllMedicine() {
        return null;
    }

    @Override
    public io.reactivex.Observable<List<Medicine>> getMedicineForHospitalId(Long hospitalId) {
        return null;
    }

    @Override
    public io.reactivex.Observable<Boolean> isHospitalEmpty() {
        return null;
    }

    @Override
    public io.reactivex.Observable<Boolean> isMedicineEmpty() {
        return null;
    }

    @Override
    public io.reactivex.Observable<Boolean> saveHospital(Hospital hospital) {
        return null;
    }

    @Override
    public io.reactivex.Observable<Boolean> saveMedicine(Medicine medicine) {
        return null;
    }

    @Override
    public io.reactivex.Observable<Boolean> saveHospitalList(List<Hospital> hospitalList) {
        return null;
    }

    @Override
    public io.reactivex.Observable<Boolean> saveMedicineList(List<Medicine> medicineList) {
        return null;
    }

    @Override
    public Observable
}
