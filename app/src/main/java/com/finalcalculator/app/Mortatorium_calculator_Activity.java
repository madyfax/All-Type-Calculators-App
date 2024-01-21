package com.finalcalculator.app;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class Mortatorium_calculator_Activity extends AppCompatActivity {

    private EditText loanAmountEditText;
    private EditText interestEditText;
    private EditText loanTenureEditText;
    private RadioGroup tenureRadioGroup;
    private EditText moratoriumPeriodEditText;
    private EditText noOfEmisEditText;
    private Spinner optionSpinner;
    private TextView totalInterestTextView;
    private TextView totalPrincipleTextView;
    private TextView overallTenureTextView;
    private TextView totalPaymentTextView;
    private TextView emiResultTextView;

    private double loanAmount = 0.0;
    private double interestRate = 0.0;
    private int loanTenure = 0;
    private int moratoriumPeriod = 0;
    private int noOfEmisPaid = 0;
    private String selectedOption = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mortatorium_calculator);

        loanAmountEditText = findViewById(R.id.loan_amount_edittext);
        interestEditText = findViewById(R.id.interest_edittext);
        loanTenureEditText = findViewById(R.id.loan_tenure_edittext);
        tenureRadioGroup = findViewById(R.id.tenure_radio_group);
        moratoriumPeriodEditText = findViewById(R.id.moratorium_period_edittext);
        noOfEmisEditText = findViewById(R.id.no_of_emis_edittext);
        optionSpinner = findViewById(R.id.option_spinner);
        totalInterestTextView = findViewById(R.id.total_interest_textview);
        totalPrincipleTextView = findViewById(R.id.total_principle_textview);
        overallTenureTextView = findViewById(R.id.overall_tenure_textview);
        totalPaymentTextView = findViewById(R.id.total_payment_textview);
        emiResultTextView = findViewById(R.id.emi_result_textview);

        Button calculateButton = findViewById(R.id.calculate_button);
        Button resetButton = findViewById(R.id.reset_button);

        // Set up spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tenure_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        optionSpinner.setAdapter(adapter);
        optionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedOption = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedOption = "";
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateMoratorium();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetCalculator();
            }
        });
    }

    private void calculateMoratorium() {
        // Get input values
        String loanAmountStr = loanAmountEditText.getText().toString().trim();
        String interestRateStr = interestEditText.getText().toString().trim();
        String loanTenureStr = loanTenureEditText.getText().toString().trim();
        String moratoriumPeriodStr = moratoriumPeriodEditText.getText().toString().trim();
        String noOfEmisPaidStr = noOfEmisEditText.getText().toString().trim();

        // Validate input values
        if (TextUtils.isEmpty(loanAmountStr) || TextUtils.isEmpty(interestRateStr) ||
                TextUtils.isEmpty(loanTenureStr) || TextUtils.isEmpty(moratoriumPeriodStr) ||
                TextUtils.isEmpty(noOfEmisPaidStr) || TextUtils.isEmpty(selectedOption)) {
            Toast.makeText(this, "Please enter all the required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        loanAmount = Double.parseDouble(loanAmountStr);
        interestRate = Double.parseDouble(interestRateStr);
        loanTenure = Integer.parseInt(loanTenureStr);
        moratoriumPeriod = Integer.parseInt(moratoriumPeriodStr);
        noOfEmisPaid = Integer.parseInt(noOfEmisPaidStr);

        // Validate input values
        if (loanAmount <= 0 || interestRate <= 0 || loanTenure <= 0 ||
                moratoriumPeriod < 0 || noOfEmisPaid < 0) {
            Toast.makeText(this, "Invalid input values. Please enter positive numbers.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Calculate overall tenure including moratorium period
        int overallTenure = loanTenure;
        if (selectedOption.equals("Years")) {
            overallTenure += moratoriumPeriod * 12;
        } else if (selectedOption.equals("Months")) {
            overallTenure += moratoriumPeriod;
        }

        // Calculate total interest payable
        double totalInterestPayable = (loanAmount * interestRate * overallTenure) / (100 * (selectedOption.equals("Years") ? 12 : 1));

        // Calculate total principle
        double totalPrinciple = loanAmount;

        // Calculate total payment
        double totalPayment = totalPrinciple + totalInterestPayable;

        // Calculate EMI (Monthly Payment)
        double emi = totalPayment / (overallTenure - noOfEmisPaid);

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        // Display the results
        totalInterestTextView.setText("Total Interest Payable: " + decimalFormat.format(totalInterestPayable));
        totalPrincipleTextView.setText("Total Principle: " + decimalFormat.format(totalPrinciple));
        overallTenureTextView.setText("Overall Tenure: " + overallTenure + " " + selectedOption);
        totalPaymentTextView.setText("Total Payment: " + decimalFormat.format(totalPayment));
        emiResultTextView.setText("EMI (Monthly Payment): " + decimalFormat.format(emi));
    }

    private void resetCalculator() {
        loanAmountEditText.setText("");
        interestEditText.setText("");
        loanTenureEditText.setText("");
        moratoriumPeriodEditText.setText("");
        noOfEmisEditText.setText("");
        optionSpinner.setSelection(0);
        totalInterestTextView.setText("Total Interest Payable: ");
        totalPrincipleTextView.setText("Total Principle: ");
        overallTenureTextView.setText("Overall Tenure: ");
        totalPaymentTextView.setText("Total Payment: ");
        emiResultTextView.setText("EMI (Monthly Payment): ");
    }
}
















