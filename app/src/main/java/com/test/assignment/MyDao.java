package com.test.assignment;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MyDao {
    @Insert
    void insert(MyEntity myEntity);

    @Query("SELECT * FROM data_table")
    List<MyEntity> getAllEntities();

}
