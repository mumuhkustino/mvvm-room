package com.example.arsitektur_mvvm_and_room.ui.crud.insert;

import androidx.databinding.ObservableField;

import com.example.arsitektur_mvvm_and_room.data.others.Medical;

public class InsertItemViewModel {

    public final ObservableField<Long> id;

    public final ObservableField<String> hospitalName;

    public final ObservableField<String> medicineName;

    public final Medical medical;

    public InsertItemViewModel(Long id, Medical medical) {
        this.medical = medical;
        this.id = new ObservableField<>(id);
        this.hospitalName = new ObservableField<>(medical.getHospitalName());
        this.medicineName = new ObservableField<>(medical.getMedicineName());
    }

}
