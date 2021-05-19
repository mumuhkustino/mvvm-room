package com.example.arsitektur_mvvm_and_room.ui.crud;

import com.example.arsitektur_mvvm_and_room.data.DataManager;
import com.example.arsitektur_mvvm_and_room.ui.base.BaseViewModel;
import com.example.arsitektur_mvvm_and_room.utils.rx.SchedulerProvider;

public class CRUDViewModel extends BaseViewModel<CRUDNavigator> {
    public CRUDViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}