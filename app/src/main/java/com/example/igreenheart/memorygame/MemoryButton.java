package com.example.igreenheart.memorygame;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.AppCompatDrawableManager;
import android.widget.Button;
import android.widget.GridLayout;
/**
 * Created by Black1820 on 04/04/2018.
 */

@SuppressLint({"RestrictedApi", "AppCompatCustomView"})
@TargetApi(Build.VERSION_CODES.JELLY_BEAN)

public class MemoryButton extends Button {

    protected int row;
    protected int column;
    protected int frontDrawableId;

    protected boolean isFlipped = false;
    protected boolean isMatched = false;

    protected Drawable front;
    protected Drawable back;




    public MemoryButton(Context context, int r, int c, int frontImageDrawableId){

        super(context);
        row = r;
        column= c;
        frontDrawableId=frontImageDrawableId;

        front = AppCompatDrawableManager.get().getDrawable(context, frontDrawableId);
        back = AppCompatDrawableManager.get().getDrawable(context, R.mipmap.cubiertos);

        setBackground(back);

        GridLayout.LayoutParams tempParams = new GridLayout.LayoutParams(GridLayout.spec(r),GridLayout.spec(c));

        tempParams.width = (int) getResources().getDisplayMetrics().density * 50;
        tempParams.height = (int) getResources().getDisplayMetrics().density * 50;
        tempParams.bottomMargin = (int) getResources().getDisplayMetrics().density * 8;// se modifico esta linea
        tempParams.leftMargin = (int) getResources().getDisplayMetrics().density * 20;
        tempParams.rightMargin = (int) getResources().getDisplayMetrics().density * 20;



        setLayoutParams(tempParams);

    }



    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }

    public int getFrontDrawableId() {
        return frontDrawableId;
    }

    public void flip(){

        if(isMatched)
            return;

        if(isFlipped){
             setBackground(back);
             isFlipped=false;
        }
        else{
            setBackground(front);
            isFlipped=true;
        }
    }

}
