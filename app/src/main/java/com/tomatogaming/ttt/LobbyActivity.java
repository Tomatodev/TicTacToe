package com.tomatogaming.ttt;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class LobbyActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        Button btnBack = (Button)findViewById(R.id.btnBack);
        Button btnNew = (Button)findViewById(R.id.btnNew);
        Button btnJoin = (Button)findViewById(R.id.btnJoin);
        Button btnSpectate = (Button)findViewById(R.id.btnSpectate);

        //TODO
        //attempt connection

        //TODO
        //populate the game list



        //on back press
        btnBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //TODO
                //dc gracefully

                //quit to main menu
                Intent menu = new Intent(LobbyActivity.this, MainMenu.class);
                startActivity(menu);
                finish();
            }
        });

        //on new press
        btnNew.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //TODO
                //create a new match
            }
        });

        //on join press
        btnJoin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //TODO
                //check if the selected match is null

                //TODO
                //check if the match is passworded

                //TODO
                //join match
            }
        });

        //on spectate press
        btnSpectate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //TODO
                //check if the selected match is null

                //TODO
                //check if spectate is enabled

                //TODO
                //spectate
            }
        });
    }

}
