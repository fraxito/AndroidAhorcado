package com.example.jorgecisneros.ahorcado;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.ventanaJuego, new VentanaAhorcado())
                    .commit();
            //cambiaImagenAhorcado();
            eligePalabraOculta();
            //pintaGuionesEnLabel();
        }
    }

    public void botonPulsado (View vista){
        Button boton = (Button) findViewById(vista.getId());
        boton.setVisibility(View.INVISIBLE);

        if (boton.isEnabled()){
            //cambio a minúsculas la letra del botón
            String letra = boton.getText().toString().toLowerCase();
            System.out.println(letra);
            boton.setEnabled(false);
            TextView texto = (TextView)findViewById(R.id.palabraConGuiones);
            String palabraConGuiones = texto.getText().toString();

            if (palabraOculta.contains(letra)){
                for (int i=0; i< palabraOculta.length(); i++){
                    if (palabraOculta.charAt(i) == letra.charAt(0)){
                        palabraConGuiones =
                                palabraConGuiones.substring(0, 2*i)
                                        + letra
                                        + palabraConGuiones.substring(2*i +1);
                    }
                }
                texto.setText(palabraConGuiones);

                //compruebo si en la palabraConGuiones hay guiones o no
                // si hay algún guión no hago nada porque no he adivinado todavia la partida
                // si no hay guiones, tengo que indicar de alguna forma que he ganado la partida
                if (!palabraConGuiones.contains("_")){
                    numeroFallos = -1;
                }
            }
            else{
                numeroFallos++;

            }
        }
        else{
            boton.setEnabled(false);
        }
        cambiaImagenAhorcado();
    }

    String palabraOculta = "";

    // contador para saber el número de fallos
    int numeroFallos = 0;

    private void eligePalabraOculta(){
        String [] listaDePalabras = new String[10];
        Random r = new Random();

        listaDePalabras[0] = "tal";
        listaDePalabras[1] = "taluno";
        listaDePalabras[2] = "taldos";
        listaDePalabras[3] = "taltres";
        listaDePalabras[4] = "talcuatro";
        listaDePalabras[5] = "talcinco";
        listaDePalabras[6] = "talseis";
        listaDePalabras[7] = "talsiete";
        listaDePalabras[8] = "talocho";
        listaDePalabras[9] = "talnueve";


        palabraOculta = listaDePalabras[r.nextInt(9)];
        System.out.println(palabraOculta);
    }

    private void pintaGuionesEnLabel(){
        TextView texto = (TextView)findViewById(R.id.palabraConGuiones);
        texto.setText("");
        for (int i=0; i<palabraOculta.length(); i++){
            texto.setText(texto.getText() + "_ ");
        }
    }

    private void cambiaImagenAhorcado(){

        ImageView ahorcadoImagen = (ImageView) findViewById(R.id.imagenAhorcado);

        switch (numeroFallos){
            case 0: ahorcadoImagen.setImageResource(R.drawable.ahorcado_0); break;
            case 1: ahorcadoImagen.setImageResource(R.drawable.ahorcado_1); break;
            case 2: ahorcadoImagen.setImageResource(R.drawable.ahorcado_2); break;
            case 3: ahorcadoImagen.setImageResource(R.drawable.ahorcado_3); break;
            case 4: ahorcadoImagen.setImageResource(R.drawable.ahorcado_4); break;
            case 5: ahorcadoImagen.setImageResource(R.drawable.ahorcado_5); break;
            case 6: ahorcadoImagen.setImageResource(R.drawable.ahorcado_fin); break;
            case -1 : ahorcadoImagen.setImageResource(R.drawable.acertastetodo); break;
            default : ahorcadoImagen.setImageResource(R.drawable.ahorcado_fin); break;
        }

    }


    //private void chequeaLetra(JButton boton){

    //}

}
