package com.example.igreenheart.memorygame;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;

import java.util.Random;
import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import java.util.Random;

public class JuegoClasico extends AppCompatActivity implements View.OnClickListener {

    private int numeroDeElementos;

    private MemoryButton[] botones ;

    private int[] buttonGraphicLocations;
    private int[] buttonGraphics;

    private MemoryButton selectedButton1;
    private MemoryButton selectedButton2;

    private boolean isBusy = false;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_clasico);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayoutClasico);

        int numColumns = gridLayout.getColumnCount();
        int numRows = gridLayout.getRowCount();




        numeroDeElementos = numColumns * numRows;

        botones = new MemoryButton[numeroDeElementos];

        buttonGraphics = new int[ (numeroDeElementos + 1 ) / 2];

        buttonGraphics[0] = R.mipmap.a;
        buttonGraphics[1] = R.mipmap.b;
        buttonGraphics[2] = R.mipmap.c;
        buttonGraphics[3] = R.mipmap.d;
        buttonGraphics[4] = R.mipmap.e;

        buttonGraphicLocations = new int[numeroDeElementos];

        mezclarBotones();

        for(int r = 0; r < numRows; r++){

            for(int c = 0; c < numColumns; c++){

                MemoryButton botonTemp = new MemoryButton(this, r, c, buttonGraphics[buttonGraphicLocations[r * numColumns + c]]);
                botonTemp.setId(View.generateViewId());
                botonTemp.setOnClickListener(this);
                botones[r * numColumns + c] = botonTemp;
                gridLayout.addView(botonTemp);
            }
        }

    }
    protected void mezclarBotones(){

        Random random = new Random();

        for(int i = 0; i < numeroDeElementos; i++){

            buttonGraphicLocations[i] = i % (numeroDeElementos  / 2);
        }

        for(int i = 0; i < numeroDeElementos; i++){

            int temp = buttonGraphicLocations[i];
            int cambiarLugar = random.nextInt(9);


            buttonGraphicLocations[i] = buttonGraphicLocations[cambiarLugar];

            buttonGraphicLocations[cambiarLugar] = temp;
        }
    }
    @Override
    public void onClick(View view){
        if(isBusy)
            return;

        MemoryButton boton = (MemoryButton) view;

        if(boton.isMatched)
            return;

        if(selectedButton1 == null){

            selectedButton1 = boton;
            selectedButton1.flip();
            return;

        }

        if(selectedButton1.getId() == boton.getId())
            return;

        if(selectedButton1.getFrontDrawableId() == boton.getFrontDrawableId()){

            boton.flip();

            boton.setMatched(true);
            selectedButton1.setMatched(true);

            selectedButton1.setEnabled(false);
            boton.setEnabled(false);

            selectedButton1 = null;

            return;
        }
        else{

            selectedButton2 = boton;
            selectedButton2.flip();
            isBusy = true;

            final Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    selectedButton2.flip();
                    selectedButton1.flip();
                    selectedButton1 = null;
                    selectedButton2 = null;
                    isBusy = false;
                }
            }, 500);
        }

    }
}
