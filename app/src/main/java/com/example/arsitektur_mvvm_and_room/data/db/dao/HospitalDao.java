package com.example.arsitektur_mvvm_and_room.data.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.arsitektur_mvvm_and_room.data.db.model.Hospital;
import com.example.arsitektur_mvvm_and_room.data.db.model.Medicine;

import java.util.List;

import io.reactivex.Single;

public interface HospitalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertHospital(Hospital hospital);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertAllHospital(List<Hospital> hospitals);

    @Query("SELECT * FROM hospital")
    Single<List<Hospital>> loadAllHospital();

    //save medicine
    @Update
    void saveMedicine(Hospital hospital);

    //save medicine list
    @Update
    void saveMedicineList(List<Hospital> hospitals);

    //delete medicine
    @Delete
    void deleteMedicine(Hospital hospital);

    //delete all medicine
    @Delete
    void deleteAllMedicine(List<Hospital> hospitals);

}
