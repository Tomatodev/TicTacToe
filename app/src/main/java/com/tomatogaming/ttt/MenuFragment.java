package com.tomatogaming.ttt;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MenuFragment extends Fragment
{
    public MenuFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.activity_main_menu, container, false);

        Button btnSettings = (Button)view.findViewById(R.id.btnSettings);
        Button btnPlay = (Button)view.findViewById(R.id.btnPlay);
        Button btnMultiplayer = (Button)view.findViewById(R.id.btnMultiplayer);
        Button btnBattle = (Button)view.findViewById(R.id.btnBattle);

        final FragmentManager fm = getFragmentManager();

        //when the settings button is pushed
        btnSettings.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //start the settings fragment
                Fragment settingsFragment = new SettingsFragment();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.empty_layout, settingsFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        //when play is pushed
        btnPlay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //launch the game fragment
                Fragment gameFragment = new GameFragment();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.empty_layout, gameFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        //when the mp button is hit
        btnMultiplayer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //launch lobby activity

            }
        });

        //when battle btn is pushed
        btnBattle.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //launch battle activity

            }
        });

        return view;
    }

}
