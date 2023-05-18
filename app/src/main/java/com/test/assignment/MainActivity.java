package com.test.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ArrayList<DataModel> modelList  =new ArrayList<>();
    List<DataModel> newmodelList  =new ArrayList<>();
    RecyclerView rv_title;
    ProgressBar rv_progress;
    FloatingActionButton fab;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_title = findViewById(R.id.rv_title);
        fab = findViewById(R.id.fab);
        rv_progress = findViewById(R.id.rv_progress);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkAccessibilityPermission()){
                    Toast.makeText(MainActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Savedata();
        getRoomData();
    }


    private void getRoomData() {
        MyDatabase myDatabase = Room.databaseBuilder(getApplicationContext(),
                MyDatabase.class, "recyclerview-database").allowMainThreadQueries().build();
        List<MyEntity> entities = myDatabase.myDao().getAllEntities();
        ArrayList<DataModel> myList = new ArrayList<>();
        for (MyEntity entity : entities) {
           myList  = entity.getMyList();
            Log.d("room list====",""+myList);
        }
           rv_title.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                rv_title.setAdapter(new RecyclerAdapter(getApplicationContext(),myList,MainActivity.this));
                rv_progress.setVisibility(View.GONE);
                rv_title.setVisibility(View.VISIBLE);
    }

    private void Savedata() {
        Call<List<DataModel>> call = RetrofitClient.getInstance().getMyApi().getdata();
        call.enqueue(new Callback<List<DataModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<DataModel>> call, @NonNull Response<List<DataModel>> response) {
                List<DataModel> myheroList = response.body();
                if (myheroList == null) throw new AssertionError();

                for (int i = 0; i < myheroList.size(); i++) {
                    modelList.add(new DataModel(myheroList.get(i).getUserId(),myheroList.get(i).getId(),myheroList.get(i).getTitle(),myheroList.get(i).getBody()));
                }
                MyEntity myEntity = new MyEntity(modelList);
                MyDatabase myDatabase = Room.databaseBuilder(getApplicationContext(),
                        MyDatabase.class, "recyclerview-database").allowMainThreadQueries().build();
                myDatabase.myDao().insert(myEntity);
                Toast.makeText(getApplicationContext(), "saved to Room Database.", Toast.LENGTH_SHORT).show();

                  }

            @Override
            public void onFailure(Call<List<DataModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured"+t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });


    }

    public boolean checkAccessibilityPermission () {
        int accessEnabled = 0;
        try {
            accessEnabled = Settings.Secure.getInt(this.getContentResolver(), Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        if (accessEnabled == 0) {
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return false;
        } else {
            return true;
        }
    }
}