package com.cristhoper.mypersonalapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cristhoper.mypersonalapp.R;
import com.cristhoper.mypersonalapp.repositories.UserRepository;

public class RegisterActivity extends AppCompatActivity {

    private EditText fullnameInput;
    private EditText usernameInput;
    private EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullnameInput = (EditText)findViewById(R.id.fullname_input);
        usernameInput = (EditText)findViewById(R.id.username_input);
        passwordInput = (EditText)findViewById(R.id.password_input);
    }

    public void callRegister(View view){

        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();
        String fullname = fullnameInput.getText().toString();

        if(username.isEmpty() || password.isEmpty() || fullname.isEmpty()){
            Toast.makeText(this, "You must complete these fields", Toast.LENGTH_SHORT).show();
            return;
        }

        UserRepository.create(username, password, fullname);

        finish();

    }
}
