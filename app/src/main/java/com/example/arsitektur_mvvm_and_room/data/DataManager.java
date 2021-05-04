package com.example.arsitektur_mvvm_and_room.data;

import com.example.arsitektur_mvvm_and_room.data.db.DbHelper;
import com.example.arsitektur_mvvm_and_room.data.db.model.Hospital;
import com.example.arsitektur_mvvm_and_room.data.db.model.Medicine;

import io.reactivex.Observable;

public interface DataManager extends DbHelper {

    //    Single<List<Medical>> getMedical();

//    Single<List<Medical>> getMedical(Long numOfData);

//    Observable<Boolean> seedDatabaseHospital();

    Observable<Boolean> seedDatabaseHospital(Long numOfData);

    Observable<Boolean> updateDatabaseHospital(Hospital hospital);

    Observable<Boolean> deleteDatabaseHospital(Hospital hospital);

//    Observable<Boolean> seedDatabaseMedicine();

    Observable<Boolean> seedDatabaseMedicine(Long numOfData);

    Observable<Boolean> updateDatabaseMedicine(Medicine medicine);

    Observable<Boolean> deleteDatabaseMedicine(Medicine medicine);
}
