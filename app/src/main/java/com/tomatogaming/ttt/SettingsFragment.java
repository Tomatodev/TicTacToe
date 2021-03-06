package com.tomatogaming.ttt;


import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

import static android.content.Context.MODE_PRIVATE;

public class SettingsFragment extends Fragment
{
    String token;
    String color;
    int checkXO;
    int checkColor;

    public SettingsFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.activity_settings, container, false);
        Button btnBack = (Button)view.findViewById(R.id.btnBack);
        RadioGroup radioXO = (RadioGroup)view.findViewById(R.id.radioGroupX);
        RadioGroup radioColor = (RadioGroup)view.findViewById(R.id.radioGroupColor);
        final ImageView selectedToken = (ImageView)view.findViewById(R.id.imgSelectedToken) ;

        //load saved prefs if saved
        SharedPreferences settings = getActivity().getSharedPreferences("prefs", 0);
        token = settings.getString("token", "x");
        color = settings.getString("color", "red");
        checkXO = settings.getInt("checkXO", R.id.radioX);
        checkColor = settings.getInt("checkColor", R.id.radioRed);

        //set the defaults to the loaded values
        radioXO.check(checkXO);
        radioColor.check(checkColor);
        int marker = getResources().getIdentifier(token+"_"+color, "drawable", getActivity().getPackageName());
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
                int marker = getResources().getIdentifier(token+"_"+color, "drawable", getActivity().getPackageName());
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
                int marker = getResources().getIdentifier(token+"_"+color, "drawable", getActivity().getPackageName());
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
                SharedPreferences settings = getActivity().getSharedPreferences("prefs", MODE_PRIVATE);
                SharedPreferences.Editor edit = settings.edit();

                //save the strings
                edit.putString("token", token);
                edit.putString("color", color);
                edit.putInt("checkXO", checkXO);
                edit.putInt("checkColor", checkColor);
                edit.commit();

                //back to the main menu
                FragmentManager fm = getActivity().getFragmentManager();
                if(fm.getBackStackEntryCount() > 0)
                {
                    fm.popBackStack();
                }
            }
        });

        return view;
    }

}
