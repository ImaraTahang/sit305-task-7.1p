package com.example.lostfoundapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.FormatFlagsConversionMismatchException;
import java.util.List;

public class AddActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioLost, radioFound;
    private EditText editTextName, editTextPhone, editTextDescription, editTextDate, editTextLocation;
    private Button saveButton;

    private List<LostFoundItem> lostFoundItemList;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);

        radioGroup = findViewById(R.id.radioGroup);
        radioLost = findViewById(R.id.radioLost);
        radioFound = findViewById(R.id.radioFound);
        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextDate = findViewById(R.id.editTextDate);
        editTextLocation = findViewById(R.id.editTextLocation);
        saveButton = findViewById(R.id.saveButton);

        sharedPreferences=getSharedPreferences("LostFoundPrefs", MODE_PRIVATE);
        gson = new Gson();

        String json = sharedPreferences.getString("lostFoundList", null);
        Type type=new TypeToken<ArrayList<LostFoundItem>>(){}.getType();
        lostFoundItemList = json != null ? gson.fromJson(json, type) : new ArrayList<>();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String postType=radioLost.isChecked()?"Lost":"Found";
                String name = editTextName.getText().toString();
                String phone = editTextPhone.getText().toString();
                String description = editTextDescription.getText().toString();
                String date = editTextDate.getText().toString();
                String location = editTextLocation.getText().toString();

                LostFoundItem item=new LostFoundItem(postType, name, phone, description, date, location);
                lostFoundItemList.add(item);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String updatedJson = gson.toJson(lostFoundItemList);
                editor.putString("lostFoundList", updatedJson);
                editor.apply();

                Intent intent = new Intent(AddActivity.this, ViewActivity.class);
                intent.putExtra("POST_TYPE", postType);
                intent.putExtra("NAME", name);
                intent.putExtra("PHONE", phone);
                intent.putExtra("DESCRIPTION", description);
                intent.putExtra("DATE", date);
                intent.putExtra("LOCATION", location);

                startActivity(intent);
            }
        });
    }
}