package com.rentguruz.app.b2b.galadariauto.fleetowner.registration;

import android.os.Bundle;
import com.rentguruz.app.b2b.galadariauto.R;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Owner_Registration extends AppCompatActivity
{
    private static final int AUTOCOMPLETE_REQUEST_CODE = 1;

    ArrayList<String> scanData = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_fleetowner_registration);
            //0scanData = getIntent().getStringArrayListExtra("scanData");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
