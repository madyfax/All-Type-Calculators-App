package com.finalcalculator.app;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class Loan_Tenure_Calculator extends AppCompatActivity {

        private EditText principleAmountEditText, emiEditText, interestRateEditText;
        private Button calculateButton, resetButton;
        private TextView totalInterestTextView, totalPrincipleTextView, totalPaymentTextView, loanTenureTextView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activtiy_loan_tenure_calculator);

            principleAmountEditText = findViewById(R.id.principle_amount_edit_text);
            emiEditText = findViewById(R.id.emi_edit_text);
            interestRateEditText = findViewById(R.id.interest_rate_edit_text);
            calculateButton = findViewById(R.id.calculate_button);
            resetButton = findViewById(R.id.reset_button);
            totalInterestTextView = findViewById(R.id.total_interest_text_view);
            totalPrincipleTextView = findViewById(R.id.total_principle_text_view);
            totalPaymentTextView = findViewById(R.id.total_payment_text_view);
            loanTenureTextView = findViewById(R.id.loan_tenure_text_view);

            calculateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calculateLoanTenure();
                }
            });

            resetButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resetCalculator();
                }
            });
        }

        private void calculateLoanTenure() {
            String principleAmountStr = principleAmountEditText.getText().toString();
            String emiStr = emiEditText.getText().toString();
            String interestRateStr = interestRateEditText.getText().toString();

            if (TextUtils.isEmpty(principleAmountStr) || TextUtils.isEmpty(emiStr) || TextUtils.isEmpty(interestRateStr)) {
                Toast.makeText(this, "Please enter all values", Toast.LENGTH_SHORT).show();
                return;
            }

            float principleAmount = Float.parseFloat(principleAmountStr);
            float emi = Float.parseFloat(emiStr);
            float interestRate = Float.parseFloat(interestRateStr);

            float payableInterestPercent = (emi / principleAmount) * 100.0f;
            float payablePrinciplePercent = ((emi * (12 * interestRate)) / (principleAmount * 100.0f)) * 100.0f;

            float monthlyInterestRate = interestRate / (12 * 100); // Monthly interest rate
            float numerator = emi - (principleAmount * monthlyInterestRate);
            float denominator = principleAmount * monthlyInterestRate;

            float loanTenure = (float) (Math.log(numerator / denominator) / Math.log(1 + monthlyInterestRate));
            int loanTenureMonths = (int) Math.ceil(loanTenure);

            float totalInterest = (emi * loanTenureMonths) - principleAmount;
            float totalPrinciple = principleAmount;
            float totalPayment = totalPrinciple + totalInterest;

            DecimalFormat decimalFormat = new DecimalFormat("#,##,###.##");

            totalInterestTextView.setText("Total Interest Payable: ₹" + decimalFormat.format(totalInterest));
            totalPrincipleTextView.setText("Total Principle: ₹" + decimalFormat.format(totalPrinciple));
            totalPaymentTextView.setText("Total Payment (Principle + Interest): ₹" + decimalFormat.format(totalPayment));
            loanTenureTextView.setText("Loan Tenure (in months): " + loanTenureMonths);

        }

        private void resetCalculator() {
            principleAmountEditText.setText("");
            emiEditText.setText("");
            interestRateEditText.setText("");
            totalInterestTextView.setText("Total Interest Payable:");
            totalPrincipleTextView.setText("Total Principle:");
            totalPaymentTextView.setText("Total Payment (Principle + Interest):");
            loanTenureTextView.setText("Loan Tenure (in months):");

        }
    }
















