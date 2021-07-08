package com.example.arsitektur_mvvm_and_room.data.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// Model dari Object Medicine
@Entity(
        tableName = "medicines",
        foreignKeys = @ForeignKey(
                entity =Hospital.class,
                parentColumns = "id",
                childColumns = "hospitalId" // Annotation untuk menyatakan attribut foreign key
        )
)

public class Medicine {

        @Expose
        @PrimaryKey // Annotation untuk menyatakan attribut ini sebagai id
        public Long id; // atau primary key

        @Expose
        @SerializedName("hospitalId") // Pemrosesan konversi file json ke dalam objek
        @ColumnInfo(name = "hospitalId") // Annotation untuk menyatakan attribut property
        public Long hospitalId;

        @Expose
        @SerializedName("medicineName")
        @ColumnInfo(name = "medicineName") // Annotation untuk menyatakan attribut property
        public String name;
}
