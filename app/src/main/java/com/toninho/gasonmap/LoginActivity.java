package com.toninho.gasonmap;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.toninho.gasonmap.database.DBController;

public class LoginActivity extends AppCompatActivity {
    public static User loggedUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button login = (Button) findViewById(R.id.sendLoginButton);


        if (login != null) {
            login.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    EditText e = (EditText) findViewById(R.id.emailLoginEditText);
                    EditText p = (EditText) findViewById(R.id.passwordLoginEditText);
                    String email = e != null ? e.getText().toString() : null;
                    String password = p != null ? p.getText().toString() : null;
                    String result;
                    Cursor data;
                    DBController dbController = new DBController(getBaseContext());
                    data = dbController.login(email, password);
                    data.moveToFirst();

                    result = "Sucesso, você está sendo redirecionado";
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

                    email = data.getString(1);
                    String id = data.getString(0);

                    String name =  data.getString(2);
                    loggedUser = new User(id, email, name);

                    Intent maps = new Intent(v.getContext(), MapsActivity.class);
                    startActivity(maps);
                }
            });
        }

    }


    public void openRegister(View view){
        Intent register = new Intent(this, RegisterActivity.class);
        startActivity(register);
    }

    public void openForgotPassword(View view){
        Intent forgot = new Intent(this, ForgotPasswordActivity.class);
        startActivity(forgot);
    }

    /*public void openTeste(View view){
        EditText e = (EditText) findViewById(R.id.emailLoginEditText);
        EditText p = (EditText) findViewById(R.id.passwordLoginEditText);
        String email, password;

        email = e.getText().toString();
        password = p.getText().toString();

        Intent teste = new Intent(this, MainActivity.class);
        teste.putExtra("email", email);
        teste.putExtra("password", password);
        startActivity(teste);

    }*/

    public void openList(View v){
        Intent t = new Intent(this, ListUsersActivity.class);
        startActivity(t);
    }

    public static User getLoggedUser() {
        return loggedUser;
    }

}
