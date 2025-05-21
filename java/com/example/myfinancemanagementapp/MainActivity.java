package com.example.myfinancemanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputName, inputSalary, inputNeeds, inputWants, inputSavings;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các EditText và Button
        inputName = findViewById(R.id.inputName);
        inputSalary = findViewById(R.id.inputSalary);
        inputNeeds = findViewById(R.id.inputNeeds);
        inputWants = findViewById(R.id.inputWants);
        inputSavings = findViewById(R.id.inputSavings);
        calculateButton = findViewById(R.id.calculateButton);

        // Xử lý khi bấm Calculate Budget
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBudget();
            }
        });
    }

    private void calculateBudget() {
        try {
            // Kiểm tra ô nhập liệu không được để trống
            if (inputName.getText().toString().isEmpty() ||
                    inputSalary.getText().toString().isEmpty() ||
                    inputNeeds.getText().toString().isEmpty() ||
                    inputWants.getText().toString().isEmpty() ||
                    inputSavings.getText().toString().isEmpty()) {

                Toast.makeText(this, "Error: Please fill in all fields.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Lấy dữ liệu từ các EditText
            String name = inputName.getText().toString();
            double salary = Double.parseDouble(inputSalary.getText().toString());
            double needsPercent = Double.parseDouble(inputNeeds.getText().toString());
            double wantsPercent = Double.parseDouble(inputWants.getText().toString());
            double savingsPercent = Double.parseDouble(inputSavings.getText().toString());

            // Kiểm tra tổng phần trăm phải bằng 100
            if (needsPercent + wantsPercent + savingsPercent != 100) {
                Toast.makeText(this, "Error: Percentages must sum up to 100%", Toast.LENGTH_SHORT).show();
                return;
            }

            // Tính toán giá trị
            double needs = (salary * needsPercent) / 100;
            double wants = (salary * wantsPercent) / 100;
            double savings = (salary * savingsPercent) / 100;

            // Chuyển sang ResultActivity và gửi dữ liệu
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("salary", salary);
            intent.putExtra("needs", needs);
            intent.putExtra("wants", wants);
            intent.putExtra("savings", savings);
            startActivity(intent);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Error: Please enter valid numbers.", Toast.LENGTH_SHORT).show();
        }
    }
}
