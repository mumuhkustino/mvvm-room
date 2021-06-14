package com.example.arsitektur_mvvm_and_room.ui.crud.delete;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.arsitektur_mvvm_and_room.data.DataManager;
import com.example.arsitektur_mvvm_and_room.data.db.others.Medical;
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

    public void deleteDatabase(Long numOfData) {
        AtomicLong viewDeleteTime = new AtomicLong(0);
        AtomicLong deleteDbTime = new AtomicLong(0);
        AtomicLong deleteTime = new AtomicLong(0);
        AtomicLong allDeleteTime = new AtomicLong(System.currentTimeMillis());
        AtomicInteger index = new AtomicInteger(0);
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
                //Delete medicine name with object
                .concatMap(Flowable::fromIterable)
                .concatMap(medicine -> {
                    if (index.get() < numOfData) {
                        index.getAndIncrement();
                        deleteTime.set(System.currentTimeMillis());
                        return getDataManager().deleteDatabaseMedicine(medicine);
                    }
                    return Flowable.just(false);
                })
                .doOnNext(aBoolean -> {
                    if (aBoolean) {
                        deleteDbTime.set(deleteDbTime.get() + (System.currentTimeMillis() - deleteTime.get()));
                    }
                })
                .observeOn(getSchedulerProvider().ui())
                .subscribe(aBoolean -> {
                            if (index.get() == numOfData) {
                                this.numOfRecord.setValue(index.longValue()); //Change number of record
                                this.databaseDeleteTime.setValue(deleteDbTime.get()); //Change execution time
                                AtomicLong endTime = new AtomicLong(System.currentTimeMillis());
                                AtomicLong timeElapsed = new AtomicLong(endTime.get() - allDeleteTime.get());
                                viewDeleteTime.set(timeElapsed.get() - deleteDbTime.get());
                                this.viewDeleteTime.setValue(viewDeleteTime.get());
                                this.allDeleteTime.setValue(timeElapsed.get());
                                Log.d("DVM", "deleteDatabase: " + index.get());
                                index.getAndIncrement();
                            }
                        }, throwable -> Log.d("DVM", "deleteDatabase: " + throwable.getMessage())
                )
        );
    }

    public void setMedicalListLiveData(MutableLiveData<List<Medical>> medicalListLiveData) {
        this.medicalListLiveData = medicalListLiveData;
    }

    public LiveData<List<Medical>> getMedicalListLiveData() {
        return medicalListLiveData;
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
