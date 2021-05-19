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
                childColumns = "hospitalId"
        )
)

public class Medicine {
        @Expose
        @PrimaryKey
        public Long id;

        @Expose
        @SerializedName("hospitalId")
        @ColumnInfo(name = "hospitalId")
        public Long hospitalId;

        @Expose
        @SerializedName("medicineName")
        @ColumnInfo(name = "name")
        public String name;
}
