package com.project.resultcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class IntroActivity extends AppCompatActivity {

    Spinner degreeSpinner;
    String studentName, degreeName;

    String[] degrees = {"MCA", "MSC", "MTech",
            "BCA", "BSC", "BTech", "BE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        EditText nameET = findViewById(R.id.nameET);

        degreeSpinner = findViewById(R.id.degreeSpinner);
        AppCompatButton CGPABtn = findViewById(R.id.CGPABtn);
        AppCompatButton SGPABtn = findViewById(R.id.SGPABtn);

        ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, degrees);
        degreeSpinner.setAdapter(ad);

        SGPABtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentName = nameET.getText().toString();
                degreeName = degreeSpinner.getSelectedItem().toString();
                if (studentName.equals("")) {
                    Toast.makeText(IntroActivity.this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(IntroActivity.this, SGPAActivity.class);
                    intent.putExtra("student_name", studentName);
                    intent.putExtra("degree_name", degreeName);
                    startActivity(intent);
                }
            }
        });


        CGPABtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentName = nameET.getText().toString();
                degreeName = degreeSpinner.getSelectedItem().toString();
                if (studentName.equals("")) {
                    Toast.makeText(IntroActivity.this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(IntroActivity.this, CGPAActivity.class);
                    intent.putExtra("student_name", studentName);
                    intent.putExtra("degree_name", degreeName);
                    startActivity(intent);
                }
            }
        });


    }

}