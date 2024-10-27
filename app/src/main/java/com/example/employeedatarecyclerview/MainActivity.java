package com.example.employeedatarecyclerview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button button;
    ArrayList<Pojo> empList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.show_data);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        empList = new ArrayList<>();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (empList != null) {
                    empList.clear();
                    parseEmpData();
                } else {
                    parseEmpData();
                }
            }
        });

    }

    private void parseEmpData() {

        try {
            String loadJsonData = loadJSONFromRaw();
            JSONObject rootJSONObject = new JSONObject(loadJsonData);

            JSONArray empJSONArray = rootJSONObject.getJSONArray("employees");

            for (int i = 0; i < empJSONArray.length(); i++) {
                JSONObject employee = empJSONArray.getJSONObject(i);

                String name = employee.getString("name");
                String age = employee.getString("age");
                String city = employee.getString("city");

                Pojo pojo = new Pojo();
                pojo.setName(name);
                pojo.setAge(age);
                pojo.setCity(city);

                empList.add(pojo);
                AdapterRecycler adapterRecycler = new AdapterRecycler(this, empList);
                recyclerView.setAdapter(adapterRecycler);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String loadJSONFromRaw() throws IOException {
        InputStream inputStream = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String jsonDataString = null;
            inputStream = getResources().openRawResource(R.raw.data);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8)
            );
            while ((jsonDataString = bufferedReader.readLine()) != null) {
                stringBuilder.append(jsonDataString);
            }

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }

        }
        return new String(stringBuilder);
    }

}
