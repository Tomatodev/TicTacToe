package com.tomatogaming.ttt;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class GameActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button btnQuit = (Button)findViewById(R.id.btnQuit);
        Button btnPass = (Button)findViewById(R.id.btnPass);

        //grid buttons
        final ImageButton[] grid = new ImageButton[9];
        grid[0] = (ImageButton)findViewById(R.id.tacGrid0);
        grid[1] = (ImageButton)findViewById(R.id.tacGrid1);
        grid[2] = (ImageButton)findViewById(R.id.tacGrid2);
        grid[3] = (ImageButton)findViewById(R.id.tacGrid3);
        grid[4] = (ImageButton)findViewById(R.id.tacGrid4);
        grid[5] = (ImageButton)findViewById(R.id.tacGrid5);
        grid[6] = (ImageButton)findViewById(R.id.tacGrid6);
        grid[7] = (ImageButton)findViewById(R.id.tacGrid7);
        grid[8] = (ImageButton)findViewById(R.id.tacGrid8);

        //grid presses
        grid[0].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                grid[0].setClickable(false);
                grid[0].setImageResource(R.drawable.red_x);
            }
        });
        grid[1].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                grid[1].setClickable(false);
                grid[1].setImageResource(R.drawable.red_x);
            }
        });
        grid[2].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                grid[2].setClickable(false);
                grid[2].setImageResource(R.drawable.red_x);
            }
        });
        grid[3].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                grid[3].setClickable(false);
                grid[3].setImageResource(R.drawable.red_x);
            }
        });
        grid[4].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                grid[4].setClickable(false);
                grid[4].setImageResource(R.drawable.red_x);
            }
        });
        grid[5].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                grid[5].setClickable(false);
                grid[5].setImageResource(R.drawable.red_x);
            }
        });
        grid[6].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                grid[6].setClickable(false);
                grid[6].setImageResource(R.drawable.red_x);
            }
        });
        grid[7].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                grid[7].setClickable(false);
                grid[7].setImageResource(R.drawable.red_x);
            }
        });
        grid[8].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                grid[8].setClickable(false);
                grid[8].setImageResource(R.drawable.red_x);
            }
        });



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
