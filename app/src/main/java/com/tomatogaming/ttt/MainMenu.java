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
        Button btnBattle = (Button)findViewById(R.id.btnBattle);


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

        //when battle btn is pushed
        btnBattle.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //launch battle activity
                Intent battle = new Intent(MainMenu.this, BattleActivity.class);
                startActivity(battle);
                finish();
            }
        });
    }
}
