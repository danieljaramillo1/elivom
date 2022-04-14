package com.aprendiendo.android;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.*;


import com.aprendiendo.android.Models.User;
import com.aprendiendo.android.Services.CreateUserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.aprendiendo.android.Services.ipConfig.ip;

public class RegistrarUsuarios extends AppCompatActivity {

    private Retrofit retrofit;
    EditText etName;
    EditText etEmail;
    EditText etAdress;
    EditText etPassword;
    TextView tvtest;
    Button btCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuarios);


        //conecto con vista
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etAdress = findViewById(R.id.etAdress);
        etPassword = findViewById(R.id.etPassword);
        tvtest = findViewById(R.id.textView3);

    }
    //metodo para validar nuevo usuario
        public void createUser(View v)
        {
            //Paso a Strings
            int i;
            String name = etName.getText().toString();
            String email = etEmail.getText().toString();
            String adress = etAdress.getText().toString();
            String password = etPassword.getText().toString();

            if(email.isEmpty()||password.isEmpty()||name.isEmpty()||adress.isEmpty())
            {
                    Toast.makeText(this,"Don´t let any field Empty!", Toast.LENGTH_SHORT).show();

            }
            else if(!contieneSoloLetras(name))
            {
                    Toast.makeText(this,"Only aphabetical here please!try Again", Toast.LENGTH_SHORT).show();
                    etName.setText("");
            }

            else if(password.length()<8)
            {
                Toast.makeText(this,"Sorry, put at least 8 characters here please", Toast.LENGTH_SHORT).show();

            }
            else
                {

                    CreateUsers();

                }


        }



    //metodo para verificar si solo contiene letras
    public static boolean contieneSoloLetras(String cadena) {
        for (int x = 0; x < cadena.length(); x++) {
            char c = cadena.charAt(x);
            // Si no está entre a y z, ni entre A y Z, ni es un espacio
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
                return false;
            }
        }
        return true;
    }
            //metodo para agregar un usuario
        public void CreateUsers(){

            retrofit = new Retrofit.Builder().baseUrl(ip).addConverterFactory(GsonConverterFactory.create()).build();
            CreateUserService service = retrofit.create(CreateUserService.class);
            User newUser = new User();
            newUser.setName(etName.getText().toString());
            newUser.setEmail(etEmail.getText().toString());
            newUser.setAdress(etAdress.getText().toString());
            newUser.setPassword(etPassword.getText().toString());
            Call<User> create = service.CreateUser(newUser);
            create.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                     if(response.isSuccessful())
                     {
                         Toast.makeText(getApplicationContext(),"user in the DB", Toast.LENGTH_SHORT).show();
                     }else {
                         Toast.makeText(getApplicationContext(),"there is a problem with you!! ", Toast.LENGTH_SHORT).show();
                     }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"there is a problem in conection:"+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }





    }





