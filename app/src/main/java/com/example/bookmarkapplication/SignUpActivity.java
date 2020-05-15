package com.example.bookmarkapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    private EditText ptEmail, ptPassword1, ptPassword2;
    DatabaseHelper databaseHelper;
    private Button press;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        databaseHelper = new DatabaseHelper(this);
        ptEmail = findViewById(R.id.ptEmail);
        ptPassword1 = findViewById(R.id.ptPassword1);
        ptPassword2 = findViewById(R.id.ptPassword2);
        press = findViewById(R.id.btnLoginAfterSignUp);
        Button daftar = findViewById(R.id.btnSignUp);
        press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                finish();
            }
        });
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = ptEmail.getText().toString();
                String password1 = ptPassword1.getText().toString();
                String password2 = ptPassword2.getText().toString();
                if (email.equals("") || password1.equals("") || password2.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fields Are Empty !", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (password1.equals(password2)) {
                        Boolean checkEmail = databaseHelper.checkEmail(email);
                        if (checkEmail == true) {
                            Boolean insert = databaseHelper.insert(email, password1);
                            if (insert == true) {
                                Toast.makeText(getApplicationContext(), "Register Success !", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Email Already Exists !", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(getApplicationContext(), "Click Login Here", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
