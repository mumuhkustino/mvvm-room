package com.example.arsitektur_mvvm_and_room.ui.crud.delete;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.arsitektur_mvvm_and_room.data.DataManager;
import com.example.arsitektur_mvvm_and_room.data.others.ExecutionTime;
import com.example.arsitektur_mvvm_and_room.data.others.ExecutionTimePreference;
import com.example.arsitektur_mvvm_and_room.data.others.Medical;
import com.example.arsitektur_mvvm_and_room.ui.base.BaseViewModel;
import com.example.arsitektur_mvvm_and_room.utils.rx.SchedulerProvider;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import io.reactivex.Flowable;

public class DeleteViewModel extends BaseViewModel<DeleteNavigator> {
    private final MutableLiveData<Long> numOfRecord;

    private final MutableLiveData<Long> databaseDeleteTime;

    private final MutableLiveData<Long> allDeleteTime;

    private final MutableLiveData<Long> viewDeleteTime;

    private MutableLiveData<List<Medical>> medicalListLiveData;

    public DeleteViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        this.numOfRecord = new MutableLiveData<>();
        this.databaseDeleteTime = new MutableLiveData<>();
        this.allDeleteTime = new MutableLiveData<>();
        this.viewDeleteTime = new MutableLiveData<>();
        this.medicalListLiveData = new MutableLiveData<>();
    }

    // Method yang digunakan untuk delete data
    public void deleteDatabase(ExecutionTimePreference executionTimePreference, Long numOfData) {
        AtomicLong viewDeleteTime = new AtomicLong(0);
        AtomicLong deleteDbTime = new AtomicLong(0);
        AtomicLong deleteTime = new AtomicLong(0);
        AtomicLong allDeleteTime = new AtomicLong(System.currentTimeMillis());
        AtomicInteger index = new AtomicInteger(numOfData.intValue());
        getCompositeDisposable().add(getDataManager()
                //Get All Hospital with Limit
                .getAllHospital(numOfData >= 1000 ? numOfData / 1000 : 1)
                .concatMap(Flowable::fromIterable)
                //Get All Medicine with same hospital Id
                .concatMap(hospital -> Flowable.zip(
                        getDataManager().getMedicinesForHospitalId(hospital.id),
                        Flowable.just(hospital),
                        ((medicineList, hospital1) -> medicineList)
                ))
                //Delete All Medicine
                .concatMap(medicines -> {
                    deleteTime.set(System.currentTimeMillis());
                    index.set(index.get() - medicines.size());
                    return getDataManager().deleteDatabaseMedicine(medicines);
                })
                .doOnNext(aBoolean -> {
                    if (aBoolean) {
                        deleteDbTime.set(deleteDbTime.longValue() + (System.currentTimeMillis() - deleteTime.longValue()));
                    }
                })
                .observeOn(getSchedulerProvider().ui())
                .subscribe(aBoolean -> {
                            if (index.get() == 0) {
                                this.numOfRecord.setValue(index.longValue()); //Change number of record
                                this.databaseDeleteTime.setValue(deleteDbTime.longValue()); //Change execution time
                                AtomicLong endTime = new AtomicLong(System.currentTimeMillis());
                                AtomicLong timeElapsed = new AtomicLong(endTime.longValue() - allDeleteTime.longValue());
                                viewDeleteTime.set(timeElapsed.longValue() - deleteDbTime.longValue());
                                this.viewDeleteTime.setValue(viewDeleteTime.longValue());
                                this.allDeleteTime.setValue(timeElapsed.longValue());

                                ExecutionTime executionTime = executionTimePreference.getExecutionTime();
                                executionTime.setDatabaseDeleteTime(deleteDbTime.toString());
                                executionTime.setAllDeleteTime(timeElapsed.toString());
                                executionTime.setViewDeleteTime(viewDeleteTime.toString());
                                executionTime.setNumOfRecordDelete(numOfData.toString());
                                executionTimePreference.setExecutionTime(executionTime);

                                Log.d("DVM", "deleteDatabase: " + index.longValue());
                                index.getAndDecrement();
                            }
                        }, throwable -> Log.d("DVM", "deleteDatabase: " + throwable.getMessage())
                )
        );
    }

    public LiveData<List<Medical>> getMedicalListLiveData() {
        return medicalListLiveData;
    }

    public void setMedicalListLiveData(MutableLiveData<List<Medical>> medicalListLiveData) {
        this.medicalListLiveData = medicalListLiveData;
    }

    public LiveData<Long> getNumOfRecord() {
        return numOfRecord;
    }

    public LiveData<Long> getDatabaseDeleteTime() {
        return databaseDeleteTime;
    }

    public MutableLiveData<Long> getAllDeleteTime() {
        return allDeleteTime;
    }

    public MutableLiveData<Long> getViewDeleteTime() {
        return viewDeleteTime;
    }

    public void onClick() {
        getNavigator().onClick();
    }
}
