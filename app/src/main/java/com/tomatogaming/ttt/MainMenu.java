package com.tomatogaming.ttt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MainMenu extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button btnSettings = (Button)findViewById(R.id.btnSettings);
        Button btnPlay = (Button)findViewById(R.id.btnPlay);
        Button btnMultiplayer = (Button)findViewById(R.id.btnMultiplayer);
        Button btnQuit = (Button)findViewById(R.id.btnQuit); //people like to complain and call you an amateur if you don't have a quit button, even if its against the way things are supposed to be done...


        //when the settings button is pushed
        btnSettings.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //start the settings activity
                Intent settings = new Intent(MainMenu.this, SettingsActivity.class);
                startActivity(settings);
            }
        });

        //when play is pushed
        btnPlay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //TODO
                //replace with loading activity that sets things like prefs
                //a single player type lobby handler

                //start the game activity
                Intent game = new Intent(MainMenu.this, GameActivity.class);
                startActivity(game);
                finish();
            }
        });

        //when quit is hit
        btnQuit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //TODO
                // popup explaining that you really shouldn't close android apps an its managed by system etc
                //do you want to pause the app and return to home screen instead

                //pause the app
                finish();
            }
        });

        //when the mp button is hit
        btnMultiplayer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //launch lobby activity
                Intent lobby = new Intent(MainMenu.this, LobbyActivity.class);
                startActivity(lobby);
                finish();
            }
        });
    }
}
