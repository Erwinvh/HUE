package com.example.hue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Ignore;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.os.Bundle;

import com.example.hue.database.LightDao;
import com.example.hue.database.LightRoomDatabase;
import com.example.hue.database.LightViewModel;

public class MainActivity extends AppCompatActivity {

    private LightViewModel lightViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialise Room DAO. TODO: replace ViewModelProviders with ViewModelProvider
        // noinspection deprecation
        lightViewModel = ViewModelProviders.of(this).get(LightViewModel.class);

        // TODO Add line below to observe changes in Room DAO. ('adapter' is ListAdapter)
        // lightViewModel.getAllLights().observe(this, lights -> adapter.submitList(lights));
    }

    public static RoomDatabase.Callback sLightDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            LightRoomDatabase.getExecutor().execute(() -> {
                LightDao dao = LightRoomDatabase.getINSTANCE().lightDao();
                dao.deleteAll();
            });
        }
    };
}
