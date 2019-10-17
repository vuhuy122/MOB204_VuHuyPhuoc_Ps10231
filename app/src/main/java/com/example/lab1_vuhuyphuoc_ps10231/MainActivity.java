package com.example.lab1_vuhuyphuoc_ps10231;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import DAO.NguoiDungDAO;


public class MainActivity extends AppCompatActivity {
    EditText edUserName, edPassword;
    Button btnLogin, btnCanel;
    CheckBox chkRememberPass;
    String strUser, strPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ánh xạ
        setTitle("ĐĂNG NHẬP");
        edUserName = findViewById(R.id.edUserName);
        edPassword = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnCanel = findViewById(R.id.btnCanel);
        chkRememberPass = findViewById(R.id.chkRememberPass);

    }

    public void checkLogin(View v) {
        strUser = edUserName.getText().toString();
        strPass = edPassword.getText().toString();
        if (strUser.isEmpty() || strPass.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Tên đăng nhập và mật khẩu không được bỏ trống",
                    Toast.LENGTH_SHORT).show();
        } else {


            if
            (strUser.equals("admin") && strPass.equals("admin")) {
                Intent i = new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(i);
            } else {
                Toast.makeText(getApplicationContext(),
                        "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_LONG).show();
            }
        }
    }

}

