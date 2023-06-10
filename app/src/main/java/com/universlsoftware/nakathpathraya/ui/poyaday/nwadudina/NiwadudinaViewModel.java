package com.universlsoftware.nakathpathraya.ui.poyaday.nwadudina;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NiwadudinaViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public NiwadudinaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("niwasu");
    }

    public LiveData<String> getText() {
        return mText;
    }
}