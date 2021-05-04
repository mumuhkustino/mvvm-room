package com.example.arsitektur_mvvm_and_room.utils.rx;

import java.util.concurrent.Executors;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AppSchedulerProvider implements SchedulerProvider{

    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }

    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler single() {
        return Schedulers.single();
    }

    @Override
    public Scheduler trampoline() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler fromA() {
        return Schedulers.from(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));
    }

    @Override
    public Scheduler fromB() {
        return Schedulers.from(Executors.newFixedThreadPool(8));
    }

    @Override
    public Scheduler fromC() {
        return Schedulers.from(Executors.newFixedThreadPool(8));
    }

    @Override
    public Scheduler immediate() {
        return null;
    }
}
