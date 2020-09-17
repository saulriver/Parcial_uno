package com.example.parcial1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //para llamar a las variables privadas que vamos a usar en las diferentes clases
    private TextView  textViewMensajeLimite, textViewMensajeListView;
    private EditText editTextCapacidadParqueadero;
    private EditText editTextCapacidadPlaya;
    private EditText editTextPlacaVehiculo;
    private EditText editTextNumeroPersonas;
    private Button buttonRegistrar, buttonRetirarRegistro;
    private ListView listView;

    //para llamar el arraylist clase de java
    private List<String> placaVehiculos;
    private List<String> numeroPersonas;
    private ArrayAdapter listViewRegistrar;

    //para llamar al contador y acumulador
    private int contador = 0;
    private int acumulador = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPlacaVehiculo = (EditText)findViewById(R.id.editText3);
        editTextNumeroPersonas = (EditText)findViewById(R.id.editText4);
        editTextCapacidadParqueadero = (EditText)findViewById(R.id.editText1);
        editTextCapacidadPlaya = (EditText)findViewById(R.id.editText2);
        textViewMensajeListView = (TextView) findViewById(R.id.textView7);
        textViewMensajeLimite = (TextView) findViewById(R.id.textView2);
        buttonRegistrar = (Button) findViewById(R.id.button1);
        buttonRetirarRegistro = (Button) findViewById(R.id.button2);
        listView = (ListView)findViewById(R.id.listView);

        //deshabilito el editTextCapacidadParqueadero y editTextCapacidadPlaya para que no sea modificado.
        editTextCapacidadParqueadero.setEnabled(false);
        editTextCapacidadPlaya.setEnabled(false);

        //declaracion de arraylist para los campos editText3 y editText4
        placaVehiculos = new ArrayList <>();
        numeroPersonas = new ArrayList <>();

        //codigo para listar los datos que quiero almacenar en mi arraylist y pintarlos en el listView
        listViewRegistrar = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, placaVehiculos);
        listView.setAdapter(listViewRegistrar);

        //codigo para cuando presionamos en el boton registrar nos almacene los datos de los editText3 y 4 de placa de vehiculo y N° de personas en el listView
        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placaVehiculos.add(editTextPlacaVehiculo.getText().toString());
                numeroPersonas.add(editTextNumeroPersonas.getText().toString());
                editTextPlacaVehiculo.getText().clear();
                editTextNumeroPersonas.getText().clear();
                listViewRegistrar.notifyDataSetChanged();

                //realiza el incremento en el campo de capacidad de parqueadero
                //con una capacidad de 5 vehiculos si la sentencia se cumple muestra mensaje y se deshabilita el campo de ingreso de placa de vehiculos.
                //para que el operador no pueda ingresar mas datos.
                contador++;
                editTextCapacidadParqueadero.setText("" + contador);
                if (contador == 5) {
                    textViewMensajeLimite.setText("Retira registros para seguir ingresando vehiculos.");
                    editTextPlacaVehiculo.setEnabled(false);
                }
                //le sumo al carro personas variables le sumo uno si la variable es menor igual guardar sino porque ya supere la cantidad
                acumulador++;
                editTextCapacidadPlaya.setText("" + acumulador);
                if (acumulador == 10) {
                    textViewMensajeLimite.setText("Retira registros para seguir ingresando personas.");
                }
            }});

        //codigo para cuando presionamos en el listView los datos almacenados nos arroje la información en el textView//
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView parent, View view, int position, long id) {
                textViewMensajeListView.setText (" Placa del vehiculo: " + placaVehiculos.get(position) + "\n" + " Numero de personas: " + numeroPersonas.get(position));

            }
        });

        //codigo para eliminar al elegir los registros del listView con el boton de retirar registro y muestre un mensaje en el textView//
        buttonRetirarRegistro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int position = 0;
                textViewMensajeListView.setText("La placa del vehiculo: " + "( " + placaVehiculos.remove(position) + " )" + "\n" + " con N° de personas en playa: " + " (" + numeroPersonas.remove(position) + ") " + " ha sido RETIRADA. ");
                listViewRegistrar.notifyDataSetChanged();

                //realiza el decremento al momento de retirar un registro en el campo capacidad de parqueadero.
                //si la sentencia se cumple se borra el ultimo conteo, y se habilita un mensaje.
                contador --;
                editTextCapacidadParqueadero.setText("" + contador);
                if (contador <= 0 ){
                    editTextCapacidadParqueadero.getText().clear();
                    textViewMensajeLimite.setText ("Ahora puedes seguir ingresando ");
                }
                //cuando se elimina una placa de un vehiculo, se habilita el campo para seguir registrando vehiculos.
                editTextPlacaVehiculo.setEnabled(true);

                return;
            }
        });
    }
}
