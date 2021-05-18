package com.example.arsitektur_mvvm_and_room.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.arsitektur_mvvm_and_room.data.db.dao.HospitalDao;
import com.example.arsitektur_mvvm_and_room.data.db.dao.MedicineDao;
import com.example.arsitektur_mvvm_and_room.data.db.model.Hospital;
import com.example.arsitektur_mvvm_and_room.data.db.model.Medicine;

@Database(entities = {Hospital.class, Medicine.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {

    public abstract HospitalDao hospitalDao();
    public abstract MedicineDao medicineDao();

}
