package com.example.emicalculator_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView tvEmiResult, tvPrincipalAmount, tvInterestRate, tvTenure, tvPaymentFrequency;
    private Button btnStartOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Initialize views
        tvEmiResult = findViewById(R.id.tvEmiResult);
        tvPrincipalAmount = findViewById(R.id.tvPrincipalAmount);
        tvInterestRate = findViewById(R.id.tvInterestRate);
        tvTenure = findViewById(R.id.tvTenure);
        tvPaymentFrequency = findViewById(R.id.tvPaymentFrequency);
        btnStartOver = findViewById(R.id.btnStartOver); // Initialize button

        // Get data from Intent
        String emi = getIntent().getStringExtra("EMI");
        String principal = getIntent().getStringExtra("PRINCIPAL");
        String interestRate = getIntent().getStringExtra("INTEREST_RATE");
        String tenure = getIntent().getStringExtra("TENURE");
        String paymentFrequency = getIntent().getStringExtra("PAYMENT_FREQUENCY");

        // Set data to views
        tvEmiResult.setText("$" + emi + " / Monthly");
        tvPrincipalAmount.setText("Principal: $" + principal);
        tvInterestRate.setText("Interest Rate: " + interestRate);
        tvTenure.setText("Tenure: " + tenure);
        tvPaymentFrequency.setText("Payment Frequency: " + paymentFrequency);

        // Set click listener for the Start Over button
        btnStartOver.setOnClickListener(v -> {
            // Navigate back to MainActivity
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Clears the back stack
            startActivity(intent);
            finish(); // Close the current activity
        });
    }
}
