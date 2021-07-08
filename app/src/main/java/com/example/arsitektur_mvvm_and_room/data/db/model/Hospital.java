package com.example.arsitektur_mvvm_and_room.data.db.model;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// Model dari Object Hospital
@Entity(tableName = "hospitals")
public class Hospital {

    // Attribute id berupa integer (long)
    @Expose
    @PrimaryKey // Annotation untuk menyatakan attribut ini sebagai id
    public Long id; // atau primary key

    @Expose
    @SerializedName("hospitalName")
    @ColumnInfo(name = "hospitalName") // Annotation untuk menyatakan
    public String name; // attribut ini merupakan property
}
