package com.toninho.gasonmap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.toninho.gasonmap.database.DBController;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btn = (Button) findViewById(R.id.sendRegisterButton);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DBController dbController = new DBController(getBaseContext());
                EditText nameE = (EditText) findViewById(R.id.nameRegisterEditText);
                EditText emailE = (EditText) findViewById(R.id.emailRegisterEditText);
                EditText passwordE = (EditText) findViewById(R.id.passwordRegisterEditText);
                EditText confirmPasswordE = (EditText) findViewById(R.id.confirmPasswordRegisterEditText);

                String name = nameE.getText().toString();
                String email = emailE.getText().toString();
                String password = passwordE.getText().toString();
                String confirmPassword = confirmPasswordE.getText().toString();
                String result;

                if(password.compareTo(confirmPassword) == 0)
                    result = dbController.registerUser(name, email, password);

                else
                    result = "As senhas não são iguais!";

                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            }
        });
        Button btn2 = (Button) findViewById(R.id.linkToLoginButton);
        btn2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                DBController dbController = new DBController(getBaseContext());
                dbController.setPosto();
            }
        });
    }
}
