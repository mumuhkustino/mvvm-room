package com.example.arsitektur_mvvm_and_room.ui.crud.insert;

import com.example.arsitektur_mvvm_and_room.data.db.others.Medical;

import java.util.List;

public interface InsertNavigator {

    void handleError(Throwable throwable);

    void onClick();

    void updateMedical(List<Medical> medicalList);

}
