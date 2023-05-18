package com.test.assignment;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {MyEntity.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class MyDatabase extends RoomDatabase {
    public abstract MyDao myDao();
}
