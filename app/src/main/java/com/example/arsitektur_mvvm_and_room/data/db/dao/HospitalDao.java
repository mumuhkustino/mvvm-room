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

import io.reactivex.Flowable;

public interface HospitalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Hospital hospital);

    @Delete
    void delete(Hospital hospital);

    @Query("SELECT * FROM hospitals WHERE id = :hospitalId")
    Flowable<Hospital> load(long hospitalId);

    @Query("SELECT * FROM hospitals")
    Flowable<List<Hospital>> loadAll();

    @Query("SELECT * FROM hospitals LIMIT :numofData")
    Flowable<List<Hospital>> loadList(Long numofData);

    @Update
    void save(Hospital hospital);
}
