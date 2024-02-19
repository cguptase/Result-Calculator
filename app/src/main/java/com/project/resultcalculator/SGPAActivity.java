package com.project.resultcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class SGPAActivity extends AppCompatActivity {

    private Spinner gradeSpinner1, gradeSpinner2, gradeSpinner3,
            gradeSpinner4, gradeSpinner5, gradeSpinner6,
            gradeSpinner7, gradeSpinner8, gradeSpinner9, gradeSpinner10;
    private EditText creditEditText1, creditEditText2, creditEditText3,
            creditEditText4, creditEditText5, creditEditText6,
            creditEditText7, creditEditText8, creditEditText9, creditEditText10;
    private TextView sgpaTextView;
    AppCompatButton calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sgpaactivity);

        gradeSpinner1 = findViewById(R.id.sub1Grade);
        gradeSpinner2 = findViewById(R.id.sub2Grade);
        gradeSpinner3 = findViewById(R.id.sub3Grade);
        gradeSpinner4 = findViewById(R.id.sub4Grade);
        gradeSpinner5 = findViewById(R.id.sub5Grade);
        gradeSpinner6 = findViewById(R.id.sub6Grade);
        gradeSpinner7 = findViewById(R.id.sub7Grade);
        gradeSpinner8 = findViewById(R.id.sub8Grade);
        gradeSpinner9 = findViewById(R.id.sub9Grade);
        gradeSpinner10 = findViewById(R.id.sub10Grade);

        creditEditText1 = findViewById(R.id.sub1Credit);
        creditEditText2 = findViewById(R.id.sub2Credit);
        creditEditText3 = findViewById(R.id.sub3Credit);
        creditEditText4 = findViewById(R.id.sub4Credit);
        creditEditText5 = findViewById(R.id.sub5Credit);
        creditEditText6 = findViewById(R.id.sub6Credit);
        creditEditText7 = findViewById(R.id.sub7Credit);
        creditEditText8 = findViewById(R.id.sub8Credit);
        creditEditText9 = findViewById(R.id.sub9Credit);
        creditEditText10 = findViewById(R.id.sub10Credit);

        sgpaTextView = findViewById(R.id.resultTV);
        calculateButton = findViewById(R.id.enterBtn);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.grades_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        gradeSpinner1.setAdapter(adapter);
        gradeSpinner2.setAdapter(adapter);
        gradeSpinner3.setAdapter(adapter);
        gradeSpinner4.setAdapter(adapter);
        gradeSpinner5.setAdapter(adapter);
        gradeSpinner6.setAdapter(adapter);
        gradeSpinner7.setAdapter(adapter);
        gradeSpinner8.setAdapter(adapter);
        gradeSpinner9.setAdapter(adapter);
        gradeSpinner10.setAdapter(adapter);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateSGPA();
            }
        });
    }

    private void calculateSGPA() {

        if (creditEditText1.getText().toString().equals("") || creditEditText2.getText().toString().equals("") || creditEditText3.getText().toString().equals("")) {
            sgpaTextView.setText("Some Values are Missing.");
            return;
        } else {

            int credits1 = Integer.parseInt(creditEditText1.getText().toString());
            int credits2 = Integer.parseInt(creditEditText2.getText().toString());
            int credits3 = Integer.parseInt(creditEditText3.getText().toString());
            int credits4 = Integer.parseInt(creditEditText4.getText().toString());
            int credits5 = Integer.parseInt(creditEditText5.getText().toString());
            int credits6 = Integer.parseInt(creditEditText6.getText().toString());
            int credits7 = Integer.parseInt(creditEditText7.getText().toString());
            int credits8 = Integer.parseInt(creditEditText8.getText().toString());
            int credits9 = Integer.parseInt(creditEditText9.getText().toString());
            int credits10 = Integer.parseInt(creditEditText10.getText().toString());

            String grade1 = gradeSpinner1.getSelectedItem().toString();
            String grade2 = gradeSpinner2.getSelectedItem().toString();
            String grade3 = gradeSpinner3.getSelectedItem().toString();
            String grade4 = gradeSpinner4.getSelectedItem().toString();
            String grade5 = gradeSpinner5.getSelectedItem().toString();
            String grade6 = gradeSpinner6.getSelectedItem().toString();
            String grade7 = gradeSpinner7.getSelectedItem().toString();
            String grade8 = gradeSpinner8.getSelectedItem().toString();
            String grade9 = gradeSpinner8.getSelectedItem().toString();
            String grade10 = gradeSpinner8.getSelectedItem().toString();

            double gradePoint1 = getGradePoint(grade1);
            double gradePoint2 = getGradePoint(grade2);
            double gradePoint3 = getGradePoint(grade3);
            double gradePoint4 = getGradePoint(grade4);
            double gradePoint5 = getGradePoint(grade5);
            double gradePoint6 = getGradePoint(grade6);
            double gradePoint7 = getGradePoint(grade7);
            double gradePoint8 = getGradePoint(grade8);
            double gradePoint9 = getGradePoint(grade8);
            double gradePoint10 = getGradePoint(grade8);

            double totalCredits = credits1 + credits2 + credits3;
            double sgpa = ((credits1 * gradePoint1) + (credits2 * gradePoint2) + (credits3 * gradePoint3) + (credits4 * gradePoint4)
                    + (credits5 * gradePoint5) + (credits6 * gradePoint6) + (credits7 * gradePoint7) + (credits8 * gradePoint8)
                    + (credits9 * gradePoint9) + (credits10 * gradePoint10)) / totalCredits;


            sgpaTextView.setText("SGPA: " + sgpa);
        }
    }

    private double getGradePoint(String grade) {
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
}