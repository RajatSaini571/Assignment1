package com.test.assignment;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "data_table")
public class MyEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

   public ArrayList<DataModel> myList;

    public MyEntity(ArrayList<DataModel> myList) {
        this.myList = myList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<DataModel> getMyList() {
        return myList;
    }

    public void setMyList(ArrayList<DataModel> myList) {
        this.myList = myList;
    }
}
