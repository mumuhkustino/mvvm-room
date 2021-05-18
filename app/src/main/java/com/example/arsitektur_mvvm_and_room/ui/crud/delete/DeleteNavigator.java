package com.example.arsitektur_mvvm_and_room.ui.crud.delete;
import java.util.List;

public interface DeleteNavigator {
    void handleError(Throwable throwable);

    void onClick();

    void updateMedical(List<Medical> medicalList);
}
