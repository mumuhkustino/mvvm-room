package com.example.arsitektur_mvvm_and_room.ui.crud.select;
import java.util.List;

public interface SelectNavigator {
    void handleError(Throwable throwable);

    void onClick();

    void updateMedical(List<Medical> medicalList);
}
