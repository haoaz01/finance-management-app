package com.example.myfinancemanagementapp;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView textName, textSalary, textNeeds, textWants, textSavings;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Ánh xạ TextView và Button
        textName = findViewById(R.id.textName);
        textSalary = findViewById(R.id.textSalary);
        textNeeds = findViewById(R.id.textNeeds);
        textWants = findViewById(R.id.textWants);
        textSavings = findViewById(R.id.textSavings);
        backButton = findViewById(R.id.backButton);

        // Lấy dữ liệu từ Intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        double salary = intent.getDoubleExtra("salary", 0);
        double needs = intent.getDoubleExtra("needs", 0);
        double wants = intent.getDoubleExtra("wants", 0);
        double savings = intent.getDoubleExtra("savings", 0);

        // Cập nhật UI
        textName.setText(name);
        textSalary.setText(String.format("$ %.0f", salary));
        textNeeds.setText(String.format("$ %.0f", needs));
        textWants.setText(String.format("$ %.0f", wants));
        textSavings.setText(String.format("$ %.0f", savings));

        // Xử lý sự kiện khi nhấn nút Back
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Quay lại MainActivity
            }
        });
    }
}
