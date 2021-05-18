package com.example.arsitektur_mvvm_and_room.di.component;
import com.example.arsitektur_mvvm_and_room.di.module.FragmentModule;
import com.example.arsitektur_mvvm_and_room.di.scope.FragmentScope;
import com.example.arsitektur_mvvm_and_room.ui.crud.delete.DeleteFragment;
import com.example.arsitektur_mvvm_and_room.ui.crud.insert.InsertFragment;
import com.example.arsitektur_mvvm_and_room.ui.crud.select.SelectFragment;
import com.example.arsitektur_mvvm_and_room.ui.crud.update.UpdateFragment;

import dagger.Component;

@FragmentScope
@Component(modules = FragmentModule.class, dependencies = ApplicationComponent.class)
public interface FragmentComponent {
    void inject(InsertFragment fragment);

    void inject(SelectFragment fragment);

    void inject(UpdateFragment fragment);

    void inject(DeleteFragment fragment);
}
