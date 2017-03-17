package com.tomatogaming.ttt;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MainMenu extends AppCompatActivity
{
    Button btnSettings;
    Button btnPlay;
    Button btnMultiplayer;
    Button btnBattle;

    protected void hideMenu(boolean hide)
    {
        if (hide)
        {
            btnBattle.setVisibility(View.GONE);
            btnMultiplayer.setVisibility(View.GONE);
            btnPlay.setVisibility(View.GONE);
            btnSettings.setVisibility(View.GONE);
        }
        else
        {
            btnBattle.setVisibility(View.VISIBLE);
            btnMultiplayer.setVisibility(View.VISIBLE);
            btnPlay.setVisibility(View.VISIBLE);
            btnSettings.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        btnSettings = (Button)findViewById(R.id.btnSettings);
        btnPlay = (Button)findViewById(R.id.btnPlay);
        btnMultiplayer = (Button)findViewById(R.id.btnMultiplayer);
        btnBattle = (Button)findViewById(R.id.btnBattle);

        final GameFragment gameFragment = new GameFragment();

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
                //launch the game fragment
                Fragment fr = new GameFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.activity_main_menu, fr);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                hideMenu(true);
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
