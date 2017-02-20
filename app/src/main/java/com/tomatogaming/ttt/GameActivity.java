package com.tomatogaming.ttt;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class GameActivity extends AppCompatActivity
{
    public boolean isOnline = false; //whether or not the game is online
    public boolean turn = true; //turn counter will be from the perspective of player1
    public boolean[] gridState = {false, false, false, false, false, false, false, false, false}; //if a grid is occupied
    public boolean[] myGrid = {false, false, false, false, false, false, false, false, false}; //if your marker is there


    public int didWin()
    {
        final int WIN = 1;
        final int NO = 0;
        final int TIE = 2;
        if (myGrid[0] && myGrid[1] && myGrid[2]) //top row
            return WIN;
        if (myGrid[3] && myGrid[4] && myGrid[5]) //middle row
            return WIN;
        if (myGrid[6] && myGrid[7] && myGrid[8]) //bottom row
            return WIN;

        if (myGrid[0] && myGrid[3] && myGrid[6]) // left column
            return WIN;
        if (myGrid[1] && myGrid[4] && myGrid[7]) //middle column
            return WIN;
        if (myGrid[2] && myGrid[5] && myGrid[8]) //right column
            return WIN;

        if (myGrid[0] && myGrid[4] && myGrid[8]) //left to right diag
            return WIN;
        if (myGrid[6] && myGrid[4] && myGrid[2]) //right to left diag
            return WIN;

        boolean allTrue = true;
        for (boolean b : gridState)
        {
            if (!b)
            {
                allTrue = false;
            }
        }
        if (allTrue) //if there are no falses, the board is full
        {
            return TIE; //if the board is full, its a tie
        }


        return NO;
    }

    public void p2Turn()
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

        if (!isOnline) //if ai
        {
            turn = true;
            p1turn();
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

    public void p1PlacePick(int pick)
    {
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

        //load saved prefs if saved
        SharedPreferences settings = getSharedPreferences("prefs", 0);
        final String token = settings.getString("token", "x");
        final String color = settings.getString("color", "red");
        final int marker = getResources().getIdentifier(token+"_"+color, "drawable", this.getPackageName());
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);

        if (!gridState[pick]) //if this grid is not occupied
        {
            if (turn) //if it is your turn
            {
                grid[pick].setClickable(false); //disable future clicks
                grid[pick].setImageResource(marker); //place your marker
                myGrid[pick] = true; //set this grid to occupied
                gridState[pick] = true; //set this grid to occupied
                if (didWin() == 0) ; //if this place didnt win, continue
                {
                    turn = false; //turn over
                    p2Turn();
                }
                if (didWin() == 1) //it did win
                {
                    //popup win thing
                    builder1.setMessage("You won the game!");
                    builder1.setCancelable(false);
                    builder1.setNeutralButton("Great!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //TODO
                            //clean up and end game
                        }
                    });
                    AlertDialog alertWon = builder1.create();
                    alertWon.show();

                }
                if (didWin() == 2) //it was a tie
                {
                    //popup tie thing
                    builder1.setMessage("The game was a tie.");
                    builder1.setCancelable(false);
                    builder1.setNeutralButton("Bummer", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //TODO
                            //clean up and end game
                        }
                    });
                    AlertDialog alertTie = builder1.create();
                    alertTie.show();
                }
            }


        }
        else //theres already a token here
        {
            grid[pick].setClickable(false); //disable future clicks
        }
    }

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
                p1PlacePick(0);
            }
        });
        grid[1].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                p1PlacePick(1);
            }
        });
        grid[2].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                p1PlacePick(2);
            }
        });
        grid[3].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                p1PlacePick(3);
            }
        });
        grid[4].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                p1PlacePick(4);
            }
        });
        grid[5].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                p1PlacePick(5);
            }
        });
        grid[6].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                p1PlacePick(6);
            }
        });
        grid[7].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                p1PlacePick(7);
            }
        });
        grid[8].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                p1PlacePick(8);
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

