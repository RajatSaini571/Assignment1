package com.test.assignment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.viewholder> {

    Context context;
    ArrayList<DataModel> arrayList;
    MainActivity activity;

    public RecyclerAdapter(Context context, ArrayList<DataModel> arrayList,MainActivity activity) {
        this.context = context;
        this.arrayList = arrayList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_recyclerview,parent,false);
        return new RecyclerAdapter.viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.viewholder holder, int position) {


        DataModel obj = arrayList.get(position);
        holder.tv_title.setText(obj.getTitle());
        holder.cv_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("body", obj.getBody());
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                BodyFragment fragobj = new BodyFragment();
                fragobj.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container, fragobj);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
               // Toast.makeText(context, ""+obj.getBody(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView tv_title;
        CardView cv_main;
        public viewholder(@NonNull View itemView) {
            super(itemView);

            cv_main = itemView.findViewById(R.id.cv_main);
            tv_title = itemView.findViewById(R.id.tv_title);

        }
    }
}
