package com.example.bluedoll.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.bluedoll.models.Dolls;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DollsRepo {

    private MutableLiveData<List<Dolls>> mutableDollsList;

    public LiveData<List<Dolls>> getDolls(){
        if (mutableDollsList == null ){
            mutableDollsList = new MutableLiveData<>();
            loadDolls();

        }
        return mutableDollsList;
    }
    private void loadDolls(){
        List<Dolls> dollsList = new ArrayList<>();

        dollsList.add(new Dolls(UUID.randomUUID().toString(),"Boneka Hewan","nanti di database","Boneka Hewan yang lucu yang berbentuk hewan","Creator A"));
    }

}
