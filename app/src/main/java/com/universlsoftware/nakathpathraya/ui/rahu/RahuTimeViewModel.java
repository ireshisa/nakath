package com.universlsoftware.nakathpathraya.ui.rahu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RahuTimeViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private final MutableLiveData<String> mText;

    public RahuTimeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("[oya]");
    }

    public LiveData<String> getText() {
        return mText;
    }
}