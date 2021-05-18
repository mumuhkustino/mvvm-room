package com.example.arsitektur_mvvm_and_room.di.component;
import com.example.arsitektur_mvvm_and_room.CRUDActivity;
import com.example.arsitektur_mvvm_and_room.di.module.ActivityModule;
import com.example.arsitektur_mvvm_and_room.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = ActivityModule.class, dependencies = ApplicationComponent.class)
public interface ActivityComponent {
    void inject(CRUDActivity crudActivity);
}
