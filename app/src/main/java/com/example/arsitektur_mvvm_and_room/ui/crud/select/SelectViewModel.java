package com.example.arsitektur_mvvm_and_room.ui.crud.select;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.Flowable;

public class SelectViewModel extends BaseViewModel<SelectNavigator> {
    private final MutableLiveData<Long> numOfRecord;

    private final MutableLiveData<Long> executionTime;

    private MutableLiveData<List<Medical>> medicalListLiveData;

    public SelectViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        this.numOfRecord = new MutableLiveData<>();
        this.executionTime = new MutableLiveData<>();
        this.medicalListLiveData = new MutableLiveData<>();
    }

    public void selectDatabase(Long numOfData) {
        long startTime = System.currentTimeMillis();
        AtomicInteger index = new AtomicInteger(0);
        List<Medical> medicals = new ArrayList<>();
        getCompositeDisposable().add(getDataManager()
                //Get All Hospital with Limit
                .getAllHospital(numOfData >= 1000 ? numOfData / 1000 : 1)
                .concatMap(Flowable::fromIterable)
                //Get All Medicine with same hospital Id
                .concatMap(hospital -> Flowable.zip(
                        getDataManager().getMedicineForHospitalId(hospital.getId()),
                        Flowable.just(hospital),
                        ((medicineList, h) -> {
                            for (Medicine m : medicineList) {
                                if (index.get() < numOfData) {
                                    medicals.add(new Medical(h.getName(), m.getName()));
                                    index.getAndIncrement();
                                }
                            }
                            return medicals;
                        })
                ))
                .observeOn(getSchedulerProvider().ui())
                .subscribe(medicalList -> {
                            if (medicalList != null && index.get() == numOfData) {
                                this.medicalListLiveData.setValue(medicalList); //Change data list
                                this.numOfRecord.setValue(index.longValue()); //Change number of record
                                long endTime = System.currentTimeMillis();
                                long timeElapsed = endTime - startTime; //In MilliSeconds
                                this.executionTime.setValue(timeElapsed); //Change execution time
                                Log.d("SVM", "selectDatabase: " + index.get());
                                index.getAndIncrement();
                            }
                        }, throwable -> Log.d("SVM", "selectDatabase: " + throwable.getMessage())
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

    public LiveData<Long> getExecutionTime() {
        return executionTime;
    }

    public void onClick() {
        getNavigator().onClick();
    }
}
