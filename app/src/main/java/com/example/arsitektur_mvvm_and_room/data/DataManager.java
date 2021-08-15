package com.example.arsitektur_mvvm_and_room.data;

import com.example.arsitektur_mvvm_and_room.data.db.DbHelper;
import com.example.arsitektur_mvvm_and_room.data.db.model.Hospital;
import com.example.arsitektur_mvvm_and_room.data.db.model.Medicine;

import java.util.List;

import io.reactivex.Flowable;

public interface DataManager extends DbHelper {

    Flowable<List<Hospital>> seedDatabaseHospital(Long numOfData);

    Flowable<Boolean> updateDatabaseHospital(List<Hospital> hospitals);

    Flowable<Boolean> deleteDatabaseHospital(List<Hospital> hospitals);

    Flowable<List<Medicine>> seedDatabaseMedicine(Long numOfData);

    Flowable<Boolean> updateDatabaseMedicine(List<Medicine> medicines);

    Flowable<Boolean> deleteDatabaseMedicine(List<Medicine> medicines);
}
