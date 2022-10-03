package com.rentguruz.app.b2b.galadariauto.fleetowner.ownerprofile;

import android.os.Bundle;
import android.view.WindowManager;
import com.rentguruz.app.b2b.galadariauto.R;
import androidx.appcompat.app.AppCompatActivity;

public class Fleet_Owner_Profile extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fleetowner_profile);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

    }
}