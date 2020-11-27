package com.example.hue.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.hue.database.table.Light;

import java.util.List;

@Dao
public interface LightDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Light light);

    @Query("DELETE FROM light_table")
    void deleteAll();

    @Query("SELECT * FROM light_table ORDER BY productname ASC")
    LiveData<List<Light>> getLights();
}
