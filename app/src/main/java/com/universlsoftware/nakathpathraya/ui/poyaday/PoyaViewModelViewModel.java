package com.universlsoftware.nakathpathraya.ui.poyaday;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PoyaViewModelViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PoyaViewModelViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("[oya]");
    }

    public LiveData<String> getText() {
        return mText;
    }
}