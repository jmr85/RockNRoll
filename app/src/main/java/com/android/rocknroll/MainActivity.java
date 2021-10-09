package com.android.rocknroll;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public EditText campoMail;
    public EditText campoPassword;
    public View registro;
    public View ingresar;
    UserServiceLogin userServiceLogin1;
    TextView vt;

    ArrayList<Login> login = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );


        //Icono action bar
     //   getSupportActionBar().setDisplayShowHomeEnabled( true );
    //    getSupportActionBar().setIcon( R.mipmap.ic_icon );


        vt = (TextView) findViewById( R.id.fecha );
        vt.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vt.setText( new Date().toString() );
            }
        } );


        Time today = new Time( Time.getCurrentTimezone() );
        today.setToNow();
        int dia = today.monthDay;
        int mes = today.month;
        int ano = today.year;
        mes = mes + 1;
        vt.setText( "Hoy:  " + dia + "/" + mes + "/" + ano );


        campoMail = (EditText) findViewById( R.id.editTextEmail );
        campoPassword = (EditText) findViewById( R.id.editTextPassword );

        registro = (Button) findViewById( R.id.buttonRegistro );
        registro.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this, crearUsuario.class );
                startActivity( intent );
            }
        } );

        ingresar = (Button) findViewById( R.id.buttonIngresar );
        userServiceLogin1 = ApiUtilsLogin.getUserServiceLogin1();

        ingresar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mail = campoMail.getText().toString();
                String clave = campoPassword.getText().toString();

                doLogin( mail, clave );

            }
        } );

    }

    private void doLogin(final String mail, String clave) {
    RequestLogin requestLogin= new RequestLogin( mail, clave );

        Call<ResponseLogin> call =userServiceLogin1.login(requestLogin);
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {

                if (response.isSuccessful()) {

            if(response.body()!= null){

                String n_nombre= response.body().getNombre();
                String m_mail =response.body().getMail();
                String c_clave = response.body().getClave();
                String i_idusuario= String.valueOf( response.body().getIdusuario() );
                System.out.println("ID USUARIO: " + response.body().getIdusuario());


               Toast.makeText(MainActivity.this, "Bienvenido!  " + mail + "\n" + "Cargando publicaciones...", Toast.LENGTH_LONG).show();

                SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putString("nombre", n_nombre);
                editor.putString("clave", c_clave);
                editor.putString("mail", m_mail);
                editor.putString("idusuario", i_idusuario);
                System.out.println("ID -> i_idusuario: " + i_idusuario);

                editor.commit();

                Intent intent = new Intent(MainActivity.this, reservarEntradas.class);
                intent.putExtra("mail", mail);
                intent.putExtra("idusuario", i_idusuario);

                        startActivity(intent);


                    } else {
                        Toast.makeText(MainActivity.this, "Mail o contraseña incorrecta.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Mail o contraseña incorrecta.", Toast.LENGTH_SHORT).show();

                    ApiErrorLogin message = null;

               //     try {
                   //     message = new Gson().fromJson(response.errorBody().string(), ApiErrorLogin.class);
                     //   Toast.makeText(MainActivity.this, "" + message.getMensaje(), Toast.LENGTH_SHORT).show();
                    System.out.println(response.errorBody());

                 //   } catch (IOException e) {
                 //       e.printStackTrace();
                   //     Toast.makeText(MainActivity.this, "Error.", Toast.LENGTH_LONG).show();

                //    }


                }
            }


            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(MainActivity.this, "Intente nuevamente", Toast.LENGTH_SHORT).show();

            }
        });

    }
    }