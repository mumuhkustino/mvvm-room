package com.example.arsitektur_mvvm_and_room.data.db;

import com.example.arsitektur_mvvm_and_room.data.db.model.Hospital;
import com.example.arsitektur_mvvm_and_room.data.db.model.Medicine;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class AppDbHelper implements DbHelper{

    private final AppDatabase mAppDatabase;

    @Inject
    public AppDbHelper(AppDatabase appDatabase){
        this.mAppDatabase = appDatabase;
    }


    @Override
    public Flowable<Boolean> insertHospital(final Hospital hospital) {
        return Flowable.fromCallable(() -> {
            try {
                mAppDatabase.hospitalDao().insert(hospital);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    @Override
    public Flowable<Boolean> insertMedicine(final Medicine medicine) {
        return Flowable.fromCallable(() -> {
            try {
                mAppDatabase.medicineDao().insert(medicine);
                return true;
            } catch (Exception e){
                e.printStackTrace();
                return false;
            }
        });
    }

    @Override
    public Flowable<Boolean> deleteHospital(final Hospital hospital) {
        return Flowable.fromCallable(() -> {
            try {
                mAppDatabase.hospitalDao().delete(hospital);
                return true;
            } catch (Exception e){
                e.printStackTrace();
                return false;
            }
        });
    }

    @Override
    public Flowable<Boolean> deleteMedicine(final Medicine medicine) {
        return Flowable.fromCallable(() -> {
            try {
                mAppDatabase.medicineDao().delete(medicine);
                return true;
            } catch (Exception e){
                e.printStackTrace();
                return false;
            }
        });
    }

    @Override
    public Flowable<Hospital> loadHospital(Hospital hospital) {
        return mAppDatabase.hospitalDao().load(hospital.id);
    }

    @Override
    public Flowable<Medicine> loadMedicine(Medicine medicine) {
        return mAppDatabase.medicineDao().load(medicine.id);
    }

    @Override
    public Flowable<List<Hospital>> getAllHospital() {
        return mAppDatabase.hospitalDao().loadAll();
    }

    @Override
    public Flowable<List<Hospital>> getAllHospital(Long numOfData) {
        return mAppDatabase.hospitalDao().loadList(numOfData);
    }

    @Override
    public Flowable<List<Medicine>> getAllMedicine() {
        return mAppDatabase.medicineDao().loadAll();
    }

    @Override
    public Flowable<List<Medicine>> getAllMedicine(Long numOfData) {
        return mAppDatabase.medicineDao().loadList(numOfData);
    }

    @Override
    public Flowable<List<Medicine>> getMedicinesForHospitalId(Long hospitalId) {
        return mAppDatabase.medicineDao().loadAllByHospitalId(hospitalId);
    }

    @Override
    public Flowable<Boolean> saveHospital(final Hospital hospital) {
        return Flowable.fromCallable(() -> {
            try {
                mAppDatabase.hospitalDao().save(hospital);
                return true;
            } catch (Exception e){
                e.printStackTrace();
                return false;
            }
        });
    }

    @Override
    public Flowable<Boolean> saveMedicine(final Medicine medicine) {
        return Flowable.fromCallable(() -> {
            try {
                mAppDatabase.medicineDao().save(medicine);
                return true;
            } catch (Exception e){
                e.printStackTrace();
                return false;
            }
        });
    }
}
