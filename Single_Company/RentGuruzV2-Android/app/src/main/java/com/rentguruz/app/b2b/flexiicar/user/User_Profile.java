package com.rentguruz.app.b2b.flexiicar.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.fragment.NavHostFragment;
import com.rentguruz.app.b2b.R;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rentguruz.app.b2b.adapters.Helper;
import com.rentguruz.app.b2b.adapters.LoginRes;
import com.rentguruz.app.b2b.flexiicar.Reservation.Reservation_Activity;
import com.rentguruz.app.b2b.flexiicar.TimeLine.Timeline_MainActivity;
import com.rentguruz.app.b2b.flexiicar.booking2.Customer_Booking_Activity;
import com.rentguruz.app.b2b.home.Activity_Home;
import com.rentguruz.app.b2b.home.agreement.Activity_Agreements;
import com.rentguruz.app.b2b.home.reservation.Activity_Reservation;
import com.rentguruz.app.b2b.home.vehicles.Activity_Vehicles;
import com.rentguruz.app.b2b.model.base.UserData;
import com.rentguruz.app.b2b.model.display.Appcolor;
import com.rentguruz.app.b2b.model.response.CompanyLabel;

import java.util.ArrayList;

public class User_Profile extends AppCompatActivity
{

    ImageView home_icon;
    ImageView homeIcon,reservationIcon,vehicleIcon;
    ImageView Profile_icon,timeline,reservation;
    ArrayList<String> scanData;

    LinearLayout tab_home,tabReservation,tab_agreement,tab_Vehicles;
    ImageView activityMoreIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__profile);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

       // getWindow().getDecorView().setVisibility(View.GONE);
     //   getStatusBarHeight();

        home_icon=(ImageView) findViewById(R.id.home_icon);
        Profile_icon=(ImageView) findViewById(R.id.profile_icon);

        LinearLayout btmb2b,btmb2c;
        btmb2c = findViewById(R.id.btmb2c);
        btmb2b = findViewById(R.id.btmb2b);
        LoginRes loginRes = new LoginRes(this);
        if (loginRes.getData(getResources().getString(R.string.userType)).equals("1")){
            btmb2c.setVisibility(View.VISIBLE);
            btmb2b.setVisibility(View.GONE);
        } else {
            btmb2b.setVisibility(View.VISIBLE);
            btmb2c.setVisibility(View.GONE);
        }


        home_icon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (home_icon.isActivated()){
                    home_icon.setActivated(false);
                } else {
                    home_icon.setActivated(true);
                }

                //Intent intent1=(new Intent(getApplicationContext(), Booking_Activity.class));
                Intent intent1=(new Intent(getApplicationContext(), Customer_Booking_Activity.class));
                startActivity(intent1);
            }
        });

        timeline = findViewById(R.id.timeline);
        //timeline.setColorFilter(getResources().getColor(R.color.bottommenuactivecolor));
        timeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=(new Intent(getApplicationContext(), Timeline_MainActivity.class));
                startActivity(intent1);
            }
        });

        reservation = findViewById(R.id.reservation);

        reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=(new Intent(getApplicationContext(), Reservation_Activity.class));
                startActivity(intent1);
            }
        });

        if(Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19)
        { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        }
        else if(Build.VERSION.SDK_INT >= 19)
        {

            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }

        try {
            scanData = getIntent().getStringArrayListExtra("scanData");
            if (scanData != null){
                NavHostFragment hostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
                NavController navController = hostFragment.getNavController();
                NavGraph navGraph = navController.getNavInflater().inflate(R.navigation.nav_graph_user);
                navGraph.setStartDestination(R.id.DrivingLicense_Add);
                navController.setGraph(navGraph);
            }
            if (Helper.skipScan){
                NavHostFragment hostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
                NavController navController = hostFragment.getNavController();
                NavGraph navGraph = navController.getNavInflater().inflate(R.navigation.nav_graph_user);
                navGraph.setStartDestination(R.id.DrivingLicense_Add);
                navController.setGraph(navGraph);
                Helper.skipScan = false;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }


        tab_home=findViewById(R.id.tab_home);
        tabReservation=findViewById(R.id.tabReservation);
        tab_agreement=findViewById(R.id.tab_agreement);
        tab_Vehicles=findViewById(R.id.tab_Vehicles);
        activityMoreIcon = findViewById(R.id.activityMoreIcon);
     //   activityMoreIcon.setImageDrawable(getDrawable(R.drawable.ic_tab_more2));

        btmb2c.setBackgroundColor(Color.parseColor(UserData.UiColor.primary));
        btmb2b.setBackgroundColor(Color.parseColor(UserData.UiColor.secondary));
        activityMoreIcon.setColorFilter(Color.parseColor(UserData.UiColor.primary));
        Profile_icon.setColorFilter(Color.parseColor(UserData.UiColor.primary));
        LinearLayout bottommenu = findViewById(R.id.bottommenu);
        bottommenu.setBackgroundColor(Color.parseColor(UserData.UiColor.secondary));
        tabReservation.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                Intent i = new Intent(User_Profile.this, Activity_Reservation.class);
                startActivity(i);
            }
        });

        tab_home.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(User_Profile.this, Activity_Home.class);
                startActivity(i);
            }
        });

        tab_agreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(User_Profile.this, Activity_Agreements.class);
                startActivity(i);
            }
        });

        tab_Vehicles.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(User_Profile.this, Activity_Vehicles.class);
                startActivity(i);
            }
        });

        try {
            setLable(UserData.loginResponse.CompanyLabel);
            new  Activity_Home().setLable(UserData.loginResponse.CompanyLabel);
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            homeIcon = findViewById(R.id.homeIcon);
            reservationIcon = findViewById(R.id.reservationIcon);
            vehicleIcon = findViewById(R.id.vehicleIcon);

            Appcolor appcolor = new Appcolor();
           // LoginRes loginRes = new LoginRes(getApplicationContext());
            appcolor = loginRes.getModel( loginRes.getData(getString(R.string.Appcolor)), Appcolor.class);
            bottommenu.setBackgroundColor(Color.parseColor(appcolor.SecondColor));
            homeIcon.setColorFilter(Color.parseColor(appcolor.PrimaryColor));
            reservationIcon.setColorFilter(Color.parseColor(appcolor.PrimaryColor));
            vehicleIcon.setColorFilter(Color.parseColor(appcolor.PrimaryColor));
            activityMoreIcon.setColorFilter(Color.parseColor(appcolor.PrimaryColor));


        } catch (Exception e){
            e.printStackTrace();
        }


    }
   /* @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }*/

    public void BottomnavVisible()
    {
        LinearLayout lblcontinue1 = (LinearLayout) findViewById(R.id.lblcontinue1);
        lblcontinue1.setVisibility(View.VISIBLE);

        LinearLayout MainFragment = (LinearLayout) findViewById(R.id.MainFragment);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) MainFragment.getLayoutParams();
        params.setMargins(0, 0, 0, 0);
        MainFragment.setLayoutParams(params);
    }

    public void BottomnavInVisible()
    {
        LinearLayout lblcontinue1 = (LinearLayout) findViewById(R.id.lblcontinue1);
        lblcontinue1.setVisibility(View.GONE);

        LinearLayout MainFragment = (LinearLayout) findViewById(R.id.MainFragment);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) MainFragment.getLayoutParams();
        params.setMargins(0, 0, 0, 0);
        MainFragment.setLayoutParams(params);
    }


    private int getStatusBarHeight() {
        int height;
        Resources myResources = getResources();
        int idStatusBarHeight = myResources.getIdentifier( "status_bar_height", "dimen", "android");
        if (idStatusBarHeight > 0) {
            height = getResources().getDimensionPixelSize(idStatusBarHeight);
            Toast.makeText(this, "Status Bar Height = " + height, Toast.LENGTH_LONG).show();
        } else {
            height = 0;
            Toast.makeText(this, "Resources NOT found", Toast.LENGTH_LONG).show();
        }
        return height;
    }
public void tets (View view){

}

    public void setLable( CompanyLabel companyLabel){
        try {
            if (companyLabel !=null){

            } else {
                companyLabel = new CompanyLabel();
            }
            TextView textView =findViewById(R.id.txthome);
            textView.setTextColor(Color.parseColor(UserData.UiColor.primary));
            TextView reservation = findViewById(R.id.txtreservation);
            reservation.setText(companyLabel.Reservation);
            reservation.setTextColor(Color.parseColor(UserData.UiColor.primary));
            TextView agreement =findViewById(R.id.txtagreement);
            agreement.setText(companyLabel.Agreement);
            agreement.setTextColor(Color.parseColor(UserData.UiColor.primary));
            TextView vehicle = findViewById(R.id.txtvehicle);
            vehicle.setText(companyLabel.Vehicle);
            vehicle.setTextColor(Color.parseColor(UserData.UiColor.primary));
            TextView more = findViewById(R.id.more);
            more.setTextColor(Color.parseColor(UserData.UiColor.primary));
            //textView.setText(UserData.loginResponse.CompanyLabel.);

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}