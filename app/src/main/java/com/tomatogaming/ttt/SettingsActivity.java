package com.tomatogaming.ttt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button btnBack = (Button)findViewById(R.id.btnBack);


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
