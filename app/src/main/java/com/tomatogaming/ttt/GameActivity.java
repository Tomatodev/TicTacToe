package com.tomatogaming.ttt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class GameActivity extends AppCompatActivity
{
    public boolean isOnline = false; //whether or not the game is online
    public boolean gameState = true; //if the game is running
    public boolean turn = true; //turn counter will be from the perspective of player1
    public boolean[] gridState = {false, false, false, false, false, false, false, false, false}; //if a grid is occupied


    public void p2Turn()
    {
        if (!isOnline) //if ai
        {
            TextView txtTurn = (TextView)findViewById(R.id.txtTurn);
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

            btnPass.setAlpha(0.5f); //fade the pass button
            btnPass.setClickable(false); //disable button
            txtTurn.setText(R.string.uiTheirTurn);

            //loop through the grid buttons and disable them
            for (int i = 0; i < grid.length; i++)
            {
                grid[i].setClickable(false);
            }

        }
        else //online match
        {

        }
    }

    public void p1turn()
    {
        TextView txtTurn = (TextView)findViewById(R.id.txtTurn);
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


        btnPass.setAlpha(1); //enable
        btnPass.setClickable(true); //enable button
        txtTurn.setText(R.string.uiYourTurn);

        //loop through the grid buttons and enable them
        for (int i = 0; i < grid.length; i++)
        {
            grid[i].setClickable(true);
        }

    }

    public int aiWouldWin() //check and see if only one is needed to win
    {
        int pick = 0;
        boolean canWin = false;
        boolean canBlock = false;

        //TODO
        //iterate over the grid and see if any are winners

        //if canWin true return winner
        if (canWin)
            return pick;

            //if false check for blocks
        else
        {
            //iterate over the grid and see if p1 is about to win
            //if true return block
            if (canBlock)
            {
                return pick;
            }
            //if false pick at random
            else
            {
                //while random !available
                return 5;
            }
        }


    }

    public void aiPlacePick(int pick) //put the marker
    {
        //TODO
        //check and see if spot is free
        //place marker
        //end turn
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button btnQuit = (Button)findViewById(R.id.btnQuit);
        Button btnPass = (Button)findViewById(R.id.btnPass);

        //load saved prefs if saved
        SharedPreferences settings = getSharedPreferences("prefs", 0);
        final String token = settings.getString("token", "x");
        final String color = settings.getString("color", "red");
        final int marker = getResources().getIdentifier(token+"_"+color, "drawable", this.getPackageName());


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
                if (!gridState[0]) //if this grid is not occupied
                {
                    if (turn) //if it is your turn
                    {
                        grid[0].setClickable(false); //disable future clicks
                        grid[0].setImageResource(marker); //place your marker
                        gridState[0] = true; //set this grid to occupied
                        turn = false; //turn over
                        p2Turn();
                    }
                }
                else //theres already a token here
                {
                    grid[0].setClickable(false); //disable future clicks
                }
            }
        });
        grid[1].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (!gridState[1]) //if this grid is not occupied
                {
                    if (turn) //if it is your turn
                    {
                        grid[1].setClickable(false); //disable future clicks
                        grid[1].setImageResource(marker); //place your marker
                        gridState[1] = true; //set this grid to occupied
                        turn = false; //turn over
                        p2Turn();
                    }
                }
                else //theres already a token here
                {
                    grid[1].setClickable(false); //disable future clicks
                }
            }
        });
        grid[2].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (!gridState[2]) //if this grid is not occupied
                {
                    if (turn) //if it is your turn
                    {
                        grid[2].setClickable(false); //disable future clicks
                        grid[2].setImageResource(marker); //place your marker
                        gridState[2] = true; //set this grid to occupied
                        turn = false; //turn over
                        p2Turn();
                    }
                }
                else //theres already a token here
                {
                    grid[2].setClickable(false); //disable future clicks
                }
            }
        });
        grid[3].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (!gridState[3]) //if this grid is not occupied
                {
                    if (turn) //if it is your turn
                    {
                        grid[3].setClickable(false); //disable future clicks
                        grid[3].setImageResource(marker); //place your marker
                        gridState[3] = true; //set this grid to occupied
                        turn = false; //turn over
                        p2Turn();
                    }
                }
                else //theres already a token here
                {
                    grid[3].setClickable(false); //disable future clicks
                }
            }
        });
        grid[4].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (!gridState[4]) //if this grid is not occupied
                {
                    if (turn) //if it is your turn
                    {
                        grid[4].setClickable(false); //disable future clicks
                        grid[4].setImageResource(marker); //place your marker
                        gridState[4] = true; //set this grid to occupied
                        turn = false; //turn over
                        p2Turn();
                    }
                }
                else //theres already a token here
                {
                    grid[4].setClickable(false); //disable future clicks
                }
            }
        });
        grid[5].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (!gridState[5]) //if this grid is not occupied
                {
                    if (turn) //if it is your turn
                    {
                        grid[5].setClickable(false); //disable future clicks
                        grid[5].setImageResource(marker); //place your marker
                        gridState[5] = true; //set this grid to occupied
                        turn = false; //turn over
                        p2Turn();
                    }
                }
                else //theres already a token here
                {
                    grid[5].setClickable(false); //disable future clicks
                }
            }
        });
        grid[6].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (!gridState[6]) //if this grid is not occupied
                {
                    if (turn) //if it is your turn
                    {
                        grid[6].setClickable(false); //disable future clicks
                        grid[6].setImageResource(marker); //place your marker
                        gridState[6] = true; //set this grid to occupied
                        turn = false; //turn over
                        p2Turn();
                    }
                }
                else //theres already a token here
                {
                    grid[6].setClickable(false); //disable future clicks
                }
            }
        });
        grid[7].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (!gridState[7]) //if this grid is not occupied
                {
                    if (turn) //if it is your turn
                    {
                        grid[7].setClickable(false); //disable future clicks
                        grid[7].setImageResource(marker); //place your marker
                        gridState[7] = true; //set this grid to occupied
                        turn = false; //turn over
                        p2Turn();
                    }
                }
                else //theres already a token here
                {
                    grid[7].setClickable(false); //disable future clicks
                }
            }
        });
        grid[8].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (!gridState[8]) //if this grid is not occupied
                {
                    if (turn) //if it is your turn
                    {
                        grid[8].setClickable(false); //disable future clicks
                        grid[8].setImageResource(marker); //place your marker
                        gridState[8] = true; //set this grid to occupied
                        turn = false; //turn over
                        p2Turn();
                    }
                }
                else //theres already a token here
                {
                    grid[8].setClickable(false); //disable future clicks
                }
            }
        });


        //when pass is pressed
        btnPass.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (turn) //if it is your turn
                {
                    turn = false; //turn over
                    p2Turn();
                }

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

        p1turn();
    }


    //disable the back button in game
    @Override
    public void onBackPressed()
    {

    }



}

