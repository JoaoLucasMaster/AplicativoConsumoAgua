package com.example.aplicativoagua;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ResultActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        TextView resultTextView = findViewById(R.id.resultTextView);

        // Recupere o valor do consumo de água passado pela MainActivity
        int waterIntake = getIntent().getIntExtra("waterIntake", 0);

        // Recupere o último valor de consumo de água calculado do SharedPreferences
        int lastWaterIntake = sharedPreferences.getInt("lastWaterIntake", 0);

        resultTextView.setText("Quantidade de água calculada: " + waterIntake + " ml\nÚltimo valor calculado: " + lastWaterIntake + " ml");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}