package com.example.lostfoundapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity implements LostFoundItemAdapter.OnItemRemovedListener {

    private RecyclerView recyclerView;
    private LostFoundItemAdapter adapter;
    private List<LostFoundItem> lostFoundItemList;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        sharedPreferences = getSharedPreferences("LostFoundPrefs", MODE_PRIVATE);
        gson = new Gson();

        lostFoundItemList=new ArrayList<>();

        String json = sharedPreferences.getString("lostFoundList", null);
        Type type = new TypeToken<ArrayList<LostFoundItem>>() {}.getType();
        List<LostFoundItem> savedList = gson.fromJson(json, type);
        if (savedList != null) {
            lostFoundItemList.addAll(savedList);
        }

        adapter = new LostFoundItemAdapter(lostFoundItemList, this);
        recyclerView.setAdapter(adapter);

    }
    public void onItemRemoved(LostFoundItem item) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        String updatedJson = gson.toJson(lostFoundItemList);
        editor.putString("lostFoundList", updatedJson);
        editor.apply();
    }
}