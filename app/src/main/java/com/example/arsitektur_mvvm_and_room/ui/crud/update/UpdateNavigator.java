package com.example.arsitektur_mvvm_and_room.ui.crud.update;

import java.util.List;

public interface UpdateNavigator {
    void handleError(Throwable throwable);

    void onClick();

    void updateMedical(List<Medical> medicalList);
}
