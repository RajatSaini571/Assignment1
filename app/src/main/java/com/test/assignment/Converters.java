package com.test.assignment;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Converters {

    @TypeConverter
    public static ArrayList<DataModel> fromString(String value) {
        Type listType = new TypeToken<ArrayList<DataModel>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String toString(ArrayList<DataModel> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
