package com.example.bookmarkapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    public static final String LOGIN_PREF = "Login";
    public static final String REMEMBER_PREF = "GetIn";
    DatabaseHelper databaseHelper;
    private EditText email;
    private EditText password;
    String emailMasuk;
    String passwordMasuk;
    Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences(LOGIN_PREF, MODE_PRIVATE);
        String check = sharedPreferences.getString(REMEMBER_PREF, "");
        if (check.equals("true")) {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        }
        databaseHelper = new DatabaseHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.ptEmail);
        password = findViewById(R.id.ptPassword);
        buttonLogin = findViewById(R.id.btnLogin);
        TextView daftar = findViewById(R.id.tvDaftar);
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailMasuk = email.getText().toString();
                passwordMasuk = password.getText().toString();
                Boolean checkEmailPass = databaseHelper.emailPassword(emailMasuk, passwordMasuk);
                if (checkEmailPass==true){
                    Toast.makeText(getApplicationContext(), "Login Success !", Toast.LENGTH_SHORT).show();
                    SharedPreferences preferences = getSharedPreferences(LOGIN_PREF, MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(REMEMBER_PREF, "true");
                    editor.apply();
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Login Failed ! ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
