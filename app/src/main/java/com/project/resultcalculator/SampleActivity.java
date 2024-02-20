package com.project.resultcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class SampleActivity extends AppCompatActivity {
    private Spinner[] spinners = new Spinner[10];
    private EditText[] editTexts = new EditText[10];
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        resultTextView = findViewById(R.id.resultTextView);

        for (int i = 0; i < 10; i++) {
            int spinnerId = getResources().getIdentifier("spinner" + (i + 1), "id", getPackageName());
            int editTextId = getResources().getIdentifier("editText" + (i + 1), "id", getPackageName());

            spinners[i] = findViewById(spinnerId);
            editTexts[i] = findViewById(editTextId);

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.grades_array, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinners[i].setAdapter(adapter);

            final int position = i;
            spinners[i].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                    // Calculate SGPA when a grade is selected
                    calculateSGPA();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            editTexts[i].addTextChangedListener(new SGPAWatcher());
        }
    }

    private void calculateSGPA() {
        double totalCredits = 0.0;
        double totalPoints = 0.0;

        for (int i = 0; i < 10; i++) {
            String grade = spinners[i].getSelectedItem().toString();
            double credits = 0.0;
            try {
                credits = Double.parseDouble(editTexts[i].getText().toString());
            } catch (NumberFormatException e) {
                credits = 0.0;
            }

            totalCredits += credits;
            totalPoints += calculatePoints(grade) * credits;
        }

        double sgpa = totalPoints / totalCredits;
        resultTextView.setText(String.format("SGPA: %.2f", sgpa));
    }

    private double calculatePoints(String grade) {
        // Implement your grading system here
        // For example, if A=10, B=8, C=6, D=4, E=2, F=0
        switch (grade) {
            case "A":
                return 10.0;
            case "B":
                return 8.0;
            case "C":
                return 6.0;
            case "D":
                return 4.0;
            case "E":
                return 2.0;
            case "F":
                return 0.1;
            default:
                return 0.0;
        }
    }

    private class SGPAWatcher implements android.text.TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(android.text.Editable s) {
            // Calculate SGPA when credits are changed
            calculateSGPA();
        }
    }
}