package com.android.rocknroll;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class entradasReservadas extends AppCompatActivity {

    private TextView mJsonTxtView;
    UserServiceVerReservas userServiceVerReservas;

    ListView list;
    ArrayList<String> titles = new ArrayList<>();
    ArrayList<Publicaciones> publicaciones = new ArrayList<Publicaciones>();
    UserServiceVerReservas serviceVerReservas1;

    //Tres puntos
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.overflow, menu );
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.Item1) {

            SharedPreferences preferences = getSharedPreferences( "credenciales", Context.MODE_PRIVATE );
            String id_publicaciones = preferences.getString( "publicaciones", "-" );

            String ID = id_publicaciones;

            Integer i = Integer.valueOf( ID );

            if (i != 0) {

                Intent perfil = new Intent( this, entradasReservadas.class );
                startActivity( perfil );

            } else {

                Toast.makeText( entradasReservadas.this, "No posee reciatl reservado.", Toast.LENGTH_LONG ).show();

            }


        }
        if (id == R.id.Item2) {
            Intent perfil = new Intent( this, abmUsuario.class );
            startActivity( perfil );

        }
        if (id == R.id.Item3) {
            finish();
            System.exit( 0 );

        }

        return super.onOptionsItemSelected( item );

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_entradas_reservadas );

        //flecha action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        //Icono action bar
        getSupportActionBar().setDisplayShowHomeEnabled( true );
        getSupportActionBar().setIcon( R.mipmap.ic_icon );

        serviceVerReservas1 = ApiUtilsReservas.getUserServiceVerReservas1();

        SharedPreferences preferences = getSharedPreferences( "credenciales", Context.MODE_PRIVATE );
        String id_publicaciones = preferences.getString( "publicaciones", "-" );

        String id = id_publicaciones;

        Integer i = Integer.valueOf( id );

        verReservas( id_publicaciones );
        list.setOnItemClickListener( new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                muestraDialogo( position );
            }
        } );


    }


    private void verReservas(String id_publicaciones) {
        ArrayAdapter arrayAdapter = new ArrayAdapter( this, android.R.layout.simple_list_item_1, titles );


        list = findViewById( R.id.list );
        list.setAdapter( arrayAdapter );

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( "https://recitalestaller6.herokuapp.com/" )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();

        UserServiceVerReservas userServiceVerReservas = retrofit.create( UserServiceVerReservas.class );
        Call<List<com.android.rocknroll.Publicaciones>> call = userServiceVerReservas.verReservas();

        call.enqueue( new Callback<List<com.android.rocknroll.Publicaciones>>() {
            @Override
            public void onResponse(Call<List<com.android.rocknroll.Publicaciones>> call, Response<List<Publicaciones>> response) {
                for (com.android.rocknroll.Publicaciones post : response.body()) {


                    String fechastring;
                    Date date = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat( "dd/MM/yyyy" );
                    fechastring = formatter.format( date );

                    titles.add( "Recital: " + post.getNombre() + "\n" + "Fecha: " + fechastring + "\n" + "Id de evento: " + post.getId() );
                    publicaciones.add( post );

                }
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Publicaciones>> call, Throwable t) {
                mJsonTxtView.setText( t.getMessage() );
            }
        } );
    }

    private void muestraDialogo(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder( this );

        String fechastring = publicaciones.get( position ).getFecha_recital();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat( "dd/MM/yyyy" );
        fechastring = formatter.format( date );

        builder.setMessage( "" + publicaciones.get( position ).getNombre() + "\n" + fechastring + "\n" + publicaciones.get( position ).getNombre() + "\n" + publicaciones.get( position ).getId() )
                .setIcon( android.R.drawable.ic_delete )
                .setTitle( "¿Desea cancelar su reserva?" )
                .setCancelable( false )
                .setPositiveButton( "Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                        String idevento = publicaciones.get( position ).getId();

                        String cancelarreserva= "0";
                        cancelarReserva( idevento, position );
                        modificarReserva( idevento, position );
                    }
                } )

                .setNegativeButton( "No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {dialog.cancel();
                    }
                } );
        AlertDialog alert = builder.create();
        alert.show();
    }


    private void cancelarReserva(String cancelarreserva, int position) {

        SharedPreferences preferences = getSharedPreferences( "credenciales", Context.MODE_PRIVATE );
        String idusuario = preferences.getString( "idusuario", "-" );

        EditarRequest editarRequest = new EditarRequest();
        editarRequest.setIdusuario(idusuario);
        editarRequest.setPublicacion( cancelarreserva );


        Call<EditarResponse> editarResponseCall = ApiClientEditar.userServiceEdit().userEdit( editarRequest, idusuario );
        editarResponseCall.enqueue( new Callback<EditarResponse>() {
            @Override
            public void onResponse(Call<EditarResponse> call, Response<EditarResponse> response) {
                if (response.isSuccessful()) {

                    SharedPreferences preferences = getSharedPreferences( "credenciales", Context.MODE_PRIVATE );
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString( "publicaciones", cancelarreserva );
                    editor.commit();

                    Toast.makeText( entradasReservadas.this, "Cancelacion Exitosa!", Toast.LENGTH_LONG ).show();
                    recreate();

                } else {
                    APIError messege = null;

               // try {
                 //   message = new Gson().fromJson( response.errorBody().string(), APIError.class );
                    System.out.println(response.errorBody());
              //  } catch (IOException ioException) {
            //        ioException.printStackTrace();
                }
             //   Toast.makeText( entradasReservadas.this, "" + message, Toast.LENGTH_LONG ).show();


           }

            @Override
            public void onFailure(Call<EditarResponse> call, Throwable t) {

                Toast.makeText( entradasReservadas.this, "Intente nuevamente - Throwable" + t.getLocalizedMessage(), Toast.LENGTH_LONG ).show();
            }
        } );
    }

    private void modificarReserva(String idevento, int position) {

        String capacidad = "-1";
        String Id;
        Id = idevento;

        Integer s = Integer.valueOf( capacidad );

        InsertarPublicacionRequest insertarPublicacionRequest = new InsertarPublicacionRequest();
        insertarPublicacionRequest.setCapacidad( s );


        Call<InsertarPublicacionesResponse> insertarPublicacionesResponseCall = ApiUtilsReservas.getUserServiceVerReservas1().userServiceInsertarPublicaciones( insertarPublicacionRequest, Id );
        insertarPublicacionesResponseCall.enqueue( new Callback<InsertarPublicacionesResponse>() {
            @Override
            public void onResponse(Call<InsertarPublicacionesResponse> call, Response<InsertarPublicacionesResponse> response) {
                if (response.isSuccessful()) {


                } else {

                    APIError message = null;

                    try {
                        message = new Gson().fromJson( response.errorBody().string(), APIError.class );
                        Toast.makeText( entradasReservadas.this, "" + message, Toast.LENGTH_LONG ).show();


                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            }

            @Override
            public void onFailure(Call<InsertarPublicacionesResponse> call, Throwable t) {

                Toast.makeText( entradasReservadas.this, "Intente nuevamente...", Toast.LENGTH_LONG ).show();

            }
        } );
    }

}