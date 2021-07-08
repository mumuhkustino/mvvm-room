package com.example.arsitektur_mvvm_and_room.data.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.arsitektur_mvvm_and_room.data.db.model.Medicine;

import java.util.List;

import io.reactivex.Flowable;

@Dao
//Interface untuk model Hospital pada database
public interface MedicineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) //Annotation untuk insert
    void insert(Medicine medicine);

    @Delete //Annotation untuk delete
    void delete(Medicine medicine);

    @Query("SELECT * FROM medicines WHERE id = :id") //Annotation untuk select
    Flowable<Medicine> load(long id);

    @Query("SELECT * FROM medicines WHERE hospitalId = :hospitalId") //Annotation untuk select
    Flowable<List<Medicine>> loadAllByHospitalId(Long hospitalId);

    @Query("SELECT * FROM medicines") //Annotation untuk select all
    Flowable<List<Medicine>> loadAll();

    @Query("SELECT * FROM medicines LIMIT :numOfData") //Annotation untuk select limit
    Flowable<List<Medicine>> loadList(Long numOfData);

    @Update //Annotation untuk update
    void save(Medicine medicine);
}
