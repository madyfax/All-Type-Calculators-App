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

public class SimpleCompoundActivity extends AppCompatActivity {

    private EditText amountEditText;
    private EditText interestRateEditText;
    private EditText savingTermEditText;
    private RadioGroup depositFrequencyRadioGroup;
    private RadioGroup compoundFrequencyRadioGroup;
    private TextView investmentAmountTextView;
    private TextView totalInterestValueTextView;
    private TextView maturityValueTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_compound);

        amountEditText = findViewById(R.id.amountEditText);
        interestRateEditText = findViewById(R.id.interestRateEditText);
        savingTermEditText = findViewById(R.id.savingTermEditText);
        depositFrequencyRadioGroup = findViewById(R.id.depositFrequencyRadioGroup);
        compoundFrequencyRadioGroup = findViewById(R.id.compoundFrequencyRadioGroup);
        investmentAmountTextView = findViewById(R.id.investmentAmountTextView);
        totalInterestValueTextView = findViewById(R.id.totalInterestValueTextView);
        maturityValueTextView = findViewById(R.id.maturityValueTextView);

        Button calculateButton = findViewById(R.id.calculateButton);
        Button resetButton = findViewById(R.id.resetButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateInterest();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetCalculator();
            }
        });
    }

    private void calculateInterest() {
        String amountStr = amountEditText.getText().toString().trim();
        String interestRateStr = interestRateEditText.getText().toString().trim();
        String savingTermStr = savingTermEditText.getText().toString().trim();

        if (amountStr.isEmpty() || interestRateStr.isEmpty() || savingTermStr.isEmpty()) {
            Toast.makeText(this, "Please enter all the required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double amount = Double.parseDouble(amountStr);
        double interestRate = Double.parseDouble(interestRateStr) / 100.0;
        double savingTerm = Double.parseDouble(savingTermStr);

        int selectedDepositFrequencyId = depositFrequencyRadioGroup.getCheckedRadioButtonId();
        int selectedCompoundFrequencyId = compoundFrequencyRadioGroup.getCheckedRadioButtonId();

        if (amount <= 0 || interestRate <= 0 || savingTerm <= 0) {
            Toast.makeText(this, "Invalid input values. Please enter positive numbers.", Toast.LENGTH_SHORT).show();
            return;
        }

        double investmentAmount = 0;
        double totalInterestValue = 0;
        double maturityValue = 0;

        // Implement the calculations for different scenarios based on the selected options
        // You can extend the code to include other combinations of deposit and compound frequencies
        switch (selectedDepositFrequencyId) {
            case R.id.yearlyRadioButton:
                switch (selectedCompoundFrequencyId) {
                    case R.id.yearlyCompRadioButton:
                        // Calculate interest and maturity for yearly deposit and yearly compound frequency
                        // Your logic for yearly deposit and yearly compound frequency
                        break;
                    case R.id.monthlyCompRadioButton:
                        // Calculate interest and maturity for yearly deposit and monthly compound frequency
                        // Your logic for yearly deposit and monthly compound frequency
                        break;
                    // Add cases for other combinations of deposit and compound frequencies
                }
                break;
            case R.id.monthlyRadioButton:
                switch (selectedCompoundFrequencyId) {
                    case R.id.yearlyCompRadioButton:
                        // Calculate interest and maturity for monthly deposit and yearly compound frequency
                        // Your logic for monthly deposit and yearly compound frequency
                        break;
                    case R.id.monthlyCompRadioButton:
                        // Calculate interest and maturity for monthly deposit and monthly compound frequency
                        // Your logic for monthly deposit and monthly compound frequency
                        break;
                    // Add cases for other combinations of deposit and compound frequencies
                }
                break;
            // Add cases for other deposit frequencies
        }

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        // Display the results
        investmentAmountTextView.setText("Investment Amount: " + decimalFormat.format(investmentAmount));
        totalInterestValueTextView.setText("Total Interest Value: " + decimalFormat.format(totalInterestValue));
        maturityValueTextView.setText("Maturity Value: " + decimalFormat.format(maturityValue));
    }

    private void resetCalculator() {
        amountEditText.setText("");
        interestRateEditText.setText("");
        savingTermEditText.setText("");
        depositFrequencyRadioGroup.clearCheck();
        compoundFrequencyRadioGroup.clearCheck();
        investmentAmountTextView.setText("Investment Amount: ");
        totalInterestValueTextView.setText("Total Interest Value: ");
        maturityValueTextView.setText("Maturity Value: ");
    }
}



