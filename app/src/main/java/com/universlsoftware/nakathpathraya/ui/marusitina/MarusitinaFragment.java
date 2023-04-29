package com.universlsoftware.nakathpathraya.ui.marusitina;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.universlsoftware.nakathpathraya.R;

public class MarusitinaFragment extends Fragment {

    private MarusitinaViewModel mViewModel;

    public static MarusitinaFragment newInstance() {
        return new MarusitinaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_marusitina, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MarusitinaViewModel.class);
        // TODO: Use the ViewModel
    }

}