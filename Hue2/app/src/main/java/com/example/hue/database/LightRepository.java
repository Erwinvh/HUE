package com.example.hue.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.hue.database.table.Light;

import java.util.List;

class LightRepository {

    private LightDao lightDao;
    private LiveData<List<Light>> allLights;

    public LightRepository(Application application) {
        LightRoomDatabase db = LightRoomDatabase.getDatabase(application);
        lightDao = db.lightDao();
        allLights = lightDao.getLights();
    }

    public LiveData<List<Light>> getAllLights() {
        return allLights;
    }

    public void insert(final Light light) {
        LightRoomDatabase.getExecutor().execute(() -> lightDao.insert(light));
    }
}
