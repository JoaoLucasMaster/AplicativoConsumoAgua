package com.example.aplicativoagua;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout weightInputLayout;
    private TextInputLayout ageInputLayout;
    private SharedPreferences sharedPreferences;

    private Button reminder;
    private Button alarm;
    private TimePickerDialog timePicker;
    private Calendar calendar;
    private TextView hourTxt;
    private TextView minuteTxt;
    private int actualHour;
    private int actualMinute=0;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightInputLayout = findViewById(R.id.weightInputLayout);
        ageInputLayout = findViewById(R.id.ageInputLayout);
        reminder = findViewById(R.id.reminderBtn);
        alarm = findViewById(R.id.alarmBtn);
        hourTxt = findViewById(R.id.textHour);
        minuteTxt = findViewById(R.id.textMinute);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                actualHour = calendar.get(Calendar.HOUR_OF_DAY);
                actualMinute = calendar.get(Calendar.MINUTE);
                timePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minutes) {
                        hourTxt.setText(String.format("%2d", hourOfDay));
                        minuteTxt.setText(String.format("%2d", minutes));
                    }
                }, actualHour, actualMinute, true);
                timePicker.show();
            }
        });

        findViewById(R.id.calculateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateWaterIntake();
            }
        });
    }

    private void calculateWaterIntake() {
        String weightStr = weightInputLayout.getEditText().getText().toString().trim();
        String ageStr = ageInputLayout.getEditText().getText().toString().trim();

        if (weightStr.isEmpty() || ageStr.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int weight = Integer.parseInt(weightStr);
            int age = Integer.parseInt(ageStr);

            // Seu código de cálculo aqui
            int litros = weight*35;

            // Salve o último valor de consumo de água calculado no SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("lastWaterIntake", litros); // Substitua 100 pelo valor real calculado
            editor.apply();

            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("waterIntake", litros); // Substitua 100 pelo valor real calculado
            intent.putExtra("age", age);
            startActivity(intent);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Valores inválidos. Certifique-se de inserir números inteiros válidos.", Toast.LENGTH_SHORT).show();
        }
    }
}