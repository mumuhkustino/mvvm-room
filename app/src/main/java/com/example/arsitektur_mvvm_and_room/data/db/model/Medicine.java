package com.example.arsitektur_mvvm_and_room.data.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(
        tableName = "medicines",
        foreignKeys = @ForeignKey(
                entity =Hospital.class,
                parentColumns = "id",
                childColumns = "hospital_id"
        )
)

public class Medicine {
        @Expose
        @PrimaryKey
        public Long id;

        @Expose
        @SerializedName("medicineName")
        @ColumnInfo(name = "name")
        private String name;
}
