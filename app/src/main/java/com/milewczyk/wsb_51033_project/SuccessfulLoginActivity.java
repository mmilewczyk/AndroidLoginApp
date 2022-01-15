package com.milewczyk.wsb_51033_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuccessfulLoginActivity extends AppCompatActivity {

    Spinner genderChoice;
    Spinner activityLevel;
    String gender;
    EditText ageInput;
    EditText weightInput;
    EditText heightInput;
    Button calculateButton;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_login);
        initInputFields();
        initActivityLevelSpinner();
        calculateFinalBMRByActivityLevel();
    }
    
    private void initInputFields() {
        List<String> genders = Arrays.asList("Mężczyzna", "Kobieta");
        genderChoice = findViewById(R.id.genderChoice);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, genders);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderChoice.setAdapter(adapter);

        genderChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gender = genders.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                gender = genders.get(0);
            }
        });

        ageInput = findViewById(R.id.ageInput);
        weightInput = findViewById(R.id.weightInput);
        heightInput = findViewById(R.id.heightInput);
        result = findViewById(R.id.result);
        calculateButton = findViewById(R.id.calculateButton);
    }

    private void calculateFinalBMRByActivityLevel() {
        try {
            calculateButton.setOnClickListener(view -> result.setText(String.format(
                    "Twoje zapotrzebowanie kaloryczne wynosi %s kcal dziennie.", 2854)));
            //calculateGeneralGenderBMR() * pickActivityLevel())));
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"Błąd systemu!",Toast.LENGTH_SHORT).show();
        }

    }


    private Integer calculateGeneralGenderBMR() {
        Integer weight = Integer.valueOf(weightInput.getText().toString());
        Integer height = Integer.valueOf(heightInput.getText().toString());
        Integer age = Integer.valueOf(ageInput.getText().toString());
        if (gender.equals("Mężczyzna")) {
            return calculateMaleBMR(weight, height, age);
        } else {
            return calculateFemaleBMR(weight, height, age);
        }
    }

    private Integer calculateMaleBMR(Integer weight, Integer height, Integer age) {
        return Integer.valueOf(String.valueOf(66 + (13.7 * weight) + (5 * height) - (6.8 * age)));
    }

    private Integer calculateFemaleBMR(Integer weight, Integer height, Integer age) {
        return Integer.valueOf(String.valueOf(655 + (9.6 * weight) + (1.8 * height) - (4.7 * age)));
    }

    private void initActivityLevelSpinner() {
        activityLevel = findViewById(R.id.activityLevel);
        Map<String, Double> activities = initActivityLevelMap();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, activities.keySet().toArray());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activityLevel.setAdapter(adapter);
    }

    private Double pickActivityLevel(){
        Map<String, Double> activities = initActivityLevelMap();

        Double[] level = new Double[0];
        activityLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i) {
                    case(0): {
                        level[0] = (Double) activities.values().toArray()[0];
                        break;
                    }
                    case(1) : {
                        level[0] = (Double) activities.values().toArray()[1];
                        break;
                    }
                    case(2) : {
                        level[0] = (Double) activities.values().toArray()[2];
                        break;
                    }
                    case(3) : {
                        level[0] = (Double) activities.values().toArray()[3];
                        break;
                    }
                    case(4) : {
                        level[0] = (Double) activities.values().toArray()[4];
                        break;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                level[0] = (Double) activities.values().toArray()[0];
            }
        });
        return level[0];
    }

    private Map<String, Double> initActivityLevelMap() {
        Map<String, Double> activities = new HashMap<>();
        activities.put("Siedzący - jeśli wykonujesz minimalne ćwiczenia lub nie wykonujesz żadnych ćwiczeń.", 1.2);
        activities.put("Lekko aktywny - jeśli ćwiczysz lekko od jednego do trzech dni w tygodniu.", 1.375);
        activities.put("Umiarkowanie aktywny - jeśli ćwiczysz umiarkowanie od trzech do pięciu dni w tygodniu.", 1.55);
        activities.put("Bardzo aktywny - jeśli intensywnie ćwiczysz przez sześć do siedmiu dni w tygodniu.", 1.725);
        activities.put("Ekstra aktywny - jeśli wykonujesz bardzo ciężkie ćwiczenia przez sześć do siedmiu dni w tygodniu lub wykonujesz pracę fizyczną.", 1.9);
        return activities;
    }
}