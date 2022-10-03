package com.rentguruz.app.b2b.fleetowner.ownerlogin;

import androidx.appcompat.app.AppCompatActivity;
import com.rentguruz.app.b2b.R;
import android.os.Bundle;
import android.view.WindowManager;

public class FleetOwner_Login extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_fleetowner_login);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}