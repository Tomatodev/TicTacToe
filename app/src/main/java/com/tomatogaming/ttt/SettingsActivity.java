package com.tomatogaming.ttt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity
{
    String token;
    String color;
    int checkXO;
    int checkColor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button btnBack = (Button)findViewById(R.id.btnBack);
        RadioGroup radioXO = (RadioGroup)findViewById(R.id.radioGroupX);
        RadioGroup radioColor = (RadioGroup)findViewById(R.id.radioGroupColor);
        final ImageView selectedToken = (ImageView)findViewById(R.id.imgSelectedToken) ;

        //load saved prefs if saved
        SharedPreferences settings = getSharedPreferences("prefs", 0);
        token = settings.getString("token", "x");
        color = settings.getString("color", "red");
        checkXO = settings.getInt("checkXO", R.id.radioX);
        checkColor = settings.getInt("checkColor", R.id.radioRed);

        //set the defaults to the loaded values
        radioXO.check(checkXO);
        radioColor.check(checkColor);
        int marker = getResources().getIdentifier(token+"_"+color, "drawable", getPackageName());
        selectedToken.setImageResource(marker);

        //when the radio in XO is changed
        radioXO.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i)
            {
                checkXO = i;
                switch (i)
                {
                    case R.id.radioX:
                        token = "x";
                        break;

                    case R.id.radioO:
                        token = "o";
                        break;
                }
                //update the token
                int marker = getResources().getIdentifier(token+"_"+color, "drawable", getPackageName());
                selectedToken.setImageResource(marker);
            }
        });

        //when the group color is changed
        radioColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i)
            {
                checkColor = i;
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
                //update the token
                int marker = getResources().getIdentifier(token+"_"+color, "drawable", getPackageName());
                selectedToken.setImageResource(marker);
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
                SharedPreferences settings = getSharedPreferences("prefs", MODE_PRIVATE);
                SharedPreferences.Editor edit = settings.edit();

                //save the strings
                edit.putString("token", token);
                edit.putString("color", color);
                edit.putInt("checkXO", checkXO);
                edit.putInt("checkColor", checkColor);
                edit.commit();

                //back to the main menu
                Intent menu = new Intent(SettingsActivity.this, MainMenu.class);
                startActivity(menu);
                finish();
            }
        });
    }
}
