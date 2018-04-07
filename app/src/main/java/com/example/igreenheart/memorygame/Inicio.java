package com.example.igreenheart.memorygame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.niveles,menu);
        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Toast.makeText(this, "Juego Clasico", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, JuegoClasico.class);
                this.startActivity(intent);
                return true;
            case R.id.item2:
                Toast.makeText(this, "Juego Avanzado", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(this, Game4x4Activity.class);
                this.startActivity(intent2);
                return true;
            case R.id.salir:
                Toast.makeText(this, "Salir", Toast.LENGTH_SHORT).show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
