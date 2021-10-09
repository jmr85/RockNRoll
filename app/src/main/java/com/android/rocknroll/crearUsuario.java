package com.android.rocknroll;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class crearUsuario extends AppCompatActivity {

    Button registrar;
    EditText nombre,mail, password;
    TextView vt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_crear_usuario );

        //flecha action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        //Icono action bar
        getSupportActionBar().setDisplayShowHomeEnabled( true );
        getSupportActionBar().setIcon(R.mipmap.ic_icon);


        vt=(TextView)findViewById(R.id.fecha);
        vt.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vt.setText( new Date().toString()  );
            }
        } );


        Time today=new Time(Time.getCurrentTimezone());
        today.setToNow();
        int dia=today.monthDay;
        int mes=today.month;
        int ano=today.year;
        mes=mes+1;
        vt.setText("Hoy:  " + dia + "/"+mes+"/"+ano);


        nombre = findViewById(R.id.editTextPersonName);
        password = findViewById(R.id.editTextPassword);
        mail = findViewById(R.id.editTextEmail);

        registrar= findViewById(R.id.buttonRegistro);
        registrar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registrarse();
             }
        });
    }

    public void registrarse() {
        RegistrarseRequest registrarseRequest = new RegistrarseRequest();
        registrarseRequest.setNombre(nombre.getText().toString());
        registrarseRequest.setClave(password.getText().toString());
        registrarseRequest.setMail(mail.getText().toString());


        Call<RegistrarseResponse> registrarseResponseCall = ApiClientRegister.getUserServiceRegister().userLoginRegister(registrarseRequest);
        registrarseResponseCall.enqueue(new Callback<RegistrarseResponse>() {
            @Override
            public void onResponse(Call<RegistrarseResponse> call, Response<RegistrarseResponse> response) {


                if (response.isSuccessful()) {

                    Toast.makeText(crearUsuario.this, "Registro Exitoso", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(crearUsuario.this, MainActivity.class);
                    startActivity(intent);

                } else {

                    APIError message = null;

                    try {
                        message = new Gson().fromJson(response.errorBody().string(), APIError.class);

                        if (message.getMail() != null) {
                            Toast.makeText(crearUsuario.this, "" + message.getMail(), Toast.LENGTH_SHORT).show();

                        } else if (message.getNombre() != null) {

                            Toast.makeText(crearUsuario.this, "" + message.getNombre(), Toast.LENGTH_SHORT).show();

                        } else if (message.getMensaje() != null) {
                            Toast.makeText(crearUsuario.this, "" + message.getMensaje(), Toast.LENGTH_SHORT).show();

                        } else if (message.getClave() != null) {
                            Toast.makeText(crearUsuario.this, "" + message.getClave(), Toast.LENGTH_SHORT).show();

                        } else {

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(crearUsuario.this, "Error.", Toast.LENGTH_LONG).show();
                    }
                }
            }


            @Override
            public void onFailure(Call<RegistrarseResponse> call, Throwable t) {

                Toast.makeText(crearUsuario.this, "Intente nuevamente", Toast.LENGTH_LONG).show();

            }
        });
    }

}


