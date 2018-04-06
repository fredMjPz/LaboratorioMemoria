package com.example.igreenheart.memorygame;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import java.util.Random;
//@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
public class Game4x4Activity extends AppCompatActivity implements View.OnClickListener{

    private int numberOfElements;
    private MemoryButton[] buttons;

    private int[] buttonGraphicLocations;
    private int[] buttonGraphics;

    private MemoryButton selectedButton1;
    private MemoryButton selectedButton2;

    private boolean isBusy = false;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game4x4);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.grid_layout_4x4);

        int numColumns = gridLayout.getColumnCount();
        int numRows = gridLayout.getRowCount();

        numberOfElements = numColumns *  numRows;

        buttons = new MemoryButton[numberOfElements];

        buttonGraphics = new int[(numberOfElements +1)/ 2];

        buttonGraphics[0]= R.mipmap.a;
        buttonGraphics[1]= R.mipmap.b;
        buttonGraphics[2]= R.mipmap.c;
        buttonGraphics[3]= R.mipmap.d;
        buttonGraphics[4]= R.mipmap.e;
        buttonGraphics[5]= R.mipmap.f;
        buttonGraphics[6]= R.mipmap.g;
        buttonGraphics[7]= R.mipmap.h;

        buttonGraphicLocations = new int[numberOfElements];

        shuffleButtonGraphics();

        for(int r=0;r<numRows;r++){

            for(int c=0; c < numColumns; c++){

                MemoryButton tempButton = new MemoryButton(this, r, c, buttonGraphics[buttonGraphicLocations[r * numColumns +c]]);
                tempButton.setId(View.generateViewId());
                tempButton.setOnClickListener(this);
                buttons[r * numColumns + c] = tempButton;
                gridLayout.addView(tempButton);
            }

        }

    }

    private void shuffleButtonGraphics(){

        Random rand = new Random();

        for(int i=0; i< numberOfElements; i++){

            buttonGraphicLocations[i]= i % (numberOfElements / 2);
        }

        for(int i=0;i<numberOfElements;i++){

            int temp = buttonGraphicLocations[i];

            int sawpIndex = rand.nextInt(15);

            buttonGraphicLocations[i] = buttonGraphicLocations[sawpIndex];

            buttonGraphicLocations[sawpIndex] = temp;
        }
    }


    @Override
    public void onClick(View view) {

        if(isBusy)
            return;

        MemoryButton button = (MemoryButton)view;

        if(button.isMatched)
            return;

        if(selectedButton1==null){

            selectedButton1=button;
            selectedButton1.flip();
            return;
        }

        if(selectedButton1.getId() == button.getId())
            return;

        if(selectedButton1.getFrontDrawableId()==button.getFrontDrawableId()){

            button.flip();

            button.setMatched(true);
            selectedButton1.setMatched(true);

            selectedButton1.setEnabled(false);
            button.setEnabled(false);

            selectedButton1 = null;

            return;

        }else{

            selectedButton2 = button;
            selectedButton2.flip();
            isBusy = true;

            final Handler handler = new  Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        selectedButton2.flip();
                        selectedButton1.flip();
                        selectedButton1=null;
                        selectedButton2=null;
                        isBusy=false;
                    }
                },500);
        }

    }
}
