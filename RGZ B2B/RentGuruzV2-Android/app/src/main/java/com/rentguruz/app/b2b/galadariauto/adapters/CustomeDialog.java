package com.rentguruz.app.b2b.galadariauto.adapters;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.ColorInt;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.rentguruz.app.b2b.galadariauto.model.base.UserData;
import com.rentguruz.app.b2b.galadariauto.model.parameter.DateType;
import com.rentguruz.app.b2b.galadariauto.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CustomeDialog {

    private Context context;
    final Calendar c = Calendar.getInstance();
    int mYear = c.get(Calendar.YEAR); // current year
    int mMonth = c.get(Calendar.MONTH); // current month
    int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
    int hour = c.get(Calendar.HOUR);
    int minute = c.get(Calendar.MINUTE);
    @ColorInt public static final int  colorAccent  = Color.parseColor(UserData.UiColor.primary);


    private DatePickerDialog datePickerDialog;
    public CustomeDialog(Context context) {
        this.context = context;
       // themeset();
    }

    public void themeset(){
        context = new ContextThemeWrapper(context, R.style.AppTheme);
    }

    public CustomeDialog() { }
    public void  getMonthYearDialog(OnStringListner onStringListner)
    {
       /* try {
        datePickerDialog = new DatePickerDialog((Activity) context, R.style.dlg_datePicker, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String month1;
                if (month < 9)
                    month1 = "0" + (month + 1);
                else
                    month1 = (month + 1) + "";
                onStringListner.getString(month1+"/"+String.valueOf(year).substring(2));
            }
        },  mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().findViewById(context.getResources().getIdentifier("day","id","android")).setVisibility(View.GONE);
        datePickerDialog.setTitle("Expiry Date");
        Date date = new SimpleDateFormat("MM/dd/yyyy").parse(getToday());
        datePickerDialog.getDatePicker().setMinDate(date.getTime());
        datePickerDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }*/


        try{
            Activity activity = (Activity) context;
            AppCompatActivity appCompatActivity = (AppCompatActivity) context;
            MonthYearPickerDialog pickerDialog = new MonthYearPickerDialog();
            pickerDialog.setListener(new DatePickerDialog.OnDateSetListener()
            {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int i2)
                {
                    try {
                        String monthYearStr = year + "-" + (month + 1) + "-" + i2;
                        Date DateofBirth = new SimpleDateFormat("yyyy-MM-dd").parse(monthYearStr);
                        SimpleDateFormat format1 = new SimpleDateFormat("MM/yy");
                        String parsedDateofBirth = format1.format(DateofBirth);
                        //lblExpairyDate.setText(parsedDateofBirth);
                        onStringListner.getString(parsedDateofBirth);
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            });
            pickerDialog.show(appCompatActivity.getSupportFragmentManager(), "MonthYearPickerDialog");

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static int getColorByThemeAttr(Context context, int attr, int defaultColor) {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = context.getTheme();
        boolean got = theme.resolveAttribute(attr, typedValue, true);
        return got ? typedValue.data : defaultColor;
    }

    public void getFullDate(String mindate, String maxDate, OnStringListner onStringListner)
    {
        try {
          // getColorByThemeAttr(context,R.attr.adsadsa, R.color.LightGray);


     /*       TypedValue typedValue = new TypedValue();
            Resources.Theme theme = context.getTheme();
            theme.resolveAttribute(R.attr.theme_color, typedValue, true);
            Log.e("TAG", "getFullDate: " + typedValue.data );
            typedValue.data = Color.parseColor(UserData.UiColor.primary);
            Log.e("TAG", "getFullDate: " + typedValue.data );
            //context.getTheme().applyStyle(R.style.DialogTheme, true);

            //int themeee = Integer.parseInt(context.getTheme().getResources().getResourceTypeName(R.style.DialogTheme));
            ContextThemeWrapper themeWrapper = new ContextThemeWrapper(context,R.style.DialogTheme);*/
            @StyleRes  int themeee = R.style.DialogTheme;

        datePickerDialog = new DatePickerDialog((Activity) context, themeee ,  new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
               // view.setBackground();
                String month, day;
                if (monthOfYear < 9)
                    month = "0" + (monthOfYear + 1);
                else
                    month = (monthOfYear + 1) + "";

                if (dayOfMonth < 10)
                    day = "0" + dayOfMonth;
                else
                    day = dayOfMonth + "";

                String selecteddate = month + "/" + day + "/" + year;
                onStringListner.getString(Helper.getDateDisplay(DateType.MMddyyyyS, selecteddate));
               // onStringListner.getString(month + "/" + day + "/" + year);
            }
        },mYear, mMonth, mDay);
            if (!mindate.equals("")) {
                Date dates = new SimpleDateFormat("MM/dd/yyyy").parse(mindate);
                datePickerDialog.getDatePicker().setMinDate(dates.getTime());
            }
            if (!maxDate.equals("")) {
                Date dates = new SimpleDateFormat("MM/dd/yyyy").parse(maxDate);
                datePickerDialog.getDatePicker().setMaxDate(dates.getTime());
            }
            datePickerDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getMaxDate(String mindate,String maxDate,OnStringListner onStringListner)
    {
        try {
            Calendar calendar = Calendar.getInstance();
            Date dates = new SimpleDateFormat("MM/dd/yyyy").parse(mindate);
            calendar.set(Calendar.YEAR, dates.getYear()+1900);
            calendar.set(Calendar.MONTH, dates.getMonth());
            calendar.set(Calendar.DAY_OF_MONTH, dates.getDay());
            datePickerDialog = new DatePickerDialog((Activity) context, R.style.DialogTheme, (view, year, monthOfYear, dayOfMonth) -> {
                String month, day;
                if (monthOfYear < 9)
                    month = "0" + (monthOfYear + 1);
                else
                    month = (monthOfYear + 1) + "";

                if (dayOfMonth < 10)
                    day = "0" + dayOfMonth;
                else
                    day = dayOfMonth + "";

                String selecteddate = month + "/" + day + "/" + year;
                onStringListner.getString(Helper.getDateDisplay(DateType.MMddyyyyS, selecteddate));

               // onStringListner.getString(month + "/" + day + "/" + year);
            },dates.getYear()+1900,dates.getMonth(),dates.getDay());

            if (!mindate.equals("")) {
                Date date = new SimpleDateFormat("MM/dd/yyyy").parse(mindate);
                datePickerDialog.getDatePicker().setMaxDate(date.getTime());
            }
            datePickerDialog.show();
        } catch (Exception e) {
        e.printStackTrace();
    }
    }

    public void getMinDate(String mindate,String maxDate,OnStringListner onStringListner)
    {
        try {
            Calendar calendar = Calendar.getInstance();
            Date dates = new SimpleDateFormat("MM/dd/yyyy").parse(mindate);
            calendar.set(Calendar.YEAR, dates.getYear()+1900);
            calendar.set(Calendar.MONTH, dates.getMonth());
            calendar.set(Calendar.DAY_OF_MONTH, dates.getDay());
            datePickerDialog = new DatePickerDialog((Activity) context, R.style.DialogTheme, (view, year, monthOfYear, dayOfMonth) -> {
                String month, day;
                if (monthOfYear < 9)
                    month = "0" + (monthOfYear + 1);
                else
                    month = (monthOfYear + 1) + "";

                if (dayOfMonth < 10)
                    day = "0" + dayOfMonth;
                else
                    day = dayOfMonth + "";

                String selecteddate = month + "/" + day + "/" + year;
                onStringListner.getString(Helper.getDateDisplay(DateType.MMddyyyyS, selecteddate));

               // onStringListner.getString(month + "/" + day + "/" + year);
            },dates.getYear()+1900,dates.getMonth(),dates.getDay());

            if (!mindate.equals("")) {
                Date date = new SimpleDateFormat("MM/dd/yyyy").parse(mindate);
                datePickerDialog.getDatePicker().setMinDate(date.getTime());
            }
            if (!maxDate.equals("")) {
                Date date = new SimpleDateFormat("MM/dd/yyyy").parse(maxDate);
                datePickerDialog.getDatePicker().setMaxDate(date.getTime());
            }
            datePickerDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void getFullDate(OnStringListner onStringListner)
    {
        try {
            datePickerDialog = new DatePickerDialog((Activity) context, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    String month, day;
                    if (monthOfYear < 9)
                        month = "0" + (monthOfYear + 1);
                    else
                        month = (monthOfYear + 1) + "";

                    if (dayOfMonth < 10)
                        day = "0" + dayOfMonth;
                    else
                        day = dayOfMonth + "";

                    String selecteddate = month + "/" + day + "/" + year;
                    //String selecteddate = year + "-" + month + "-" + day;

                    TimePickerDialog timePickerDialog = new TimePickerDialog((Activity) context, R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            String hourss = null, minutes = null;
                            if (hourOfDay < 9)
                                hourss = "0" + (hourOfDay);
                            else
                                hourss = String.valueOf(hourOfDay);
                            if (minute < 9)
                                minutes = "0" + (minute);
                            else
                                minutes = String.valueOf(minute);
                            String time = hourss + ":" + minutes;


                            onStringListner.getString(selecteddate + " , " + hourss + ":" + minutes);
                        }
                    },hour,minute, true);

                    timePickerDialog.show();

                    //onStringListner.getString(Helper.getDateDisplay(DateType.MMddyyyyS, selecteddate));
                    // onStringListner.getString(month + "/" + day + "/" + year);
                }
            },mYear, mMonth, mDay);
            datePickerDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void dynamicDate (){


    }

  /*  public void getDateFromXML(OnStringListner onStringListner){
        Activity activity = (Activity) context;
        SampleDateViewBinding sampleDateViewBinding = SampleDateViewBinding.inflate(activity.getLayoutInflater(), activity.findViewById(android.R.id.content), false);
        Dialog dialog = new Dialog(context);
        sampleDateViewBinding.setUiColor(UserData.UiColor);
        dialog.setContentView(sampleDateViewBinding.getRoot());
        sampleDateViewBinding.setUiColor(UserData.UiColor);
        dialog.show();
    }*/

    public String getDOB()
    {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        SimpleDateFormat md = new SimpleDateFormat("MM/dd");
        String date = simpleDateFormat.format(calendar.getTime()).toString();
        String years = year.format(calendar.getTime()).toString();
        String mds = md.format(calendar.getTime());
        int yearremove = Integer.valueOf(years) - UserData.companyModel.MinDOBAge;
        String today = mds+"/"+yearremove;
        return today;
    }

    public String getIssueDate(String date)
    {
        String[] userdate = date.split("/");
        String month = userdate[0];
        String day = userdate[1];
        String year = userdate[2];
        int userYear =  Integer.valueOf(year) + UserData.companyModel.MinDOBAge;
        String issuedate = month+"/"+day+"/"+userYear;
        return issuedate;
    }

    public String getToday()
    {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return  simpleDateFormat.format(calendar.getTime()).toString();
    }

    public static String dateConvert(String date)
    {
        if (date != null){
        String[] userdate = date.split("/");
        String month = userdate[0];
        String day = userdate[1];
        String year = userdate[2];

        return year+"-"+month+"-"+day;
        }

        return "";
    }

    public static String dateConvert2(String date)
    {
        if (date != null){
            String[] userdate = date.split("/");
            String month = userdate[1];
            String day = userdate[0];
            String year = userdate[2];

            return year+"-"+month+"-"+day;
        }

        return "";
    }
    public String dateFullFormatt(String date)
    {
        if (date != null){
            String[] userdate1 = date.split("T");
            String[] userdate = userdate1[0].split("-");
            String month = userdate[0];
            String day = userdate[1];
            String year = userdate[2];

            return year+"-"+month+"-"+day;
        }

        return "";
    }

    public String dateFullFormattt(String date)
    {
        if (date != null){
            String[] userdate1 = date.split("T");
            String[] userdate = userdate1[0].split("-");
            String month = userdate[1];
            String day = userdate[2];
            String year = userdate[0];

            return month+"/"+day+"/"+year;
        }

        return "";
    }

}
