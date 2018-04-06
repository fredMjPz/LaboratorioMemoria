package com.example.igreenheart.memorygame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

   private Button button4x4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);
       /* button4x4=(Button)findViewById(R.id.button_4x4);

        button4x4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Game4x4Activity.class);
                startActivity(intent);

           }
        });*/
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
            case R.id.item3:
                Toast.makeText(this, "Salir", Toast.LENGTH_SHORT).show();
                finish();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }

    }


}
