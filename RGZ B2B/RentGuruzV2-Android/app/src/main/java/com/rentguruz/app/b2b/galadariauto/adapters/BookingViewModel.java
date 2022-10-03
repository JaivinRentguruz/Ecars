package com.rentguruz.app.b2b.galadariauto.adapters;


import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rentguruz.app.b2b.galadariauto.apicall.ApiService;
import com.rentguruz.app.b2b.galadariauto.apicall.OnResponseListener;
import com.rentguruz.app.b2b.galadariauto.apicall.RequestType;
import com.rentguruz.app.b2b.galadariauto.model.response.VehicleModel;

import java.util.HashMap;
import java.util.List;

import static com.rentguruz.app.b2b.galadariauto.apicall.ApiEndPoint.AVAILABLEVICHICLE;
import static com.rentguruz.app.b2b.galadariauto.apicall.ApiEndPoint.BASE_URL_CUSTOMER;

public class BookingViewModel extends ViewModel {

    private MutableLiveData<List<VehicleModel>> vehicledata;

    public LiveData<List<VehicleModel>> getVehicleList(){
        if (vehicledata == null){
            vehicledata = new MutableLiveData<List<VehicleModel>>();

        }
        return vehicledata;
    }


    private void  loaddata(){
        /*new ApiService(getVehicleList, RequestType.POST,
                AVAILABLEVICHICLE, BASE_URL_CUSTOMER, header, params.getVehicleList(20));*/
    }

    OnResponseListener getVehicleList = new OnResponseListener() {
        @Override
        public void onSuccess(String response, HashMap<String, String> headers) {
          //  Handler handler = new Handler(Looper.getMainLooper());
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    try {

                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }

        @Override
        public void onError(String error) {

        }
    };
}

