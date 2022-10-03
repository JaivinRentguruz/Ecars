package com.rentguruz.app.b2b.galadariauto.adapters;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.util.Log;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

public class RequestPermissionHandler {
    Activity activity;
    Fragment fragment;
    String[] premission;
    ActivityResultLauncher<Intent> activityResultLauncher;
    String TAG = getClass().getSimpleName();
    public RequestPermissionHandler(Activity activity, Fragment fragment, String[] premission) {
        this.activity = activity;
        this.fragment = fragment;
        this.premission = premission;
    }

    public boolean checkpermission(){
        Boolean value = false;
        if (ActivityCompat.checkSelfPermission(activity, premission[0]) == PackageManager.PERMISSION_GRANTED){
            value = true;
        }else {
            Log.e(TAG, "checkpermission: " + "Not Received" );
        }
        return  value;
    }
    public void requestpermission(Intent intent){
        activityResultLauncher.launch(intent, ActivityOptionsCompat.makeBasic());
        activityResultLauncher = fragment.registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                result.getData();
                Log.e("TAG", "onActivityResult: " + result.toString() );
            }
        });
    }


    public void doRequestpermission(){

            ActivityCompat.requestPermissions(activity,premission,1);
    }


    public void getPermission(){
        ActivityCompat.shouldShowRequestPermissionRationale(activity,premission[0]);
    }

    //private ActivityResultLauncher<String> request = fragment.registerForActivityResult(new RequestPermission(), is)
}
