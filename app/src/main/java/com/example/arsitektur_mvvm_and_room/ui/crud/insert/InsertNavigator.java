package com.example.arsitektur_mvvm_and_room.ui.crud.insert;

import java.util.List;

public interface InsertNavigator {
    public interface InsertNavigator {

        void handleError(Throwable throwable);

        void onClick();

        void updateMedical(List<Medical> medicalList);

    }
