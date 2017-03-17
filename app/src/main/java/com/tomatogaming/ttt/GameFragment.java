package com.tomatogaming.ttt;

import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class GameFragment extends Fragment
{
    public GameFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.activity_game, container, false);

        Button btnQuit = (Button)view.findViewById(R.id.btnQuit);
        btnQuit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
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
