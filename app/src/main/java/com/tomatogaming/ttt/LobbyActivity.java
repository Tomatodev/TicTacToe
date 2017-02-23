package com.tomatogaming.ttt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.BaseGameUtils;

public class LobbyActivity extends Activity
    implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
{
    private GoogleApiClient mGoogleApiClient; //client used to interact with API
    private static int RC_SIGN_IN = 9001; //request code for sign in
    private boolean mResolvingConnectionFailure = false;
    private boolean mAutoStartSignInFlow = true;
    private boolean mSignInClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        Button btnBack = (Button)findViewById(R.id.btnBack);
        Button btnNew = (Button)findViewById(R.id.btnNew);
        Button btnJoin = (Button)findViewById(R.id.btnInvitations);
        Button btnQuickPlay = (Button)findViewById(R.id.btnQuickPlay);

        //create the API client
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Games.API)
                .addScope(Games.SCOPE_GAMES)
                .build();

        //on back press
        btnBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //TODO
                //dc gracefully

                //quit to main menu
                Intent menu = new Intent(LobbyActivity.this, MainMenu.class);
                startActivity(menu);
                finish();
            }
        });

        //on new press
        btnNew.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (mGoogleApiClient.isConnected())
                {

                }
                else
                {
                    mGoogleApiClient.connect();
                }
            }
        });

        //on join press
        btnJoin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (mGoogleApiClient.isConnected())
                {

                }
                else
                {
                    mGoogleApiClient.connect();
                }
            }
        });

        //on quick press
        btnQuickPlay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (mGoogleApiClient.isConnected())
                {

                }
                else
                {
                    mGoogleApiClient.connect();
                }
            }
        });


    }

    protected void onStart()
    {
        super.onStart();
        mGoogleApiClient.connect();
    }

    protected void onStop()
    {
        super.onStop();
        if (mGoogleApiClient.isConnected())
        {
            mGoogleApiClient.disconnect();
        }
    }

    // Shows the "sign in" bar (explanation and button).
    private void showSignInBar()
    {
        //
    }

    // Shows the "sign out" bar (explanation and button).
    private void showSignOutBar()
    {
        Toast.makeText(this, "Success",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnected(Bundle bundle)
    {
        showSignOutBar();
    }

    @Override
    public void onConnectionSuspended(int i)
    {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult)
    {

        if (mResolvingConnectionFailure)
        {
            return;
        }

        if (mSignInClicked || mAutoStartSignInFlow)
        {
            mAutoStartSignInFlow = false;
            mSignInClicked = false;
            mResolvingConnectionFailure = BaseGameUtils.resolveConnectionFailure(this, mGoogleApiClient,
                    connectionResult, RC_SIGN_IN, getString(R.string.common_google_play_services_sign_in_failed_text));
        }
        showSignInBar();
    }

    protected void onActivityResult(int requestCode, int responseCode, Intent intent)
    {
        if (requestCode == RC_SIGN_IN) {
            mSignInClicked = false;
            mResolvingConnectionFailure = false;
            if (responseCode == RESULT_OK)
            {
                mGoogleApiClient.connect();
            }
            else
            {
                BaseGameUtils.showActivityResultError(this,requestCode,responseCode, R.string.common_google_play_services_sign_in_failed_text);
            }
        }
    }
}
