package com.finalcalculator.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class PPF_Calculator_Activity extends AppCompatActivity {

    private EditText amountEditText;
    private EditText interestRateEditText;
    private EditText tenureEditText;
    private RadioGroup modeRadioGroup;
    private EditText maturityDurationEditText;
    private TextView investmentAmountTextView;
    private TextView totalInterestTextView;
    private TextView maturityValueTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ppf_calculator);

        amountEditText = findViewById(R.id.amountEditText);
        interestRateEditText = findViewById(R.id.interestRateEditText);
        tenureEditText = findViewById(R.id.tenureEditText);
        modeRadioGroup = findViewById(R.id.modeRadioGroup);
        maturityDurationEditText = findViewById(R.id.maturityDurationEditText);
        investmentAmountTextView = findViewById(R.id.investmentAmountTextView);
        totalInterestTextView = findViewById(R.id.totalInterestTextView);
        maturityValueTextView = findViewById(R.id.maturityValueTextView);

        Button calculateButton = findViewById(R.id.calculateButton);
        Button resetButton = findViewById(R.id.resetButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatePPFValues();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetCalculator();
            }
        });
    }

    private void calculatePPFValues() {
        String amountStr = amountEditText.getText().toString().trim();
        String interestRateStr = interestRateEditText.getText().toString().trim();
        String tenureStr = tenureEditText.getText().toString().trim();
        String maturityDurationStr = maturityDurationEditText.getText().toString().trim();

        if (amountStr.isEmpty() || interestRateStr.isEmpty() || tenureStr.isEmpty() || maturityDurationStr.isEmpty()) {
            Toast.makeText(this, "Please enter all the required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double amount = Double.parseDouble(amountStr);
        double interestRate = Double.parseDouble(interestRateStr);
        int tenure = Integer.parseInt(tenureStr);
        int maturityDuration = Integer.parseInt(maturityDurationStr);

        // Validate input values
        if (amount <= 0 || interestRate <= 0 || interestRate > 50 || tenure <= 0 || maturityDuration <= 0) {
            Toast.makeText(this, "Invalid input values. Please enter valid data.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Calculate investment amount based on the mode
        RadioButton selectedModeRadioButton = findViewById(modeRadioGroup.getCheckedRadioButtonId());
        String selectedMode = selectedModeRadioButton.getText().toString();
        double investmentAmount = calculateInvestmentAmount(amount, interestRate, tenure, selectedMode, maturityDuration);

        // Calculate total interest value
        double totalInterestValue = calculateTotalInterestValue(amount, interestRate, tenure, selectedMode, maturityDuration);

        // Calculate maturity value
        double maturityValue = amount + totalInterestValue;

        // Display the results
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        investmentAmountTextView.setText("Investment Amount: " + decimalFormat.format(investmentAmount));
        totalInterestTextView.setText("Total Interest Value: " + decimalFormat.format(totalInterestValue));
        maturityValueTextView.setText("Maturity Value: " + decimalFormat.format(maturityValue));
    }

    private double calculateInvestmentAmount(double amount, double interestRate, int tenure, String mode, int maturityDuration) {
        // Calculate investment amount based on the mode and maturity duration
        if (mode.equals("Fixed Yearly Amount")) {
            // For Fixed Yearly Amount PPF, calculate the investment amount as (Total Amount / Tenure)
            return amount / tenure;
        } else if (mode.equals("Fixed Monthly Amount")) {
            // For Fixed Monthly Amount PPF, calculate the investment amount as (Total Amount / (Tenure * 12))
            return amount / (tenure * 12);
        }
        return amount;
    }

    private double calculateTotalInterestValue(double amount, double interestRate, int tenure, String mode, int maturityDuration) {
        // Calculate total interest value based on the mode and maturity duration
        double totalInterest = 0;
        double monthlyInterestRate = interestRate / 1200;

        if (mode.equals("Fixed Yearly Amount")) {
            // For Fixed Yearly Amount PPF, calculate the yearly interest and multiply it by the tenure
            totalInterest = amount * monthlyInterestRate * 12 * tenure;
        } else if (mode.equals("Fixed Monthly Amount")) {
            // For Fixed Monthly Amount PPF, calculate the monthly interest and multiply it by (tenure * 12)
            totalInterest = amount * monthlyInterestRate * (tenure * 12);
        }

        return totalInterest;
    }

    private void resetCalculator() {
        amountEditText.setText("");
        interestRateEditText.setText("");
        tenureEditText.setText("");
        modeRadioGroup.check(R.id.radioFixedYearly);
        maturityDurationEditText.setText("");
        investmentAmountTextView.setText("Investment Amount: ");
        totalInterestTextView.setText("Total Interest Value: ");
        maturityValueTextView.setText("Maturity Value: ");
    }
}





