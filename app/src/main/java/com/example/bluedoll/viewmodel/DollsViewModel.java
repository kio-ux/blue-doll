package com.example.bluedoll.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bluedoll.models.Dolls;
import com.example.bluedoll.repositories.DollsRepo;

import java.util.List;

public class DollsViewModel extends ViewModel {
    DollsRepo dollsRepo = new DollsRepo();

    MutableLiveData<Dolls> mutableDolls = new MutableLiveData<>();

    public LiveData<List<Dolls>> getProducts(){
        return dollsRepo.getDolls();
    }

    public void setDolls (Dolls dolls){
        mutableDolls.setValue(dolls);
    }
    public LiveData<Dolls> getDolls(){
        return mutableDolls;
    }
}
