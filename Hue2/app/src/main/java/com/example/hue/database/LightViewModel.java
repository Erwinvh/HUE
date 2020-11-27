package com.example.hue.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.hue.database.table.Light;

import java.util.List;

public class LightViewModel extends AndroidViewModel {

    private LightRepository repository;
    private final LiveData<List<Light>> allLights;

    public LightViewModel(@NonNull Application application) {
        super(application);
        repository = new LightRepository(application);
        allLights = repository.getAllLights();
    }

    public LiveData<List<Light>> getAllLights() {
        return allLights;
    }

    public void insert(Light light) {
        repository.insert(light);
    }
}
