package com.example.emicalculator_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText etPrincipal;
    private Spinner spinnerInterestRate, spinnerTenure;
    private Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        etPrincipal = findViewById(R.id.etPrincipal);
        spinnerInterestRate = findViewById(R.id.spinnerInterestRate);
        spinnerTenure = findViewById(R.id.spinnerTenure);
        btnCalculate = findViewById(R.id.btnCalculate);

        // Set up Interest Rate Spinner
        String[] interestRates = {
                "3 Year Fixed Rate 4.84%",
                "5 Year Fixed Rate 4.74%",
                "1 Year Closed 7.34%",
                "5 Year Closed Variable Rate 6.14%",
                "5 Year Open Variable Rate 7.6%"
        };
        ArrayAdapter<String> interestRateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, interestRates);
        spinnerInterestRate.setAdapter(interestRateAdapter);

        // Set up Tenure Spinner (Years)
        String[] tenures = {"10 years", "15 years", "20 years", "25 years", "30 years"};
        ArrayAdapter<String> tenureAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, tenures);
        spinnerTenure.setAdapter(tenureAdapter);

        // Set click listener for the calculate button
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateEMI();
            }
        });
    }

    private void calculateEMI() {
        // Get input values
        String principalStr = etPrincipal.getText().toString();

        // Validate the principal input
        if (principalStr.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please enter a principal amount.", Toast.LENGTH_SHORT).show();
            return;
        }

        String selectedInterestRate = spinnerInterestRate.getSelectedItem().toString();
        String selectedTenure = spinnerTenure.getSelectedItem().toString();

        // Extract numeric values from the selection strings
        double interestRate = Double.parseDouble(selectedInterestRate.replaceAll("[^0-9.]", ""));
        int tenureYears = Integer.parseInt(selectedTenure.replaceAll("[^0-9]", ""));
        double principal = Double.parseDouble(principalStr);

        // Convert annual interest rate to monthly and tenure to months
        double monthlyInterestRate = interestRate / 12 / 100;
        int tenureMonths = tenureYears * 12;

        // Calculate EMI
        double emi = (principal * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, tenureMonths)) /
                (Math.pow(1 + monthlyInterestRate, tenureMonths) - 1);

        // Format EMI
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String emiFormatted = decimalFormat.format(emi);

        // Pass data to ResultActivity
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra("EMI", emiFormatted);
        intent.putExtra("PRINCIPAL", principalStr);
        intent.putExtra("INTEREST_RATE", selectedInterestRate);
        intent.putExtra("TENURE", selectedTenure);
        intent.putExtra("PAYMENT_FREQUENCY", "Monthly"); // For simplicity, hardcoding as Monthly
        startActivity(intent);
    }
}
