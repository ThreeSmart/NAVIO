package com.example.navio.ui.account.pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.auth0.android.jwt.JWT;
import com.example.navio.R;
import com.example.navio.backend.local_storage.LocalStorage;
import com.example.navio.backend.model.User;
import com.example.navio.backend.service.AuthenticationService;

import java.util.Arrays;
import java.util.Calendar;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final ConstraintLayout backArrowLayout = findViewById(R.id.back_arrow_layout);
        backArrowLayout.setOnClickListener(v -> finish());

        final ImageView backArrow = findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(v -> finish());

        final EditText nameInput = findViewById(R.id.name_input);
        final EditText surnameInput = findViewById(R.id.surname_input);
        final EditText emailInput = findViewById(R.id.email_input);
        final EditText employeeIdInput = findViewById(R.id.employee_id_input);
        final EditText usernameInput = findViewById(R.id.username_input);
        final ConstraintLayout birthdateInput = findViewById(R.id.birthdate_input);
        final TextView dateValue = findViewById(R.id.date_value);
        final ImageView maleBox = findViewById(R.id.male_box);
        final ImageView femaleBox = findViewById(R.id.female_box);
        final Button updateProfileButton = findViewById(R.id.update_profile_button);
        final String[] genderValue = {null};

        maleBox.setOnClickListener(v -> {
            System.out.println("chi galis?");
            if ("FEMALE".equals(genderValue[0])) {
                femaleBox.setImageResource(R.drawable.gender_uncheck);
                maleBox.setImageResource(R.drawable.gender_check);
                genderValue[0] = "MALE";
            } else if ("MALE".equals(genderValue[0])) {
                maleBox.setImageResource(R.drawable.gender_uncheck);
                genderValue[0] = null;
            } else if (genderValue[0] == null) {
                maleBox.setImageResource(R.drawable.gender_check);
                genderValue[0] = "MALE";
            }
        });

        femaleBox.setOnClickListener(v -> {
            if ("MALE".equals(genderValue[0])) {
                maleBox.setImageResource(R.drawable.gender_uncheck);
                femaleBox.setImageResource(R.drawable.gender_check);
                genderValue[0] = "FEMALE";
            } else if ("FEMALE".equals(genderValue[0])) {
                femaleBox.setImageResource(R.drawable.gender_uncheck);
                genderValue[0] = null;
            } else if (genderValue[0] == null) {
                femaleBox.setImageResource(R.drawable.gender_check);
                genderValue[0] = "FEMALE";
            }
        });

        updateProfileButton.setOnClickListener(v -> {
            finish();
        });

        birthdateInput.setOnClickListener(v -> {
            final String dateValueAsString = dateValue.getText().toString();
            final String[] dateComponents = dateValueAsString.split("/");

            final int year = "YYYY".equals(dateComponents[2]) ? 2000 : Integer.parseInt(dateComponents[2]);
            final int month = "MM".equals(dateComponents[1]) ? 0 : Integer.parseInt(dateComponents[1]) - 1;
            final int day = "DD".equals(dateComponents[0]) ? 1 : Integer.parseInt(dateComponents[0]);

            System.out.println(Arrays.toString(dateComponents));
            final DatePickerDialog datePickerDialog = new DatePickerDialog(
                    ProfileActivity.this,
                    AlertDialog.THEME_HOLO_LIGHT,
                    (view, y, m, d) -> {
                        m = m + 1;
                        final String date = (d < 9 ? ("0" + d) : d) + "/" + (m < 9 ? ("0" + m) : m) + "/" + y;
                        dateValue.setText(date);
                    },
                    year,
                    month,
                    day
            );
            datePickerDialog.show();
        });

        final User authenticatedUser = AuthenticationService.getAuthenticatedUser(this);
        employeeIdInput.setText(String.valueOf(authenticatedUser.getId()));
        nameInput.setText(authenticatedUser.getName());
        surnameInput.setText(authenticatedUser.getSurname());
        emailInput.setText(authenticatedUser.getEmail());
        usernameInput.setText(authenticatedUser.getUsername());

        // TODO, implement this logic in api (to have gender)
        genderValue[0] = "MALE";
        if ("MALE".equals(genderValue[0])) {
            maleBox.setImageResource(R.drawable.gender_check);
        } else if ("FEMALE".equals(genderValue[0])) {
            femaleBox.setImageResource(R.drawable.gender_check);
        }

    }
}