package com.example.arsitektur_mvvm_and_room.ui.crud.select;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arsitektur_mvvm_and_room.data.others.Medical;
import com.example.arsitektur_mvvm_and_room.databinding.ItemSelectEmptyViewBinding;
import com.example.arsitektur_mvvm_and_room.databinding.ItemSelectViewBinding;
import com.example.arsitektur_mvvm_and_room.ui.base.BaseViewHolder;

import java.util.List;

public class SelectAdapter extends RecyclerView.Adapter<BaseViewHolder>{
    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;

    private List<Medical> medicalList;

    private SelectAdapterListener listener;

    public SelectAdapter(List<Medical> medicalList) {
        this.medicalList = medicalList;
        Log.d("SA", "SelectAdapter: " + medicalList.size());
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                ItemSelectViewBinding itemInsertViewBinding = ItemSelectViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new SelectViewHolder(itemInsertViewBinding);
            case VIEW_TYPE_EMPTY:
            default:
                ItemSelectEmptyViewBinding emptyInsertViewBinding = ItemSelectEmptyViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new CRUDEmptyViewHolder(emptyInsertViewBinding);
        }
    }

    public void selectItems(List<Medical> medicalList) {
        this.medicalList.addAll(medicalList);
        notifyDataSetChanged();
//        Log.d("CRUDA", "selectItems: " + medicalList.size());
    }

    public void clearItems() {
        this.medicalList.clear();
    }

    public void setListener(SelectAdapterListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (this.medicalList != null && this.medicalList.size() > 0) {
            return this.medicalList.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (this.medicalList != null && this.medicalList.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    public interface SelectAdapterListener {
        void onRetryClick();
    }

    public class SelectViewHolder extends BaseViewHolder {
        private ItemSelectViewBinding binding;

        private SelectItemViewModel itemViewModel;

        public SelectViewHolder(ItemSelectViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            final Medical medical = medicalList.get(position);
            this.itemViewModel = new SelectItemViewModel((long) position, medical);
            this.binding.setViewModel(this.itemViewModel);
            this.binding.executePendingBindings();
        }
    }

    public class CRUDEmptyViewHolder extends BaseViewHolder implements SelectEmptyItemViewModel.SelectEmptyItemViewModelListener {

        private ItemSelectEmptyViewBinding binding;

        public CRUDEmptyViewHolder(ItemSelectEmptyViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            SelectEmptyItemViewModel emptyItemViewModel = new SelectEmptyItemViewModel(this);
            this.binding.setViewModel(emptyItemViewModel);
        }

        @Override
        public void onRetryClick() {
            listener.onRetryClick();
//            binding.tvMessage.setText("The fetching process was successful");
//            binding.btnRetry.setVisibility(View.GONE);
        }
    }
}
