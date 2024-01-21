package com.finalcalculator.app;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

public class Loan_interest_rate_Calculator extends AppCompatActivity {

        private EditText principleAmountEditText;
        private EditText emiEditText;
        private EditText loanTenureEditText;
        private RadioGroup tenureRadioGroup;
        private TextView interestRateTextView;
        private TextView totalInterestTextView;
        private TextView totalPrincipleTextView;
        private TextView totalPaymentTextView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activtiy_interest_rate_calculator);

            principleAmountEditText = findViewById(R.id.principle_amount_edittext);
            emiEditText = findViewById(R.id.emi_edittext);
            loanTenureEditText = findViewById(R.id.loan_tenure_edittext);
            tenureRadioGroup = findViewById(R.id.tenure_radio_group);
            interestRateTextView = findViewById(R.id.interest_rate_textview);
            totalInterestTextView = findViewById(R.id.total_interest_textview);
            totalPrincipleTextView = findViewById(R.id.total_principle_textview);
            totalPaymentTextView = findViewById(R.id.total_payment_textview);

            Button calculateButton = findViewById(R.id.calculate_button);
            Button resetButton = findViewById(R.id.reset_button);

            calculateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calculate();
                }
            });

            resetButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reset();
                }
            });
        }

    private void calculate() {
        if (TextUtils.isEmpty(principleAmountEditText.getText()) || TextUtils.isEmpty(emiEditText.getText())
                || TextUtils.isEmpty(loanTenureEditText.getText())) {
            Toast.makeText(this, "Please enter all the required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        float principleAmount = Float.parseFloat(principleAmountEditText.getText().toString().trim());
        float emi = Float.parseFloat(emiEditText.getText().toString().trim());
        int tenure = Integer.parseInt(loanTenureEditText.getText().toString().trim());
        boolean isYears = tenureRadioGroup.getCheckedRadioButtonId() == R.id.radio_years;

        // Validate input values
        if (principleAmount <= 0 || emi <= 0 || tenure <= 0) {
            Toast.makeText(this, "Invalid input values. Please enter positive numbers.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Calculate the interest rate
        float interestRate = (emi / principleAmount) * (isYears ? 12.0f : 1.0f) * 100.0f;

        // Calculate the total interest payable
        float totalInterestPayable = (emi * tenure) - principleAmount;

        // Calculate the total principle
        float totalPrinciple = principleAmount;

        // Calculate the total payment
        float totalPayment = principleAmount + totalInterestPayable;

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        // Display the results
        interestRateTextView.setText("Interest Rate: " + decimalFormat.format(interestRate) + "% per annum");
        totalInterestTextView.setText("Total Interest Payable: " + decimalFormat.format(totalInterestPayable));
        totalPrincipleTextView.setText("Total Principle: " + decimalFormat.format(totalPrinciple));
        totalPaymentTextView.setText("Total Payment: " + decimalFormat.format(totalPayment));
    }

    private void reset() {
        principleAmountEditText.getText().clear();
        emiEditText.getText().clear();
        loanTenureEditText.getText().clear();
        tenureRadioGroup.check(R.id.radio_years);
        interestRateTextView.setText("Interest Rate: ");
        totalInterestTextView.setText("Total Interest Payable: ");
        totalPrincipleTextView.setText("Total Principle: ");
        totalPaymentTextView.setText("Total Payment: ");
    }
    }


















