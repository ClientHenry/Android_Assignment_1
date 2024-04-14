package com.example.assignment_1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    private final MutableLiveData<LoadingState> loadingState = new MutableLiveData<>();

    public LiveData<LoadingState> getLoadingState() {
        return loadingState;
    }

    public void fetchData() {
        loadingState.setValue(LoadingState.LOADING);

        loadingState.setValue(LoadingState.SUCCESS);

    }
}
