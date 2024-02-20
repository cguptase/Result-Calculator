package com.project.resultcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class SGPAActivity extends AppCompatActivity {
    private Spinner[] spinners = new Spinner[10];
    private EditText[] editTexts = new EditText[10];
    private TextView resultTV;
    TextView nameTV, degreeTV;
    AppCompatButton enterBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sgpaactivity);

        nameTV = findViewById(R.id.nameTV);
        degreeTV = findViewById(R.id.degreeTV);
        resultTV = findViewById(R.id.resultTV);
        enterBtn = findViewById(R.id.enterBtn);

        getSupportActionBar().setTitle("SGPA Calculator");


        Intent intent = getIntent();
        String studentName = intent.getStringExtra("student_name");
        nameTV.setText(studentName);
        String degreeName = intent.getStringExtra("degree_name");
        degreeTV.setText(degreeName);

        for (int i = 0; i < 10; i++) {
            int spinnerId = getResources().getIdentifier("sub" + (i + 1) + "Grade", "id", getPackageName());
            int editTextId = getResources().getIdentifier("sub" + (i + 1) + "Credit", "id", getPackageName());

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

//            editTexts[i].addTextChangedListener(new SGPAWatcher());
        }

        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateSGPA();
            }
        });
    }

    private void calculateSGPA() {
        double totalCredits = 0.0;
        double totalPoints = 0.0;
        int count = 0;

        for (int i = 0; i < 10; i++) {
            String grade = spinners[i].getSelectedItem().toString();
            double credits = 0.0;

            String valueStr = editTexts[i].getText().toString().trim();
            if (!TextUtils.isEmpty(valueStr)) {

                try {
                    credits = Double.parseDouble(editTexts[i].getText().toString());
                } catch (NumberFormatException e) {
                    credits = 0.0;
                }
                count++;

                totalCredits += credits;
                totalPoints += calculatePoints(grade) * credits;
            }
        }
        if (count > 0) {
            double sgpa = totalPoints / totalCredits;
            resultTV.setText("SGPA: " + sgpa);
        } else {
            resultTV.setText("No values entered.");
        }


    }


    private double calculatePoints(String grade) {
        // Implement your grading system here
        // For example, if A=10, B=8, C=6, D=4, E=2, F=0
        switch (grade) {

            case "O":
                return 10.0;
            case "A+":
                return 9.0;
            case "A":
                return 8.0;
            case "B+":
                return 7.0;
            case "B":
                return 6.0;
            case "C":
                return 5.0;
            default:
                return 0.0;
        }
    }

//    private class SGPAWatcher implements android.text.TextWatcher {
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//        }
//
//        @Override
//        public void afterTextChanged(android.text.Editable s) {
//            // Calculate SGPA when credits are changed
//            calculateSGPA();
//        }
//    }
}