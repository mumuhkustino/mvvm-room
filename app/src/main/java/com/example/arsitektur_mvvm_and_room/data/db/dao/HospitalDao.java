package com.example.arsitektur_mvvm_and_room.data.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.arsitektur_mvvm_and_room.data.db.model.Hospital;
import java.util.List;

import io.reactivex.Flowable;

@Dao

//Interface untuk model Hospital pada database
public interface HospitalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) //Annotation untuk insert
    void insert(Hospital hospital);

    @Delete //Annotation untuk delete
    void delete(Hospital hospital);

    @Query("SELECT * FROM hospitals WHERE id = :hospitalId") //Annotation untuk select
    Flowable<Hospital> load(long hospitalId);

    @Query("SELECT * FROM hospitals") //Annotation untuk select all
    Flowable<List<Hospital>> loadAll();

    @Query("SELECT * FROM hospitals LIMIT :numofData") //Annotation untuk select limit
    Flowable<List<Hospital>> loadList(Long numofData);

    @Update //Annotation untuk update
    void save(Hospital hospital);
}
