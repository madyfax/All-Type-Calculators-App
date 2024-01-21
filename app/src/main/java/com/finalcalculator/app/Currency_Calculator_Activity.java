package com.finalcalculator.app;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.HashMap;

public class Currency_Calculator_Activity extends AppCompatActivity {

    private EditText amountEditText;
    private Spinner fromCountrySpinner;
    private Spinner toCountrySpinner;
    private TextView conversionRateTextView;
    private TextView dateTextView;
    private TextView resultTextView;

    // Sample conversion rates
    private HashMap<String, Double> conversionRates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_calculator);

        amountEditText = findViewById(R.id.amount_edit_text);
        fromCountrySpinner = findViewById(R.id.from_country_spinner);
        toCountrySpinner = findViewById(R.id.to_country_spinner);
        conversionRateTextView = findViewById(R.id.conversion_rate_text_view);
        dateTextView = findViewById(R.id.date_text_view);
        resultTextView = findViewById(R.id.result_text_view);

        Button calculateButton = findViewById(R.id.calculate_button);
        Button resetButton = findViewById(R.id.reset_button);

        // Set up spinner adapters
        ArrayAdapter<CharSequence> countryAdapter = ArrayAdapter.createFromResource(this, R.array.currencies_array, android.R.layout.simple_spinner_item);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromCountrySpinner.setAdapter(countryAdapter);
        toCountrySpinner.setAdapter(countryAdapter);

        // Initialize conversion rates
        initializeConversionRates();

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateConversion();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetCalculator();
            }
        });
    }

    private void initializeConversionRates() {
        // Initialize the conversion rates hashmap
        conversionRates = new HashMap<>();
        conversionRates.put("USD to EUR", 0.85);
        conversionRates.put("USD to GBP", 0.72);
        conversionRates.put("USD to JPY", 110.20);
        conversionRates.put("USD to AUD", 1.37);
        conversionRates.put("USD to CAD", 1.26);
        conversionRates.put("USD to INR", 74.56);
        conversionRates.put("USD to EUR", 0.84);
        conversionRates.put("USD to GBP", 0.73);
        conversionRates.put("USD to JPY", 110.57);
        conversionRates.put("USD to AUD", 1.37);
        conversionRates.put("USD to CAD", 1.26);
        conversionRates.put("USD to INR", 74.56);
        conversionRates.put("EUR to USD", 1.19);
        conversionRates.put("EUR to GBP", 0.86);
        conversionRates.put("EUR to JPY", 130.77);
        conversionRates.put("EUR to AUD", 1.62);
        conversionRates.put("EUR to CAD", 1.49);
        conversionRates.put("EUR to INR", 88.43);
        conversionRates.put("GBP to USD", 1.38);
        conversionRates.put("GBP to EUR", 1.16);
        conversionRates.put("GBP to JPY", 151.32);
        conversionRates.put("GBP to AUD", 1.88);
        conversionRates.put("GBP to CAD", 1.73);
        conversionRates.put("GBP to INR", 102.47);
        conversionRates.put("JPY to USD", 0.0091);
        conversionRates.put("JPY to EUR", 0.0076);
        conversionRates.put("JPY to GBP", 0.0066);
        conversionRates.put("JPY to AUD", 0.012);
        conversionRates.put("JPY to CAD", 0.011);
        conversionRates.put("JPY to INR", 0.65);
        conversionRates.put("AUD to USD", 0.73);
        conversionRates.put("AUD to EUR", 0.62);
        conversionRates.put("AUD to GBP", 0.53);
        conversionRates.put("AUD to JPY", 82.39);
        conversionRates.put("AUD to CAD", 0.92);
        conversionRates.put("AUD to INR", 54.52);
        conversionRates.put("CAD to USD", 0.79);
        conversionRates.put("CAD to EUR", 0.67);
        conversionRates.put("CAD to GBP", 0.58);
        conversionRates.put("CAD to JPY", 90.15);
        conversionRates.put("CAD to AUD", 1.08);
        conversionRates.put("CAD to INR", 59.86);
        conversionRates.put("INR to USD", 0.013);
        conversionRates.put("INR to EUR", 0.011);
        conversionRates.put("INR to GBP", 0.0098);
        conversionRates.put("INR to JPY", 1.53);
        conversionRates.put("INR to AUD", 0.018);
        conversionRates.put("INR to CAD", 0.017);
        // Add more conversion rates as needed
    }

    private void calculateConversion() {
        String amountStr = amountEditText.getText().toString().trim();
        String fromCountry = fromCountrySpinner.getSelectedItem().toString();
        String toCountry = toCountrySpinner.getSelectedItem().toString();

        if (amountStr.isEmpty()) {
            Toast.makeText(this, "Please enter an amount", Toast.LENGTH_SHORT).show();
            return;
        }

        double amount = Double.parseDouble(amountStr);

        // Validate input values
        if (amount <= 0) {
            Toast.makeText(this, "Invalid amount. Please enter a positive number.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get the conversion rate based on the selected countries
        String conversionKey = fromCountry + " to " + toCountry;
        double conversionRate = conversionRates.containsKey(conversionKey) ? conversionRates.get(conversionKey) : 1.0;

        // Perform currency conversion
        double result = amount * conversionRate;

        // Format the result with 2 decimal places
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedResult = decimalFormat.format(result);

        // Display the conversion details
        conversionRateTextView.setText("Conversion Rate: " + conversionRate);
        dateTextView.setText("Date: " + getCurrentDate());
        resultTextView.setText("Result: " + formattedResult + " " + toCountry);
    }

    private void resetCalculator() {
        amountEditText.setText("");
        fromCountrySpinner.setSelection(0);
        toCountrySpinner.setSelection(0);
        conversionRateTextView.setText("Conversion Rate: ");
        dateTextView.setText("Date: ");
        resultTextView.setText("Result: ");
    }

    private String getCurrentDate() {
        // Implement your logic to get the current date here
        return "2022-01-01";
    }
}





