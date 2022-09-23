package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.firstapp.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setListener();
    }

    private void setListener() {
        binding.btnCalculate.setOnClickListener(listener -> {
            /**
             * Get Student
             */
            String student = binding.nameEditText.getText().toString().trim();
            if (student.isEmpty()) {

                Toast.makeText(this, getString(R.string.required_name), Toast.LENGTH_LONG).show();
                return;
            } else {
                student = getString(R.string.student) + student + "\n";
            }

            /**
             * Get Course
             */
            String course = getCourseSelected();
            if (course.isEmpty()) {
                Toast.makeText(this, getString(R.string.required_course), Toast.LENGTH_LONG).show();
                return;
            } else {
                course = getString(R.string.course) + course + "\n";
            }

            /**
             * Get Total
             */
            String total = getString(R.string.discount) + getTotalDiscounts() + "%\n" + getString(R.string.total) + getPriceTotal();

            /**
             * Print Result
             */
            binding.resultTextView.setText(student + course + total);
        });
    }

    private String getCourseSelected() {
        if (binding.radioNet.isChecked()) {
            return "Net";
        } else if (binding.radioJava.isChecked()) {
            return "Java";
        } else if (binding.radioPhp.isChecked()) {
            return "PHP";
        } else {
            return "";
        }
    }

    private double getPriceTotal() {
        double priceCourse = binding.radioNet.isChecked() ? 500 : binding.radioJava.isChecked() ? 400 : 300;
        if (getTotalDiscounts() > 0) {
            return priceCourse - priceCourse * getTotalDiscounts() / 100;
        } else {
            return priceCourse;
        }
    }

    private int getTotalDiscounts() {
        int totalDiscounts = 0;
        if (binding.chkDiscountFive.isChecked()) {
            totalDiscounts += 5;
        }
        if (binding.chkDiscountTen.isChecked()) {
            totalDiscounts += 10;
        }
        return totalDiscounts;
    }
}