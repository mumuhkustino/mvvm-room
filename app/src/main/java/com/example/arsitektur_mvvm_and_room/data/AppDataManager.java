package com.example.arsitektur_mvvm_and_room.data;

import android.content.Context;

import com.example.arsitektur_mvvm_and_room.data.db.DbHelper;
import com.example.arsitektur_mvvm_and_room.data.db.model.Hospital;
import com.example.arsitektur_mvvm_and_room.data.db.model.Medicine;
import com.example.arsitektur_mvvm_and_room.utils.AppConstants;
import com.example.arsitektur_mvvm_and_room.utils.CommonUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

@Singleton
public class AppDataManager implements DataManager {

    private final Context context;

    private final DbHelper dbHelper;

    @Inject
    public AppDataManager(Context context, DbHelper dbHelper) {
        this.context = context;
        this.dbHelper = dbHelper;
    }

    @Override
    public Flowable<List<Hospital>> seedDatabaseHospital(Long numOfData) {
        GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        final Gson gson = builder.create();

        String pathJson;
        if (numOfData < 10000) {
            pathJson = AppConstants.SEED_DATABASE_HOSPITALS_1;
        } else if (numOfData < 100000) {
            pathJson = AppConstants.SEED_DATABASE_HOSPITALS_10;
        } else if (numOfData < 1000000) {
            pathJson = AppConstants.SEED_DATABASE_HOSPITALS_100;
        } else {
            pathJson = AppConstants.SEED_DATABASE_HOSPITALS_1000;
        }
        try {
            Type type = $Gson$Types.newParameterizedTypeWithOwner(null, List.class, Hospital.class);
            List<Hospital> hospitalList = gson.fromJson(CommonUtils.loadJSONFromAsset(context, pathJson), type);
            return Flowable.just(hospitalList);
        } catch (Exception e) {
            return Flowable.just(null);
        }
    }

    @Override
    public Flowable<Boolean> updateDatabaseHospital(List<Hospital> hospitals) {
        return dbHelper.saveHospitals(hospitals);
    }

    @Override
    public Flowable<Boolean> deleteDatabaseHospital(List<Hospital> hospitals) {
        return dbHelper.deleteHospitals(hospitals);
    }

    @Override
    public Flowable<List<Medicine>> seedDatabaseMedicine(Long numOfData) {
        GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        final Gson gson = builder.create();

        String pathJson;
        if (numOfData < 10000) {
            pathJson = AppConstants.SEED_DATABASE_MEDICINES_1;
        } else if (numOfData < 100000) {
            pathJson = AppConstants.SEED_DATABASE_MEDICINES_10;
        } else if (numOfData < 1000000) {
            pathJson = AppConstants.SEED_DATABASE_MEDICINES_100;
        } else {
            pathJson = AppConstants.SEED_DATABASE_MEDICINES_1000;
        }
        try {
            Type type = new TypeToken<List<Medicine>>() {
            }.getType();
            List<Medicine> medicineList = gson.fromJson(CommonUtils.loadJSONFromAsset(context, pathJson), type);
            return Flowable.just(medicineList);
        } catch (Exception e) {
            return Flowable.just(null);
        }
    }

    @Override
    public Flowable<Boolean> updateDatabaseMedicine(List<Medicine> medicines) {
        return dbHelper.saveMedicines(medicines);
    }

    @Override
    public Flowable<Boolean> deleteDatabaseMedicine(List<Medicine> medicines) {
        return dbHelper.deleteMedicines(medicines);
    }

    @Override
    public Flowable<Boolean> insertHospitals(List<Hospital> hospitals) {
        return dbHelper.insertHospitals(hospitals);
    }

    @Override
    public Flowable<Boolean> insertMedicines(List<Medicine> medicines) {
        return dbHelper.insertMedicines(medicines);
    }

    @Override
    public Flowable<Boolean> deleteHospitals(List<Hospital> hospitals) {
        return dbHelper.deleteHospitals(hospitals);
    }

    @Override
    public Flowable<Boolean> deleteMedicines(List<Medicine> medicines) {
        return dbHelper.deleteMedicines(medicines);
    }

    @Override
    public Flowable<Hospital> loadHospital(Hospital hospital) {
        return dbHelper.loadHospital(hospital);
    }

    @Override
    public Flowable<Medicine> loadMedicine(Medicine medicine) {
        return dbHelper.loadMedicine(medicine);
    }

    @Override
    public Flowable<List<Hospital>> getAllHospital() {
        return dbHelper.getAllHospital();
    }

    @Override
    public Flowable<List<Hospital>> getAllHospital(Long numOfData) {
        return dbHelper.getAllHospital(numOfData);
    }

    @Override
    public Flowable<List<Medicine>> getAllMedicine() {
        return dbHelper.getAllMedicine();
    }

    @Override
    public Flowable<List<Medicine>> getAllMedicine(Long numOfData) {
        return dbHelper.getAllMedicine(numOfData);
    }

    @Override
    public Flowable<List<Medicine>> getMedicinesForHospitalId(Long hospitalId) {
        return dbHelper.getMedicinesForHospitalId(hospitalId);
    }

    @Override
    public Flowable<Boolean> saveHospitals(List<Hospital> hospitals) {
        return dbHelper.saveHospitals(hospitals);
    }

    @Override
    public Flowable<Boolean> saveMedicines(List<Medicine> medicines) {
        return dbHelper.saveMedicines(medicines);
    }

}
