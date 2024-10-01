package com.example.emicalculator_app;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.emicalculator_app.R;

public class ResultActivity extends AppCompatActivity {

    private TextView tvEmiResult, tvPrincipalAmount, tvInterestRate, tvTenure, tvPaymentFrequency;

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
    }
}
