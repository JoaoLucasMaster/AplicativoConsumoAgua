package com.example.aplicativoagua;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.aplicativoagua.ResultActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout weightInputLayout;
    private TextInputLayout ageInputLayout;
    private SharedPreferences sharedPreferences;

    private Button reminder;

    private Button alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightInputLayout = findViewById(R.id.weightInputLayout);
        ageInputLayout = findViewById(R.id.ageInputLayout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

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