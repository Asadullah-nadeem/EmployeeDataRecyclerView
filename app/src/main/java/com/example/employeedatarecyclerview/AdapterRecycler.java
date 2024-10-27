package com.example.employeedatarecyclerview;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.MyHolder> {

    Context context;
    ArrayList<Pojo> empPojos;

    public AdapterRecycler(Context context, ArrayList<Pojo> empPojos) {
        this.context = context;
        this.empPojos = empPojos;
    }

    @NonNull
    @Override
    public AdapterRecycler.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_recycler_json, parent, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecycler.MyHolder holder, final int position) {

        holder.name.setText(empPojos.get(position).getName());
        holder.age.setText(empPojos.get(position).getAge());
        holder.city.setText(empPojos.get(position).getCity());

    }

    @Override
    public int getItemCount() {
        return empPojos.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        TextView name, age, city;

        public MyHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.name);
            age = view.findViewById(R.id.age);
            city = view.findViewById(R.id.city);
        }
    }

}
