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

import java.util.Random;


public class GameActivity extends AppCompatActivity
{
    boolean isOnline = false; //whether or not the game is online
    boolean turn = true; //turn counter will be from the perspective of player1
    int aiMarker; //ai token marker
    int marker; //p1 marker
    boolean[] gridState = {false, false, false, false, false, false, false, false, false}; //if a grid is occupied
    boolean[] myGrid = {false, false, false, false, false, false, false, false, false}; //if your marker is there
    boolean[] aiGrid = {false, false, false, false, false, false, false, false, false}; //tracks ai markers
    ImageButton[] grid = new ImageButton[9];

    public int didWin(String player)
    {
        final int WIN = 1;
        final int NO = 0;
        final int TIE = 2;

        if (player.equals("p1"))
        {
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
        if (player.equals("ai"))
        {
            if (aiGrid[0] && aiGrid[1] && aiGrid[2]) //top row
                return WIN;
            if (aiGrid[3] && aiGrid[4] && aiGrid[5]) //middle row
                return WIN;
            if (aiGrid[6] && aiGrid[7] && aiGrid[8]) //bottom row
                return WIN;

            if (aiGrid[0] && aiGrid[3] && aiGrid[6]) // left column
                return WIN;
            if (aiGrid[1] && aiGrid[4] && aiGrid[7]) //middle column
                return WIN;
            if (aiGrid[2] && aiGrid[5] && aiGrid[8]) //right column
                return WIN;

            if (aiGrid[0] && aiGrid[4] && aiGrid[8]) //left to right diag
                return WIN;
            if (aiGrid[6] && aiGrid[4] && aiGrid[2]) //right to left diag
                return WIN;

            boolean isFull = false;
            for (int i = 0; i < gridState.length; i++)
            {
                if (!gridState[i]) //if that spot is open
                {
                    isFull = false;
                    break;
                }
                else
                {
                    isFull = true;
                }
            }
            if (isFull)
            {
                return TIE;
            }
            return NO;
        }
        else
        {
            return NO;
        }
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
            aiPlacePick();
        }
        else //online match
        {

        }
    }

    public void p1Turn()
    {
        TextView txtTurn = (TextView)findViewById(R.id.txtTurn);
        Button btnPass = (Button)findViewById(R.id.btnPass);
        //grid buttons
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
            if (!gridState[i]) //if that grid was empty
            {
                grid[i].setClickable(true); //re-enable clicks
            }
        }

    }

    public int aiGetPick() //check and see if only one is needed to win
    {
        int pick = 0;
        boolean canWin = false;
        boolean canBlock = false;

        //iterate over the grid and see if any are winners
        for (int i = 0; i < aiGrid.length; i++)
        {
            if (gridState[i]) //if there is a token in that spot
            {
                continue;
            }
            else //the spot is open
            {
            //horizontal checking
                if (i == 0 || i == 3 || i == 6) //the left column
                {
                    if (aiGrid[i + 1] && aiGrid[i + 2]) //i, and the two to its right
                    {
                        pick = i;
                        canWin = true;
                        break;
                    }
                }
                if (i == 1 || i == 4 || i == 7) //the middle column
                {
                    if (aiGrid[i - 1] && aiGrid[i + 1]) //to the left and right
                    {
                        pick = i;
                        canWin = true;
                        break;
                    }
                }
                if (i == 2 || i == 5 || i == 8) //the final column
                {
                    if (aiGrid[i -1] && aiGrid[i -2]) //the two to its left
                    {
                        pick = i;
                        canWin = true;
                        break;
                    }
                }

            //vertical checking
                if (i == 0 || i ==1 || i == 2) //top row
                {
                    if (aiGrid[i + 3] && aiGrid[i + 6])//the grid directly under and directly under that
                    {
                        pick = i;
                        canWin = true;
                        break;
                    }
                }
                if (i == 3 || i ==4 || i == 5) //middle row
                {
                    if (aiGrid[i - 3] && aiGrid[i + 3])//the grid directly above and directly under
                    {
                        pick = i;
                        canWin = true;
                        break;
                    }
                }
                if (i == 6 || i ==7 || i == 8) //bottom row
                {
                    if (aiGrid[i - 3] && aiGrid[i - 6])//the grid directly above and the grid directly above that
                    {
                        pick = i;
                        canWin = true;
                        break;
                    }
                }

            // diagonal checking
                if (i == 0) //top left
                {
                    if (aiGrid[i + 4] && aiGrid[i + 8]) //middle to bottom right
                    {
                        pick = i;
                        canWin = true;
                        break;
                    }
                }
                if ( i == 2) //top right
                {
                    if (aiGrid[i +2] && aiGrid[i +4]) //middle to bottom left
                    {
                        pick = i;
                        canWin = true;
                        break;
                    }
                }
                if (i == 4) //middle
                {
                    if (aiGrid[i - 4] && aiGrid[i + 4]) //top left to bottom right
                    {
                        pick = i;
                        canWin = true;
                        break;
                    }
                    if (aiGrid[i - 2] && aiGrid[i + 2]) //top right to bottom left
                    {
                        pick = i;
                        canWin= true;
                        break;
                    }
                }
                if ( i == 6) //bottom left
                {
                    if (aiGrid[i -2] && aiGrid[i -4]) //middle to top left
                    {
                        pick = i;
                        canWin = true;
                        break;
                    }
                }
                if (i == 8) //bottom right
                {
                    if (aiGrid[i - 4] && aiGrid[i - 8]) //middle to top left
                    {
                        pick = i;
                        canWin = true;
                        break;
                    }
                }
            }
        }


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
                //TODO
                //implement blocking
                return pick;
            }
            //if false pick at random
            else
            {
                Random rn = new Random();
                int r = rn.nextInt(8 + 1);
                while (gridState[r]) //while the random grid is occupied
                {
                    r = rn.nextInt(8 + 1); //get a new random
                }
                return r;
            }

        }


    }

    public void aiPlacePick() //put the marker
    {
        //grid buttons
        grid[0] = (ImageButton)findViewById(R.id.tacGrid0);
        grid[1] = (ImageButton)findViewById(R.id.tacGrid1);
        grid[2] = (ImageButton)findViewById(R.id.tacGrid2);
        grid[3] = (ImageButton)findViewById(R.id.tacGrid3);
        grid[4] = (ImageButton)findViewById(R.id.tacGrid4);
        grid[5] = (ImageButton)findViewById(R.id.tacGrid5);
        grid[6] = (ImageButton)findViewById(R.id.tacGrid6);
        grid[7] = (ImageButton)findViewById(R.id.tacGrid7);
        grid[8] = (ImageButton)findViewById(R.id.tacGrid8);

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);


        //get the ideal pick
        int pick = aiGetPick();

        //place marker
        grid[pick].setClickable(false); //disable future clicks
        grid[pick].setImageResource(aiMarker); //place your marker
        aiGrid[pick] = true; //set this grid to occupied
        gridState[pick] = true; //set this grid to occupied
        if (didWin("ai") == 0)  //if this place didnt win, continue
        {
            turn = true; //turn over
            p1Turn();
        }
        if (didWin("ai") == 1) //it did win
        {
            //popup win thing
            builder1.setMessage("You lost the game.");
            builder1.setCancelable(false);
            builder1.setNeutralButton("Mega Bummer", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //TODO
                    //clean up and end game
                }
            });
            AlertDialog alertWon = builder1.create();
            alertWon.show();

        }
        if (didWin("ai") == 2) //it was a tie
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

    public void p1PlacePick(int pick)
    {
        //grid buttons
        grid[0] = (ImageButton)findViewById(R.id.tacGrid0);
        grid[1] = (ImageButton)findViewById(R.id.tacGrid1);
        grid[2] = (ImageButton)findViewById(R.id.tacGrid2);
        grid[3] = (ImageButton)findViewById(R.id.tacGrid3);
        grid[4] = (ImageButton)findViewById(R.id.tacGrid4);
        grid[5] = (ImageButton)findViewById(R.id.tacGrid5);
        grid[6] = (ImageButton)findViewById(R.id.tacGrid6);
        grid[7] = (ImageButton)findViewById(R.id.tacGrid7);
        grid[8] = (ImageButton)findViewById(R.id.tacGrid8);

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);

        if (!gridState[pick]) //if this grid is not occupied
        {
            if (turn) //if it is your turn
            {
                grid[pick].setClickable(false); //disable future clicks
                grid[pick].setImageResource(marker); //place your marker
                myGrid[pick] = true; //set this grid to occupied
                gridState[pick] = true; //set this grid to occupied
                if (didWin("p1") == 0)  //if this place didnt win, continue
                {
                    turn = false; //turn over
                    p2Turn();
                }
                if (didWin("p1") == 1) //it did win
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
                if (didWin("p1") == 2) //it was a tie
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

    public void getMarkers()
    {
        //load saved prefs if saved
        SharedPreferences settings = getSharedPreferences("prefs", 0);
        String token = settings.getString("token", "x");
        String color = settings.getString("color", "red");
        marker = getResources().getIdentifier(token+"_"+color, "drawable", this.getPackageName());

        if (!isOnline) //if against ai
        {
            //set ai token to the opposite
            String aiToken;
            if (token == "x") {
                aiToken = "o";
            } else {
                aiToken = "x";
            }
            String aiColor = color; //set it to p1's choice for now
            while (aiColor == color) //while its the same color
            {
                Random rn = new Random();
                switch (1 + rn.nextInt(8)) //random 1-8
                {
                    case 1: {
                        aiColor = "blue";
                        break;
                    }
                    case 2: {
                        aiColor = "brown";
                        break;
                    }
                    case 3: {
                        aiColor = "green";
                        break;
                    }
                    case 4: {
                        aiColor = "orange";
                        break;
                    }
                    case 5: {
                        aiColor = "pink";
                        break;
                    }
                    case 6: {
                        aiColor = "purple";
                        break;
                    }
                    case 7: {
                        aiColor = "red";
                        break;
                    }
                    case 8: {
                        aiColor = "teal";
                        break;
                    }
                }
            }

            aiMarker = getResources().getIdentifier(aiToken + "_" + aiColor, "drawable", this.getPackageName());

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
        grid[0] = (ImageButton)findViewById(R.id.tacGrid0);
        grid[1] = (ImageButton)findViewById(R.id.tacGrid1);
        grid[2] = (ImageButton)findViewById(R.id.tacGrid2);
        grid[3] = (ImageButton)findViewById(R.id.tacGrid3);
        grid[4] = (ImageButton)findViewById(R.id.tacGrid4);
        grid[5] = (ImageButton)findViewById(R.id.tacGrid5);
        grid[6] = (ImageButton)findViewById(R.id.tacGrid6);
        grid[7] = (ImageButton)findViewById(R.id.tacGrid7);
        grid[8] = (ImageButton)findViewById(R.id.tacGrid8);

        getMarkers();

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

        p1Turn();
    }


    //disable the back button in game
    @Override
    public void onBackPressed()
    {

    }



}

