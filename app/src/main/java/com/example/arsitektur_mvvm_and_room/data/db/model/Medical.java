package com.example.arsitektur_mvvm_and_room.data.db.model;

public class Medical {

    private String hospitalName;

    private String medicineName;

    public Medical(
            String hospitalName, String medicineName) {
        this.hospitalName = hospitalName;
        this.medicineName = medicineName;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

}
