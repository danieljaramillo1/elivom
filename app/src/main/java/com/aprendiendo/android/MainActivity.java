package com.aprendiendo.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.aprendiendo.android.Models.AnswerBoolean;
import com.aprendiendo.android.Models.UserLogin;
import com.aprendiendo.android.Services.CreateUserService;
import com.aprendiendo.android.Services.LogUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity  {
    private Retrofit retrofit;
    EditText etMail, etPass;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etMail = findViewById(R.id.etMail);
        etPass = findViewById(R.id.etPass);


    }

    //Metodo boton Registrarse
    public void Registrarse (View view){
        Intent registrarse = new Intent(this, RegistrarUsuarios.class);
        startActivity(registrarse);
    }

    // Metodo boton Loggin provicional
    public void Login (View view){
                LogUser();

    }



        public void LogUser()
        {
            retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.110:8080/").addConverterFactory(GsonConverterFactory.create()).build();
            LogUser service = retrofit.create(LogUser.class);
            UserLogin userLogging = new UserLogin();
            userLogging.setEmail(etMail.getText().toString());
            userLogging.setPassword(etPass.getText().toString());
            Call<AnswerBoolean> log = service.LogUser(userLogging);
            log.enqueue(new Callback<AnswerBoolean>() {
                @Override
                public void onResponse(Call<AnswerBoolean> call, Response<AnswerBoolean> response) {
                    if(response.isSuccessful())
                    {
                        Log.d("...TAG...", "onResponse: "+ response.body().getRes());


                        if(response.body().getRes())
                        {
                            Toast.makeText(getApplicationContext(),"ok!",Toast.LENGTH_LONG).show();
                            Intent login = new Intent(getApplicationContext(), Inicio.class);
                            startActivity(login);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Wrong email or password",Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"sorry there is a problem!",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<AnswerBoolean> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"Connection problem:"+t.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }

}
