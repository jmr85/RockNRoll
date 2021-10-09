package com.android.rocknroll;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class abmUsuario extends AppCompatActivity {

    private View guardarCambios, eliminarUsuario;

    public EditText nombre,mail, clave;

    public static int MILISEGUNDOS_ESPERA = 5000;

    //Tres puntos
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.Item1) {

            SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
            String id_publicaciones = preferences.getString("publicaciones", "-");

            String ID = id_publicaciones;

            Integer i = Integer.valueOf(ID);

            if (i != 0) {

                Intent perfil = new Intent(this, entradasReservadas.class);
                startActivity(perfil);

            } else {

                Toast.makeText(abmUsuario.this, "No posee reciatl reservado.", Toast.LENGTH_LONG).show();

            }


        }
        if (id == R.id.Item2) {
            Intent perfil = new Intent(this, abmUsuario.class);
            startActivity(perfil);

        }
        if (id == R.id.Item3) {
            finish();
            System.exit(0);

        }

        return super.onOptionsItemSelected(item);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_abm_usuario );

        //flecha action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        //Icono action bar
        getSupportActionBar().setDisplayShowHomeEnabled( true );
        getSupportActionBar().setIcon( R.mipmap.ic_icon );


        nombre = (EditText) findViewById( R.id.editTextPersonName );
        mail = (EditText) findViewById( R.id.editTextEmail );
        clave = (EditText) findViewById( R.id.editTextPassword );

        SharedPreferences preferences = getSharedPreferences( "credenciales", Context.MODE_PRIVATE );

       // String id =String.valueOf(preferences.getString( "idusuario", " " ));

        String id = preferences.getString( "idusuario", " " );
        String n_nombre = preferences.getString( "nombre", " " );
        String m_mail = preferences.getString( "mail", " " );
        String c_clave = preferences.getString( "clave", " " );

        nombre.setText( n_nombre );
        mail.setText( m_mail );
        clave.setText( c_clave );



        guardarCambios = (Button) findViewById( R.id.buttonGuardarCambios );
        guardarCambios.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( abmUsuario.this, MainActivity.class );
                editar( id);
            }
        } );

        eliminarUsuario= (Button) findViewById( R.id.buttonEliminarUsuario);
        eliminarUsuario.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( abmUsuario.this, MainActivity.class );
                eliminar( id );
            }
        } );

    }

    private void eliminar(String id) {
        EliminarRequest eliminarRequest = new EliminarRequest();
        eliminarRequest.setIdusuario(id);
        eliminarRequest.setNombre(nombre.getText().toString());
        eliminarRequest.setMail(mail.getText().toString());
        eliminarRequest.setClave(clave.getText().toString());
        Call<EliminarResponse> eliminarResponseCall = ApiClienteEliminar.userServiceEliminar().userEliminar(eliminarRequest, id);
        eliminarResponseCall.enqueue(new Callback<EliminarResponse>(){
            @Override
            public void onResponse(Call<EliminarResponse> call, Response<EliminarResponse>response) {
                if (response.isSuccessful()) {

                    Toast.makeText( abmUsuario.this, "Usuario eliminado.", Toast.LENGTH_LONG ).show();
                    esperarYCerrar( MILISEGUNDOS_ESPERA );
                    Intent intent = new Intent(abmUsuario.this, MainActivity.class);
                    startActivity( intent );

                } else {

                    APIError message = null;

                    try {
                        message = new Gson().fromJson( response.errorBody().string(), APIError.class );
                        Toast.makeText( abmUsuario.this, "" + message, Toast.LENGTH_LONG ).show();


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        @Override
        public void onFailure(Call<EliminarResponse> call, Throwable t) {

            Toast.makeText(abmUsuario.this, "Intente nuevamente - Throwable" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

        }
    });

}

    private void editar(String id) {
        EditarRequest editarRequest = new EditarRequest();
        editarRequest.setIdusuario(id);
        editarRequest.setNombre(nombre.getText().toString());
        editarRequest.setMail(mail.getText().toString());
        editarRequest.setClave(clave.getText().toString());

       Call<EditarResponse> editarResponseCall = ApiClientEditar.userServiceEdit().userEdit(editarRequest, id);

                editarResponseCall.enqueue(new Callback<EditarResponse>() {
            @Override
            public void onResponse(Call<EditarResponse> call, Response<EditarResponse> response) {
                if (response.isSuccessful()) {

                    Toast.makeText(abmUsuario.this, "Cambios efectuados correctamente.La aplicacion se reiniciara en 5 segundos.", Toast.LENGTH_LONG).show();
                  //  esperarYCerrar(MILISEGUNDOS_ESPERA);
                    Intent intent = new Intent(abmUsuario.this, MainActivity.class);
                    startActivity( intent );

                } else {

                    APIError message = null;

                    // try {
                    //   message = new Gson().fromJson( response.errorBody().string(), APIError.class );
                    System.out.println(response.errorBody());
                    //  } catch (IOException ioException) {
                    //        ioException.printStackTrace();

                //   Toast.makeText( entradasReservadas.this, "" + message, Toast.LENGTH_LONG ).show();



                }
            }

            @Override
            public void onFailure(Call<EditarResponse> call, Throwable t) {

                Toast.makeText(abmUsuario.this, "Intente nuevamente - Throwable" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }

    public void esperarYCerrar(int milisegundos) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(abmUsuario.this, MainActivity.class);
                startActivity(intent);

            }
        }, milisegundos);
    }
}