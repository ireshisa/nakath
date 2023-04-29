package com.universlsoftware.nakathpathraya.ui.poyaday;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.universlsoftware.nakathpathraya.databinding.FragmentPoyaDay2Binding;
import com.universlsoftware.nakathpathraya.ui.poyaday.PoyaViewModelViewModel;

public class PoyaDayFragment extends Fragment {

        private FragmentPoyaDay2Binding binding;

        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            PoyaViewModelViewModel poyaViewModel;
            poyaViewModel = new ViewModelProvider(this).get(PoyaViewModelViewModel.class);

            binding = FragmentPoyaDay2Binding.inflate(inflater, container, false);
            View root = binding.getRoot();

//            final TextView textView = binding.textGallery;
//            poyaViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);


            return root;
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            binding = null;
        }
    }