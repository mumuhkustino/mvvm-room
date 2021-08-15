package com.example.arsitektur_mvvm_and_room.ui.crud.select;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.arsitektur_mvvm_and_room.data.DataManager;
import com.example.arsitektur_mvvm_and_room.data.db.model.Hospital;
import com.example.arsitektur_mvvm_and_room.data.db.model.Medicine;
import com.example.arsitektur_mvvm_and_room.data.others.ExecutionTime;
import com.example.arsitektur_mvvm_and_room.data.others.ExecutionTimePreference;
import com.example.arsitektur_mvvm_and_room.data.others.Medical;
import com.example.arsitektur_mvvm_and_room.ui.base.BaseViewModel;
import com.example.arsitektur_mvvm_and_room.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import io.reactivex.Flowable;

public class SelectViewModel extends BaseViewModel<SelectNavigator> {
    private final MutableLiveData<Long> numOfRecord;

    private final MutableLiveData<Long> databaseSelectTime;

    private final MutableLiveData<Long> allSelectTime;

    private final MutableLiveData<Long> viewSelectTime;

    private MutableLiveData<List<Medical>> medicalListLiveData;

    public SelectViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        this.numOfRecord = new MutableLiveData<>();
        this.databaseSelectTime = new MutableLiveData<>();
        this.allSelectTime = new MutableLiveData<>();
        this.viewSelectTime = new MutableLiveData<>();
        this.medicalListLiveData = new MutableLiveData<>();
    }

    // Method yang digunakan untuk select data
    public void selectDatabase(ExecutionTimePreference executionTimePreference, Long numOfData) {
        AtomicLong viewSelectTime = new AtomicLong(0);
        AtomicLong selectDbTime = new AtomicLong(0);
        AtomicLong selectTime = new AtomicLong(0);
        AtomicLong allSelectTime = new AtomicLong(System.currentTimeMillis());
        AtomicInteger indexAdd = new AtomicInteger(0);
        AtomicInteger total = new AtomicInteger(0);
        List<Medical> medicals = new ArrayList<>();
        List<Hospital> hospitals = new ArrayList<>();
        List<Medicine> medicines = new ArrayList<>();

        getCompositeDisposable().add(getDataManager()
                .getAllHospital(numOfData >= 1000 ? numOfData / 1000 : 1)
                .concatMap(Flowable::fromIterable)
                .concatMap(hospital -> {
                    if (hospital != null) {
                        total.set(total.intValue() + 1000);
                        selectTime.set(System.currentTimeMillis());
                        return getDataManager().loadHospital(hospital);
                    }
                    return null;
                })
                .doOnNext(hospital -> {
                    if (hospital != null) {
                        selectDbTime.set(selectDbTime.longValue()
                                + (System.currentTimeMillis() - selectTime.longValue()));
                        while (indexAdd.longValue() < total.longValue()) {
                            hospitals.add(indexAdd.intValue(), hospital);
                            indexAdd.getAndIncrement();
                        }
                        if (indexAdd.longValue() == numOfData)
                            indexAdd.set(0);
                    }
                })
                .observeOn(getSchedulerProvider().ui())
                .subscribe(hospital -> {
                }, throwable -> Log.d("SVM", "selectDatabase 1: " + throwable.getMessage())));
        getCompositeDisposable().add(getDataManager()
                .getAllMedicine(numOfData)
                .concatMap(Flowable::fromIterable)
                .concatMap(medicine -> {
                    if (medicine != null) {
                        selectTime.set(System.currentTimeMillis());
                        return getDataManager().loadMedicine(medicine);
                    }
                    return null;
                })
                .doOnNext(medicine -> {
                    if (medicine != null) {
                        selectDbTime.set(selectDbTime.longValue()
                                + (System.currentTimeMillis() - selectTime.longValue()));
                        if (indexAdd.longValue() < numOfData) {
                            medicines.add(indexAdd.intValue(), medicine);
                            indexAdd.getAndIncrement();
                        }
                    }
                })
                .observeOn(getSchedulerProvider().ui())
                .subscribe(medicine -> {
                    if (indexAdd.longValue() == numOfData) {
                        for (int i = 0; i < numOfData; i++) {
                            medicals.add(new Medical(hospitals.get(i).name, medicines.get(i).name));
                        }

                        this.medicalListLiveData.setValue(medicals); //Change data list
                        this.numOfRecord.setValue(indexAdd.longValue()); //Change number of record
                        this.databaseSelectTime.setValue(selectDbTime.longValue()); //Change execution time
                        AtomicLong endTime = new AtomicLong(System.currentTimeMillis());
                        AtomicLong timeElapsed = new AtomicLong(endTime.longValue() - allSelectTime.longValue());
                        viewSelectTime.set(timeElapsed.longValue() - selectDbTime.longValue());
                        this.viewSelectTime.setValue(viewSelectTime.longValue());
                        this.allSelectTime.setValue(timeElapsed.longValue());

                        ExecutionTime executionTime = executionTimePreference.getExecutionTime();
                        executionTime.setDatabaseSelectTime(selectDbTime.toString());
                        executionTime.setAllSelectTime(timeElapsed.toString());
                        executionTime.setViewSelectTime(viewSelectTime.toString());
                        executionTime.setNumOfRecordSelect(numOfData.toString());
                        executionTimePreference.setExecutionTime(executionTime);

                        indexAdd.getAndIncrement();
                    }
                }));
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

    public LiveData<Long> getDatabaseSelectTime() {
        return databaseSelectTime;
    }

    public MutableLiveData<Long> getAllSelectTime() {
        return allSelectTime;
    }

    public MutableLiveData<Long> getViewSelectTime() {
        return viewSelectTime;
    }

    public void onClick() {
        getNavigator().onClick();
    }
}
