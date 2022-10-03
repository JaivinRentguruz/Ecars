/*
package com.rentguruz.app.b2b.galadariauto.flexiicar.booking;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.rentguruz.app.b2b.galadariauto.R;
import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;

import com.archit.calendardaterangepicker.customviews.CalendarListener;
import com.archit.calendardaterangepicker.customviews.DateRangeCalendarView;
import com.rentguruz.app.b2b.galadariauto.adapters.CustomToast;
import com.rentguruz.app.b2b.galadariauto.adapters.DateConvert;
import com.rentguruz.app.b2b.galadariauto.adapters.Helper;
import com.rentguruz.app.b2b.galadariauto.apicall.ApiService;
import com.rentguruz.app.b2b.galadariauto.apicall.ApiService2;
import com.rentguruz.app.b2b.galadariauto.apicall.OnResponseListener;
import com.rentguruz.app.b2b.galadariauto.apicall.RequestType;
import com.rentguruz.app.b2b.galadariauto.base.BaseFragment;
import com.rentguruz.app.b2b.galadariauto.databinding.FragmentSelectedLocationBinding;
import com.rentguruz.app.b2b.galadariauto.flexiicar.login.Login;
import com.rentguruz.app.b2b.galadariauto.home.reservation.Activity_Reservation;
import com.rentguruz.app.b2b.galadariauto.model.base.UserData;
import com.rentguruz.app.b2b.galadariauto.model.common.DropDown;
import com.rentguruz.app.b2b.galadariauto.model.common.OnDropDownList;
import com.rentguruz.app.b2b.galadariauto.model.parameter.DateType;
import com.rentguruz.app.b2b.galadariauto.model.response.LocationList;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.rentguruz.app.b2b.galadariauto.apicall.ApiEndPoint.AVAILABLELOCATION;
import static com.rentguruz.app.b2b.galadariauto.apicall.ApiEndPoint.BASE_URL_LOGIN;
import static com.rentguruz.app.b2b.galadariauto.apicall.ApiEndPoint.BUSINESSBOOKINGTYPE;
import static com.rentguruz.app.b2b.galadariauto.apicall.ApiEndPoint.BusinessSourceMaster;
import static com.rentguruz.app.b2b.galadariauto.apicall.ApiEndPoint.COMMONDROPDOWNSINGLE;

public class Fragment_Selected_Location   extends BaseFragment
{
    private DateRangeCalendarView calendar;
  //  TextView txtstartdate, txtenddate, txtdone, txtpickuptime, txtreturntime, txtpickup_cancel, txt_droptime_cancel, txt_location_name, txtcity, txtzipcode, txtstreet,
  //          txtreturn_location, txtreturn_city, txtreturn_street, txtreturn_zipcode,txt_cancelCal;
  //  ImageView backarrowimageView ;
  //  RelativeLayout img_plus;
  //  LinearLayout linearlayout_different_location, lbl_change_returnLoc, lbl_change_location,llLogin,LoginLayout,RegisterLayout;
  //  RelativeLayout calanderlayout, time_relative_layout, relative_layout_droptime;
   // Bundle returnLocationBundle, locationBundle;
   // Boolean locationType, initialSelect;
    int cmP_DATE_FORMAT;
   // TextView txt_Discard;
    String StarDate, EndDate;
   // public String id = "";
    //Bundle Booking;
    //boolean isPickTimeSelectedcolor = true;
   // private static final String TAG = Fragment_Selected_Location.class.getSimpleName();

    public static String startdatejourney,enddatejourney,starttimejourney,endtimejourney;
    public static  int businessmaster;
    DropDown dropDownList;
    List<OnDropDownList> data = new ArrayList<>();
    Handler handler=new Handler(Looper.getMainLooper());
    public DateConvert dateConvert = new DateConvert();
    FragmentSelectedLocationBinding binding;
    LocationList location;
    LocationList returnlocation;
    public static LocationList[]  locationListsData;
    int idd;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        location = new LocationList();
        returnlocation = new LocationList();
        binding = FragmentSelectedLocationBinding.inflate(inflater, container,false);

        binding.LoginLayout.setOnClickListener(this);
        binding.RegisterLayout.setOnClickListener(this);
        binding.imgPlus.setOnClickListener(this);
        binding.txtDiscardSelectedLoc.setOnClickListener(this);
        binding.backarrow1.setOnClickListener(this);
       */
/* binding.lblchangeLocation.setOnClickListener(this);
        binding.lblChange.setOnClickListener(this);*//*

        binding.lblstartdate.setOnClickListener(this);
        binding.txtDone.setOnClickListener(this);
        binding.txtCancelCal.setOnClickListener(this);
        binding.pickupTime.setOnClickListener(this);
        binding.returnTime.setOnClickListener(this);
        binding.pickupCancel.setOnClickListener(this);
        binding.droptimeCancel.setOnClickListener(this);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {

        super.onViewCreated(view, savedInstanceState);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        try {

            SharedPreferences sp = getActivity().getSharedPreferences("FlexiiCar", MODE_PRIVATE);
            dropDownList = (new DropDown(BUSINESSBOOKINGTYPE,Integer.parseInt(loginRes.getData("CompanyId")),true,true));

            new ApiService2<DropDown>(new OnResponseListener() {
                @Override
                public void onSuccess(String response, HashMap<String, String> headers) {
                    handler.post(() -> {
                        try {

                            JSONObject responseJSON = new JSONObject(response);
                            Boolean status = responseJSON.getBoolean("Status");
                            final JSONArray getReservationList = responseJSON.getJSONArray("Data");
                            OnDropDownList[] onDropDownLists;
                            List<String> strings = new ArrayList<>();
                            onDropDownLists = loginRes.getModel(getReservationList.toString(),OnDropDownList[].class);
                            for (int i = 0; i < onDropDownLists.length; i++) {
                                // data.add(new OnDropDownList(onDropDownLists[i].Id, onDropDownLists[i].Name));
                                OnDropDownList onDropDownList = new OnDropDownList();
                                onDropDownList =  loginRes.getModel(getReservationList.get(i).toString(), OnDropDownList.class);
                                data.add(onDropDownList);

                                strings.add(onDropDownLists[i].Name);
                            }

                            //   getSpinner(binding.makeId,strings);
                            listSpinner(data);


                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    });
                }

                @Override
                public void onError(String error) {

                }
            }, RequestType.POST, COMMONDROPDOWNSINGLE, BASE_URL_LOGIN, header, dropDownList);

            location = (LocationList) getArguments().getSerializable("model");
            returnlocation = (LocationList) getArguments().getSerializable("models");

           */
/* if (location!=null) {
                binding.txtLocationName.setText(location.Name);
                binding.txtStreetLoc.setText(Html.fromHtml(location.AddressesModel.PreviewAddress));

            }
            binding.differentLocationLayout.setVisibility(View.GONE);
            if (returnlocation!= null) {
                binding.differentLocationLayout.setVisibility(View.VISIBLE);
                binding.returnLocationName.setText(returnlocation.Name);
                binding.txtReturnStreet.setText(Html.fromHtml(returnlocation.AddressesModel.PreviewAddress));
            }*//*

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            LinearLayout lblselectedlocation = view.findViewById(R.id.lblcontinue1);
            calendar = view.findViewById(R.id.cdrvCalendar);

            //Pick Up Time Selection
            final RelativeLayout timelayout = (RelativeLayout) view.findViewById(R.id.relative_layout_time);

            for (int i = 0; i <= 47; i++)
            {
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                lp.addRule(RelativeLayout.BELOW, (200 + i - 1));
                lp.setMargins(0, 0, 0, 0);

                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                LinearLayout Timelist = (LinearLayout) inflater.inflate(R.layout.timepickup_list, (ViewGroup) view.findViewById(android.R.id.content), false);
                Timelist.setId(200 + i);
                Timelist.setLayoutParams(lp);

                final TextView txttime = (TextView) Timelist.findViewById(R.id.txt_time);
                final Button btnselect = (Button) Timelist.findViewById(R.id.btn_selecttime);
                final LinearLayout linear = (LinearLayout) Timelist.findViewById(R.id.linearlayout2);

                final int tempPosition = i;

                linear.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View arg0)
                    {
                        binding.pickupTime.setText(txttime.getText());
                        binding.returnTime.setText(txttime.getText());
                        linear.setBackgroundColor(Color.BLACK);
                        txttime.setTextColor(Color.WHITE);

                        int len = timelayout.getChildCount();

                        for (int m = 0; m < len; m++)
                        {
                            if (m != tempPosition)
                            {
                                LinearLayout llTimeLayout = timelayout.getChildAt(m).findViewById(R.id.linearlayout2);
                                TextView txtTime = timelayout.getChildAt(m).findViewById(R.id.txt_time);
                                llTimeLayout.setBackgroundColor(Color.WHITE);
                                txtTime.setTextColor(Color.BLACK);
                                binding.timeRelativeLayout.setVisibility(View.GONE);
                            }
                        }
                    }
                });

                String hour = (i / 2) + "";
                String mins = ((i % 2) * 30) + "";

                if (hour.length() == 1)
                    hour = "0" + hour;

                if (mins.length() == 1)
                    mins += "0";

                txttime.setText(hour + ":" + mins);
                timelayout.addView(Timelist);
            }

            //Drop Time Selection
            final RelativeLayout timelayout2 = (RelativeLayout) view.findViewById(R.id.droptime_layout);

            for (int i = 0; i <= 47; i++)
            {
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                lp.addRule(RelativeLayout.BELOW, (200 + i - 1));
                lp.setMargins(0, 0, 0, 0);

                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                LinearLayout Timelist = (LinearLayout) inflater.inflate(R.layout.timepickup_list, (ViewGroup) view.findViewById(android.R.id.content), false);
                Timelist.setId(200 + i);
                Timelist.setLayoutParams(lp);

                final TextView txttime = (TextView) Timelist.findViewById(R.id.txt_time);
                Button btnselect = (Button) Timelist.findViewById(R.id.btn_selecttime);
                final LinearLayout linear = (LinearLayout) Timelist.findViewById(R.id.linearlayout2);

                final int tempPosition = i;

                linear.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View arg0)
                    {
                        binding.returnTime.setText(txttime.getText());
                        linear.setBackgroundColor(Color.BLACK);
                        txttime.setTextColor(Color.WHITE);
                        int len = timelayout2.getChildCount();

                        for (int m = 0; m < len; m++)
                        {
                            if (m != tempPosition)
                            {
                                LinearLayout llTimeLayout = timelayout2.getChildAt(m).findViewById(R.id.linearlayout2);
                                TextView txtTime = timelayout2.getChildAt(m).findViewById(R.id.txt_time);
                                llTimeLayout.setBackgroundColor(Color.WHITE);
                                txtTime.setTextColor(Color.BLACK);
                                binding.timeRelativeLayout2.setVisibility(View.GONE);
                            }
                        }
                    }
                });

                String hour = (i / 2) + "";
                String mins = ((i % 2) * 30) + "";

                if (hour.length() == 1)
                    hour = "0" + hour;

                if (mins.length() == 1)
                    mins += "0";

                txttime.setText(hour + ":" + mins);
                timelayout2.addView(Timelist);
            }


            lblselectedlocation.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    //  if(!id.equals(""))
                    // {
                    startdatejourney = binding.lblstartdate.getText().toString();
                    enddatejourney = binding.lblenddate.getText().toString();
                    starttimejourney = binding.pickupTime.getText().toString();
                    endtimejourney = binding.returnTime.getText().toString();
                    bundle.putSerializable("model", location);
                    bundle.putSerializable("models", location);
                    getDropdownId(binding.businessMaster.getSelectedItem().toString());
                    businessmaster = idd;
                    if (returnlocation!= null)
                        bundle.putSerializable("models", returnlocation);
                    bundle.putString("pickupdate", DateConvert.DateConverter(DateType.monthwithdate, binding.lblstartdate.getText().toString(), DateType.yyyyMMddD));
                    bundle.putString("dropdate", DateConvert.DateConverter(DateType.monthwithdate, binding.lblenddate.getText().toString(), DateType.yyyyMMddD));
                    bundle.putString("droptime", binding.pickupTime.getText().toString());
                    bundle.putString("pickuptime",  binding.returnTime.getText().toString());
                    Helper.isCustomerVisible = false;

                    NavHostFragment.findNavController(Fragment_Selected_Location.this)
                            .navigate(R.id.action_Selected_location_to_Vehicles_Available, bundle);
                }
            });

            //calander
            calendar.setCalendarListener(calendarListener);
            Calendar current = Calendar.getInstance();
            Calendar yearAfter = Calendar.getInstance();
            yearAfter.add(Calendar.DATE, 365);
            calendar.setCurrentMonth(current);
            calendar.setSelectableDateRange(current, yearAfter);

            SimpleDateFormat df=new SimpleDateFormat("HH:mm");
            current.add(Calendar.MINUTE,60);
            String formatedtime=df.format(current.getTime());
            binding.pickupTime.setText(dateConvert.allDateConverter(DateType.defaultdate, current.getTime().toString(),DateType.time));

            SimpleDateFormat df1=new SimpleDateFormat("HH:mm");
            String formatedtime1=df1.format(current.getTime());
            binding.returnTime.setText(dateConvert.allDateConverter(DateType.defaultdate, current.getTime().toString(),DateType.time));


            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy");
            String currentDateandTime = sdf.format(new Date());
            binding.lblstartdate.setText(dateConvert.allDateConverter(DateType.defaultdate, current.getTime().toString(),DateType.monthwithdate));

            cmP_DATE_FORMAT= UserData.companyModel.MinReservationDays;
            current.add(Calendar.DATE,(cmP_DATE_FORMAT));
            String enddate = sdf.format(current.getTime());
            binding.lblenddate.setText(enddate);

            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
            StarDate = sdfDate.format(new Date());
            EndDate = sdfDate.format(current.getTime());
            if (startdatejourney != null) {
                //StarDate = "2021-10-18";
                binding.lblstartdate.setText(startdatejourney);
                binding.lblenddate.setText(enddatejourney);
                binding.returnTime.setText(endtimejourney);
                binding.pickupTime.setText(starttimejourney);

            } */
/*else {
                startdatejourney = binding.lblstartdate.getText().toString();
            }*//*


   */
/*         String pickupdatetime = StarDate + ":"+ txtpickuptime;
            String dropdatetime = EndDate + ":" + txtreturntime;
            SimpleDateFormat datewithtime = new SimpleDateFormat("yyyy-MM-dd:HH:mm");
            Date date = datewithtime.parse(pickupdatetime);*//*

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected int getFragmentLayout() {
        return binding.getRoot().getId();
    }

    private final CalendarListener calendarListener = new CalendarListener()
    {
        @Override
        public void onFirstDateSelected(@NonNull final Calendar startDate)
        {

        }

        @Override
        public void onDateRangeSelected(@NonNull final Calendar startDate, @NonNull final Calendar endDate)
        {

            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd  yyyy");
            String formattedDate = sdf.format(startDate.getTime());
            binding.lblstartdate.setText(formattedDate);
            String formattedDate1 = sdf.format(endDate.getTime());
            binding.lblenddate.setText(formattedDate1);

            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
            StarDate = sdfDate.format(startDate.getTime());

            if (endDate != null)

                EndDate = sdfDate.format(endDate.getTime());
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Login_Layout:
                */
/*Bundle Booking=new Bundle();
                Booking.putBundle("returnLocation",returnLocationBundle);
                Booking.putBundle("location",locationBundle);
                Booking.putBoolean("locationType",locationType);
                Booking.putBoolean("initialSelect",initialSelect);*//*

                bundle.putBoolean("LoginForUser",true);
                NavHostFragment.findNavController(Fragment_Selected_Location.this)
                        .navigate(R.id.action_Selected_location_to_LoginFragment,bundle);
                break;

            case R.id.Register_Layout:
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
                break;

          */
/*  case R.id.img_plus:
                binding.differentLocationLayout.setVisibility(View.VISIBLE);
                binding.returnLocationName.setText(location.Name);
                binding.txtReturnStreet.setText(Html.fromHtml(location.AddressesModel.PreviewAddress));
                break;*//*


            case  R.id.txt_DiscardSelectedLoc:
                */
/*NavHostFragment.findNavController(Fragment_Selected_Location.this)
                        .navigate(R.id.action_Selected_location_to_Search_activity);*//*

                Intent i = new Intent(getActivity(), Activity_Reservation.class);
                startActivity(i);
                break;

            case R.id.backarrow1:
       */
/*         AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Are You Sure You Want To Cancel?");
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                NavHostFragment.findNavController(Fragment_Selected_Location.this)
                                        .navigate(R.id.action_Selected_location_to_Search_activity);
                            }
                        });
                builder.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                dialog.dismiss();
                            }
                        });

                final AlertDialog dialog = builder.create();
                dialog.show();*//*

                Intent ii = new Intent(getActivity(), Activity_Reservation.class);
                startActivity(ii);
                break;
            case R.id.lblchange_location:
                bundle.putSerializable("model", location);
                bundle.putBoolean("locationType", true);
                startdatejourney = binding.lblstartdate.getText().toString();
                enddatejourney = binding.lblenddate.getText().toString();
                starttimejourney = binding.pickupTime.getText().toString();
                endtimejourney = binding.returnTime.getText().toString();
                getDropdownId(binding.businessMaster.getSelectedItem().toString());
                businessmaster = idd;
                NavHostFragment.findNavController(Fragment_Selected_Location.this)
                        .navigate(R.id.action_Selected_location_to_Available_location, bundle);
                break;

            case R.id.lbl_change:
                bundle.putSerializable("model", location);
                bundle.putBoolean("locationType", false);
                NavHostFragment.findNavController(Fragment_Selected_Location.this)
                        .navigate(R.id.action_Selected_location_to_Available_location, bundle);
                break;
            case R.id.lblstartdate:
                binding.calenderLayout.setVisibility(View.VISIBLE);
                break;

            case R.id.txt_done:
                binding.calenderLayout.setVisibility(View.GONE);
                break;
            case R.id.txt_cancelCal:
                binding.calenderLayout.setVisibility(View.GONE);
                break;
            case R.id.pickup_time:
                binding.timeRelativeLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.return_time:
                binding.timeRelativeLayout2.setVisibility(View.VISIBLE);
                break;
            case R.id.pickup_cancel:
                binding.timeRelativeLayout.setVisibility(View.GONE);
                break;
            case R.id.droptime_cancel:
                binding.timeRelativeLayout2.setVisibility(View.GONE);
                break;
        }
    }

    OnResponseListener AvailableLocation = new OnResponseListener()
    {
        @Override
        public void onSuccess(final String response, HashMap<String, String> headers)
        {
            handler.post(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        System.out.println("Success");
                        System.out.println(response);

                        JSONObject responseJSON = new JSONObject(response);
                        Boolean status = responseJSON.getBoolean("Status");

                        if (status)
                        {
                            JSONObject resultSet = responseJSON.getJSONObject("Data");
                            final JSONArray locationlist = resultSet.getJSONArray("Data");
                            locationListsData = loginRes.getModel(locationlist.toString(), LocationList[].class);
                            for (int i = 0; i <locationListsData.length ; i++) {
                                location = locationListsData[i];
                                returnlocation = locationListsData[i];
                                break;
                            }


                        }
                        else
                        {
                            String errorString = responseJSON.getString("Message");
                            CustomToast.showToast(getActivity(),errorString,1);
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            });
        }
        @Override
        public void onError(String error)
        {
            System.out.println("Error-" + error);
        }
    };


    OnResponseListener AvailableLocation2 =   new OnResponseListener() {
    @Override
    public void onSuccess(String response, HashMap<String, String> headers) {
        handler.post(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    System.out.println("Success");
                    System.out.println(response);

                    JSONObject responseJSON = new JSONObject(response);
                    Boolean status = responseJSON.getBoolean("Status");

                    if (status)
                    {
                        JSONObject resultSet = responseJSON.getJSONObject("Data");
                        final JSONArray locationlist = resultSet.getJSONArray("Data");
                        locationListsData = loginRes.getModel(locationlist.toString(), LocationList[].class);
                        bundle.putSerializable("locModel",locationListsData);
                        for (int i = 0; i <locationListsData.length ; i++) {
                            location = locationListsData[i];
                            returnlocation = locationListsData[i];
                          */
/*  binding.txtLocationName.setText(location.Name);
                            binding.txtStreetLoc.setText(Html.fromHtml(location.AddressesModel.PreviewAddress));
                            binding.returnLocationName.setText(returnlocation.Name);
                            binding.txtReturnStreet.setText(Html.fromHtml(returnlocation.AddressesModel.PreviewAddress));*//*

                            break;
                        }



                    }
                    else
                    {
                        String errorString = responseJSON.getString("Message");
                        CustomToast.showToast(getActivity(),errorString,1);
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void onError(String error) {

    }
};

    public void listSpinner(List<OnDropDownList> data){
        List<String> business = new ArrayList<>();
        int select = 0;
        for (int i = 0; i <data.size() ; i++) {
            if (data.get(i).TableType == BUSINESSBOOKINGTYPE){
                business.add(data.get(i).Name);
            }
            if (data.get(i).Id == 4){
                select = i;
            }
        }
        ArrayAdapter<String> adapterbusiness = new ArrayAdapter<String>( context, R.layout.spinner_layout, R.id.text1,business);
        binding.businessMaster.setAdapter(adapterbusiness);
        binding.businessMaster.setSelection(select);

        if (Helper.isDropdown) {
            binding.businessMaster.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    getDropdownId(binding.businessMaster.getSelectedItem().toString());
                    new ApiService(new OnResponseListener() {
                        @Override
                        public void onSuccess(String response, HashMap<String, String> headers) {
                            handler.post(() -> {
                                try {
                                    JSONObject responseJSON = new JSONObject(response);
                                    Boolean status = responseJSON.getBoolean("Status");
                                    final JSONObject getReservationList = responseJSON.getJSONObject("Data");
                                    String locationss = getReservationList.getString("LocationMasterIds");
                                    JSONArray ints = new JSONArray();
                                    for (int i = 0; i < locationss.split(",").length; i++) {
                                        ints.put(Integer.valueOf(locationss.split(",")[i].trim()));
                                    }

                                    Log.e(TAG, "run: " + location);
                                    new ApiService(AvailableLocation2, RequestType.POST, AVAILABLELOCATION, BASE_URL_LOGIN, header, params.getAvailableLocation(ints));

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });
                        }

                        @Override
                        public void onError(String error) {

                        }
                    }, RequestType.POST, BusinessSourceMaster, BASE_URL_LOGIN, header, params.getBusinessSource(idd));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    public void getDropdownId(String name){
        for (int i = 0; i <data.size(); i++) {
            if (data.get(i).Name.equals(name)){
                idd = data.get(i).Id;
            }
        }
    }
}*/
