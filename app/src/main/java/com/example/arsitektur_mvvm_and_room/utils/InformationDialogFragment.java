package com.example.arsitektur_mvvm_and_room.utils;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.arsitektur_mvvm_and_room.R;

public class InformationDialogFragment extends DialogFragment {
    private Button mCloseButton;

    public InformationDialogFragment() {
    }

    public static InformationDialogFragment newInstance() {
        InformationDialogFragment frag = new InformationDialogFragment();
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.information_dialog, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCloseButton = view.findViewById(R.id.btnClose);
        mCloseButton.setOnClickListener(v -> {
            getDialog().cancel();
        });
    }

}
