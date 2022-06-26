package com.example.navio.ui.login;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.navio.backend.model.UserLoginDetails;
import com.example.navio.backend.service.UserService;
import com.example.navio.backend.util.messages.ErrorMessageHandler;
import com.example.navio.ui.main.MainActivity;
import com.example.navio.R;

import org.json.JSONException;

public class LoginActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText usernameInput = findViewById(R.id.usernameInput);
        final EditText passwordInput = findViewById(R.id.passwordInput);

        final Button loginButton = findViewById(R.id.login_button);
        loginButton.setBackgroundTintList(null);
        loginButton.setOnClickListener(view -> {
            final String username = usernameInput.getText().toString();
            final String password = passwordInput.getText().toString();

            UserService.getInstance()
                    .login(this,
                            new UserLoginDetails(username, password),
                            s -> {
                                final Intent intent = new Intent(this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            },
                            s -> {
                                try {
                                    Toast.makeText(this, ErrorMessageHandler.handle(s.get("message").toString()), Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                    );

        });
    }

}