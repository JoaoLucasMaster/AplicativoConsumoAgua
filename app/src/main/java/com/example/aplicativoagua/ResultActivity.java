package com.example.aplicativoagua;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
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
        ImageView resultImageView = findViewById(R.id.resultImageView);

        // Recupere o valor do consumo de água passado pela MainActivity
        int waterIntake = getIntent().getIntExtra("waterIntake", 0);
        int age = getIntent().getIntExtra("age",0); // Obtenha o valor da idade

        // Recupere o último valor de consumo de água calculado do SharedPreferences
        int lastWaterIntake = sharedPreferences.getInt("lastWaterIntake", 0);


        // Lógica para definir a imagem com base nas condições do consumo de água e idade
        if (waterIntake < 2000 && age >= 18) {
            resultImageView.setImageResource(R.drawable.baixo_consumo);
            resultTextView.setText("Quantidade de água calculada: " + waterIntake + " ml\nÚltimo valor calculado: " + lastWaterIntake + " ml\nBaixo consumo para adulto!");
        } else if (waterIntake < 2000 && age < 18) {
            resultImageView.setImageResource(R.drawable.baixo_consumo);
            resultTextView.setText("Quantidade de água calculada: " + waterIntake + " ml\nÚltimo valor calculado: " + lastWaterIntake + " ml\nBaixo consumo para criança!");
        } else if (waterIntake >= 2000 && waterIntake < 3000 && age >= 18) {
            resultImageView.setImageResource(R.drawable.medio_consumo);
            resultTextView.setText("Quantidade de água calculada: " + waterIntake + " ml\nÚltimo valor calculado: " + lastWaterIntake + " ml\nConsumo médio para adulto!");
        } else if (waterIntake >= 2000 && waterIntake < 3000 && age < 18) {
            resultImageView.setImageResource(R.drawable.medio_consumo);
            resultTextView.setText("Quantidade de água calculada: " + waterIntake + " ml\nÚltimo valor calculado: " + lastWaterIntake + " ml\nConsumo médio para criança!");
        } else if (waterIntake >= 3000 && age >= 18) {
            resultImageView.setImageResource(R.drawable.alto_consumo);
            resultTextView.setText("Quantidade de água calculada: " + waterIntake + " ml\nÚltimo valor calculado: " + lastWaterIntake + " ml\nAlto consumo para adulto!");
        } else {
            resultImageView.setImageResource(R.drawable.alto_consumo);
            resultTextView.setText("Quantidade de água calculada: " + waterIntake + " ml\nÚltimo valor calculado: " + lastWaterIntake + " ml\nAlto consumo para criança!");
        }
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
