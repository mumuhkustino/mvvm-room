package com.example.arsitektur_mvvm_and_room.data.db.model.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.arsitektur_mvvm_and_room.data.db.model.Medicine;

import java.util.List;

import io.reactivex.Single;

public interface MedicineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertMedicine(Medicine medicine);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertAllMedicine(List<Medicine> medicines);

    @Query("SELECT * FROM medicines")
    Single<List<Medicine>> loadAllMedicines();

    //save medicine
    @Update
    void saveMedicine(Medicine medicine);

    //save medicine list
    @Update
    void saveMedicineList(List<Medicine> medicines);

    //delete medicine
    @Delete
    void deleteMedicine(Medicine medicine);

    //delete all medicine
    @Delete
    void deleteAllMedicine(List<Medicine> medicines);
}
