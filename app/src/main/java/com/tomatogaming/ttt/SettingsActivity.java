package com.tomatogaming.ttt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity
{

    String token = "x";
    String color = "red";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button btnBack = (Button)findViewById(R.id.btnBack);
        RadioGroup radioXO = (RadioGroup)findViewById(R.id.radioGroupX);
        RadioGroup radioColor = (RadioGroup)findViewById(R.id.radioGroup2);

        //when the radio in XO is changed
        radioXO.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i)
            {
                switch (i)
                {
                    case R.id.radioX:
                        token = "x";
                        break;

                    case R.id.radioO:
                        token = "o";
                        break;
                }
            }
        });

        //when the group color is changed
        radioColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i)
            {
                switch (i)
                {
                    case R.id.radioBlue:
                        color = "blue";
                        break;
                    case R.id.radioBrown:
                        color = "brown";
                        break;
                    case R.id.radioGreen:
                        color = "green";
                        break;
                    case R.id.radioOrange:
                        color = "orange";
                        break;
                    case R.id.radioPink:
                        color = "pink";
                        break;
                    case R.id.radioPurple:
                        color = "purple";
                        break;
                    case R.id.radioRed:
                        color = "red";
                        break;
                    case R.id.radioTeal:
                        color = "teal";
                        break;
                }
            }
        });

        //when the back btn in settings is pushed
        btnBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //TODO
                //save settings

                //back to the main menu
                Intent menu = new Intent(SettingsActivity.this, MainMenu.class);
                startActivity(menu);
            }
        });
    }
}
