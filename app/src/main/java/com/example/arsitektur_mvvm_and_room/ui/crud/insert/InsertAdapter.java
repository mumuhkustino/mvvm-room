package com.example.arsitektur_mvvm_and_room.ui.crud.insert;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arsitektur_mvvm_and_room.ui.base.BaseViewHolder;

import java.util.List;

public class InsertAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;

    private List<Medical> medicalList;

    private InsertAdapterListener listener;

    public InsertAdapter(List<Medical> medicalList) {
        this.medicalList = medicalList;
        Log.d("IA", "InsertAdapter: " + medicalList.size());
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                ItemInsertViewBinding itemInsertViewBinding = ItemInsertViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new InsertViewHolder(itemInsertViewBinding);
            case VIEW_TYPE_EMPTY:
            default:
                ItemInsertEmptyViewBinding emptyInsertViewBinding = ItemInsertEmptyViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new InsertEmptyViewHolder(emptyInsertViewBinding);
        }
    }

    public void addItems(List<Medical> medicalList) {
        this.medicalList.addAll(medicalList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        this.medicalList.clear();
    }

    public void setListener(InsertAdapterListener listener) {
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

    public interface InsertAdapterListener {
        void onRetryClick();
    }

    public class InsertViewHolder extends BaseViewHolder {
        private ItemInsertViewBinding binding;

        private InsertItemViewModel itemViewModel;

        public InsertViewHolder(ItemInsertViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            final Medical medical = medicalList.get(position);
            this.itemViewModel = new InsertItemViewModel((long) position, medical);
            this.binding.setViewModel(this.itemViewModel);
            this.binding.executePendingBindings();
        }
    }

    public class InsertEmptyViewHolder extends BaseViewHolder implements InsertEmptyItemViewModel.InsertEmptyItemViewModelListener {

        private ItemInsertEmptyViewBinding binding;

        public InsertEmptyViewHolder(ItemInsertEmptyViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            InsertEmptyItemViewModel emptyItemViewModel = new InsertEmptyItemViewModel(this);
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
