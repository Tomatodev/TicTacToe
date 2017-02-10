package com.tomatogaming.ttt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button btnQuit = (Button)findViewById(R.id.btnQuit);
        Button btnPass = (Button)findViewById(R.id.btnPass);


        //when the quit button is pressed
        btnQuit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //TODO
                //popup loss warning

                //TODO
                //cleanup if online match

                //quit to menu
                Intent menu = new Intent(GameActivity.this, MainMenu.class);
                startActivity(menu);
                finish();
            }
        });
    }


    //disable the back button in game
    @Override
    public void onBackPressed()
    {

    }
}
