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
    public Flowable<Boolean> insertHospitals(List<Hospital> hospitals) {
        return Flowable.fromCallable(() -> {
            try {
                mAppDatabase.hospitalDao().insert(hospitals);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    @Override
    public Flowable<Boolean> insertMedicines(List<Medicine> medicines) {
        return Flowable.fromCallable(() -> {
            try {
                mAppDatabase.medicineDao().insert(medicines);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    @Override
    public Flowable<Boolean> deleteHospitals(List<Hospital> hospitals) {
        return Flowable.fromCallable(() -> {
            try {
                mAppDatabase.hospitalDao().delete(hospitals);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    @Override
    public Flowable<Boolean> deleteMedicines(List<Medicine> medicines) {
        return Flowable.fromCallable(() -> {
            try {
                mAppDatabase.medicineDao().delete(medicines);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    @Override
    public Flowable<Hospital> loadHospital(Hospital hospital) {
        return Flowable.fromCallable(() -> {
            try {
                return mAppDatabase.hospitalDao().load(hospital.id);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    @Override
    public Flowable<Medicine> loadMedicine(Medicine medicine) {
        return Flowable.fromCallable(() -> {
            try {
                return mAppDatabase.medicineDao().load(medicine.id);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    @Override
    public Flowable<List<Hospital>> getAllHospital() {
        return Flowable.fromCallable(() -> {
            try {
                return mAppDatabase.hospitalDao().loadAll();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    @Override
    public Flowable<List<Hospital>> getAllHospital(Long numOfData) {
        return Flowable.fromCallable(() -> {
            try {
                return mAppDatabase.hospitalDao().loadList(numOfData);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    @Override
    public Flowable<List<Medicine>> getAllMedicine() {
        return Flowable.fromCallable(() -> {
            try {
                return mAppDatabase.medicineDao().loadAll();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    @Override
    public Flowable<List<Medicine>> getAllMedicine(Long numOfData) {
        return Flowable.fromCallable(() -> {
            try {
                return mAppDatabase.medicineDao().loadList(numOfData);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    @Override
    public Flowable<List<Medicine>> getMedicinesForHospitalId(Long hospitalId) {
        return Flowable.fromCallable(() -> {
            try {
                return mAppDatabase.medicineDao().loadAllByHospitalId(hospitalId);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    @Override
    public Flowable<Boolean> saveHospitals(List<Hospital> hospitals) {
        return Flowable.fromCallable(() -> {
            try {
                mAppDatabase.hospitalDao().save(hospitals);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    @Override
    public Flowable<Boolean> saveMedicines(List<Medicine> medicines) {
        return Flowable.fromCallable(() -> {
            try {
                mAppDatabase.medicineDao().save(medicines);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
    }
}
