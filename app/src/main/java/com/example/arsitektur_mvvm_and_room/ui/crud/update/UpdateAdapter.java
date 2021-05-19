package com.example.arsitektur_mvvm_and_room.ui.crud.update;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arsitektur_mvvm_and_room.data.db.others.Medical;
import com.example.arsitektur_mvvm_and_room.databinding.ItemUpdateEmptyViewBinding;
import com.example.arsitektur_mvvm_and_room.databinding.ItemUpdateViewBinding;
import com.example.arsitektur_mvvm_and_room.ui.base.BaseViewHolder;

import java.util.List;

public class UpdateAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;

    private List<Medical> medicalList;

    private UpdateAdapterListener listener;

    public UpdateAdapter(List<Medical> medicalList) {
        this.medicalList = medicalList;
        Log.d("UA", "UpdateAdapter: " + medicalList.size());
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                ItemUpdateViewBinding itemInsertViewBinding = ItemUpdateViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new UpdateViewHolder(itemInsertViewBinding);
            case VIEW_TYPE_EMPTY:
            default:
                ItemUpdateEmptyViewBinding emptyInsertViewBinding = ItemUpdateEmptyViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new UpdateEmptyViewHolder(emptyInsertViewBinding);
        }
    }

    public void updateItems(List<Medical> medicalList) {
        this.medicalList.addAll(medicalList);
        notifyDataSetChanged();
//        Log.d("CRUDA", "selectItems: " + medicalList.size());
    }

    public void clearItems() {
        this.medicalList.clear();
    }

    public void setListener(UpdateAdapterListener listener) {
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

    public interface UpdateAdapterListener {
        void onRetryClick();
    }

    public class UpdateViewHolder extends BaseViewHolder {
        private ItemUpdateViewBinding binding;

        private UpdateItemViewModel itemViewModel;

        public UpdateViewHolder(ItemUpdateViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            final Medical medical = medicalList.get(position);
            this.itemViewModel = new UpdateItemViewModel((long) position, medical);
            this.binding.setViewModel(this.itemViewModel);
            this.binding.executePendingBindings();
        }
    }

    public class UpdateEmptyViewHolder extends BaseViewHolder implements UpdateEmptyItemViewModel.UpdateEmptyItemViewModelListener {

        private ItemUpdateEmptyViewBinding binding;

        public UpdateEmptyViewHolder(ItemUpdateEmptyViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            UpdateEmptyItemViewModel emptyItemViewModel = new UpdateEmptyItemViewModel(this);
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
