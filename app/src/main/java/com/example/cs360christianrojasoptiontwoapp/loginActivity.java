//package com.example.cs360christianrojasoptiontwoapp;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.database.sqlite.SQLiteDatabase;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//
//public class loginActivity extends AppCompatActivity {
//
//    private EditText username, password;
//    private Button login, register;
//    private SQLiteManager sqLiteDatabase;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        initWidgets();
//
//        sqLiteDatabase = new SQLiteManager();
//
//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String getEmail = username.getText().toString();
//                String getPassword = password.getText().toString();
//
//                sqLiteDatabase.registerUser(new LoginData(getEmail,getPassword));
//            }
//        });
//
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String getEmail = username.getText().toString();
//                String getPassword = password.getText().toString();
//
//                sqLiteDatabase.loginUser(new LoginData(getEmail,getPassword));
//
//            }
//        });
//    }
//
//    public void initWidgets() {
//        username = findViewById(R.id.newUsername);
//        password = findViewById(R.id.newPassword);
//        login = findViewById(R.id.loginButton);
//        register = findViewById(R.id.registerButton);
//    }
//}