package com.example.hue.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.hue.database.table.Light;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.example.hue.MainActivity.sLightDatabaseCallback;

@Database(entities = {Light.class}, version = 1)
public abstract class LightRoomDatabase extends RoomDatabase {

    public abstract LightDao lightDao();

    private static volatile LightRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    private static final ExecutorService databaseReadWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static LightRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LightRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            LightRoomDatabase.class, "light_database")
                             .addCallback(sLightDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static LightRoomDatabase getINSTANCE() {
        return INSTANCE;
    }

    public static ExecutorService getExecutor() {
        return databaseReadWriteExecutor;
    }
}
