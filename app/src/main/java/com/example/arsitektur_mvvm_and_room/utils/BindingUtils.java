package com.example.arsitektur_mvvm_and_room.utils;

import androidx.recyclerview.widget.RecyclerView;
import androidx.databinding.BindingAdapter;

import com.example.arsitektur_mvvm_and_room.data.db.model.Medical;
import com.example.arsitektur_mvvm_and_room.data.db.model.Medicine;

import java.util.List;

public final class BindingUtils {

    private BindingUtils(){

    }

    @BindingAdapter({"adapterInsert"})
    public static void addInsertItems(RecyclerView recyclerView, List<Medical> medicalList){
        CRUDAdapter adapter = (CRUDAdapter) recyclerView.getAdapter();
        if(adapter != null){
            adapter.clearItems();
            adapter.addItems(medicalList);
        }
    }

    @BindingAdapter({"adapterSelect"})
    public static void addSelectItems(RecyclerView recyclerView, List<Medical> medicalList) {
        CRUDAdapter adapter = (CRUDAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.selectItems(medicalList);
        }
    }

    @BindingAdapter({"adapterUpdate"})
    public static void addSelectItems(RecyclerView recyclerView, List<Medical> medicalList) {
        CRUDAdapter adapter = (CRUDAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.updateItems(medicalList);
        }
    }

    @BindingAdapter({"adapterDelete"})
    public static void addSelectItems(RecyclerView recyclerView, List<Medical> medicalList) {
        CRUDAdapter adapter = (CRUDAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.deleteItems(medicalList);
        }
    }
}
