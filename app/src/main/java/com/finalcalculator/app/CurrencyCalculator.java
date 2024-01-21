package com.finalcalculator.app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class CurrencyCalculator extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText inputAmount;
    private Spinner fromCurrencySpinner;
    private Spinner toCurrencySpinner;
    private Button convertButton;
    private TextView resultTextView;

    private static final String API_BASE_URL = "https://app.currencyapi.com/";
    private RequestQueue requestQueue;
    private DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currencyconvertor);

        inputAmount = findViewById(R.id.inputAmount);
        fromCurrencySpinner = findViewById(R.id.fromCurrencySpinner);
        toCurrencySpinner = findViewById(R.id.toCurrencySpinner);
        convertButton = findViewById(R.id.convertButton);
        resultTextView = findViewById(R.id.resultTextView);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currencies_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fromCurrencySpinner.setAdapter(adapter);
        toCurrencySpinner.setAdapter(adapter);

        fromCurrencySpinner.setOnItemSelectedListener(this);
        toCurrencySpinner.setOnItemSelectedListener(this);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertCurrency();
            }
        });

        requestQueue = Volley.newRequestQueue(this);
        decimalFormat = new DecimalFormat("#.##");
    }

    private void convertCurrency() {
        String amountText = inputAmount.getText().toString().trim();
        if (amountText.isEmpty()) {
            Toast.makeText(this, "Please enter an amount", Toast.LENGTH_SHORT).show();
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountText);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid amount format", Toast.LENGTH_SHORT).show();
            return;
        }

        String fromCurrency = fromCurrencySpinner.getSelectedItem().toString();
        String toCurrency = toCurrencySpinner.getSelectedItem().toString();

        String apiUrl = API_BASE_URL + "?base=" + fromCurrency + "&symbols=" + toCurrency;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, apiUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.has("error")) {
                                String error = response.getString("error");
                                Toast.makeText(CurrencyCalculator.this, "API Error: " + error, Toast.LENGTH_SHORT).show();
                                return;
                            }

                            if (response.has("rates")) {
                                JSONObject rates = response.getJSONObject("rates");
                                if (rates.has(toCurrency)) {
                                    double rate = rates.getDouble(toCurrency);
                                    double convertedAmount = amount * rate;
                                    String formattedResult = decimalFormat.format(convertedAmount);
                                    resultTextView.setText(formattedResult);
                                } else {
                                    Toast.makeText(CurrencyCalculator.this, "Invalid currency", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(CurrencyCalculator.this, "Invalid response", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(CurrencyCalculator.this, "Error parsing response", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("CurrencyConverter", "API request failed: " + error.toString());
                        Toast.makeText(CurrencyCalculator.this, "Failed to fetch exchange rates", Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(request);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Handle spinner item selection if needed
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Handle no item selected if needed
    }


}
