package com.example.voluntrix_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import activity.MainActivity;


public class OnBoardingActivity extends AppCompatActivity {

    String[] category =  {"Volunteer","Organiser"};
    String[] level =  {"Beginner","Intermediate","Advance"};
    String[] location =  {"New Delhi","Gurgaon","Noida","Others"};
    String[] gender =  {"Male","Female","Others"};
    AutoCompleteTextView autoCompleteTxtCategory;
    AutoCompleteTextView autoCompleteTxtLevel;
    AutoCompleteTextView autoCompleteTxtLocation;
    AutoCompleteTextView autoCompleteTxtGender;
    ArrayAdapter<String> adapterItemsCategory;
    ArrayAdapter<String> adapterItemsLevel;
    ArrayAdapter<String> adapterItemsLocation;
    ArrayAdapter<String> adapterItemsGender;

    Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        autoCompleteTxtCategory = findViewById(R.id.auto_complete_txt_category);

        adapterItemsCategory = new ArrayAdapter<String>(this,R.layout.list_item,category);
        autoCompleteTxtCategory.setAdapter(adapterItemsCategory);

        autoCompleteTxtCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item, Toast.LENGTH_SHORT).show();

            }

        });

        autoCompleteTxtLevel = findViewById(R.id.auto_complete_txt_level);

        adapterItemsLevel = new ArrayAdapter<String>(this,R.layout.list_item,level);
        autoCompleteTxtLevel.setAdapter(adapterItemsLevel);

        autoCompleteTxtLevel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item, Toast.LENGTH_SHORT).show();

            }

        });

        autoCompleteTxtLocation = findViewById(R.id.auto_complete_txt_location);

        adapterItemsLocation = new ArrayAdapter<String>(this,R.layout.list_item,location);
        autoCompleteTxtLocation.setAdapter(adapterItemsLocation);

        autoCompleteTxtLocation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item, Toast.LENGTH_SHORT).show();

            }

        });

        autoCompleteTxtGender = findViewById(R.id.auto_complete_txt_gender);

        adapterItemsGender = new ArrayAdapter<String>(this,R.layout.list_item,gender);
        autoCompleteTxtGender.setAdapter(adapterItemsGender);

        autoCompleteTxtGender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item, Toast.LENGTH_SHORT).show();

            }

        });

        btnDone=findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform the action when the button is clicked
                Intent intent = new Intent(OnBoardingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}