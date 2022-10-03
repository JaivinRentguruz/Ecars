package com.rentguruz.app.b2b.galadariauto.flexiicar.selfcheckout;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.rentguruz.app.b2b.galadariauto.R;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.rentguruz.app.b2b.galadariauto.adapters.ImageOptionMenu;
import com.rentguruz.app.b2b.galadariauto.base.BaseFragment;
import com.rentguruz.app.b2b.galadariauto.databinding.FragmentVehicleImages1Binding;
import com.androidnetworking.AndroidNetworking;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.rentguruz.app.b2b.galadariauto.adapters.CustomToast;
import com.rentguruz.app.b2b.galadariauto.adapters.Helper;
import com.rentguruz.app.b2b.galadariauto.adapters.LoginRes;
import com.rentguruz.app.b2b.galadariauto.apicall.ApiService;
import com.rentguruz.app.b2b.galadariauto.apicall.OnResponseListener;
import com.rentguruz.app.b2b.galadariauto.model.AttachmentsModel;
import com.rentguruz.app.b2b.galadariauto.model.base.UserData;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import static com.rentguruz.app.b2b.galadariauto.apicall.ApiEndPoint.UPLOADIMAGE;

public class Fragment_Vehicle_Image_1 extends BaseFragment
{
    LinearLayout lblNext;
    ImageView imgback,UploadImg,defaultimage;
    private static final int RESULT_LOAD_IMAGE = 1;
    public String id="";
    JSONArray ImageList = new JSONArray();
    TextView txt_DateTime,txtDiscard,imagecount,carImageType;
    Bundle AgreementsBundle;
    Bundle bundle = new Bundle();
    Handler handler = new Handler(Looper.getMainLooper());
    LoginRes loginRes;
    List<AttachmentsModel> attachmentsModelList = new ArrayList<>();
    int screen,count = 1;
    Boolean next = false;
    public Dialog fullProgressbar;
    LocationManager locationManager ;
    boolean GpsStatus ;
    private static final int REQUEST_LOCATION = 1;
    public String useraddress;
    public static String lat,log;
    ImageOptionMenu optionMenu;
    ActivityResultLauncher<Intent> activityResultLauncher;
    FragmentVehicleImages1Binding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        loginRes = new LoginRes(getContext());
        binding = FragmentVehicleImages1Binding.inflate(inflater,container,false);
        // Inflate the layout for this fragment
     //   return inflater.inflate(R.layout.fragment_vehicle_images_1, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        binding.setUiColor(UiColor);
        binding.header.screenHeader.setText("Self " + companyLabel.CheckOut);
        optionMenu = new ImageOptionMenu(getActivity());
       try {

           fullProgressbar = new Dialog(getContext());
           fullProgressbar.setCancelable(false);
           fullProgressbar.setContentView(R.layout.custom_progresss);

           if (!CheckGpsStatus()) {
               Intent intent1 = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
               startActivity(intent1);
               fullProgressbar.hide();
           }
           getCurrentLocation();
           fullProgressbar.hide();
           bundle.putSerializable("reservation",getArguments().getSerializable("reservation"));
        bundle.putInt( "Id", getArguments().getInt("Id"));
        Log.e("TAG", "RID " + getArguments().getInt("Id"));
        screen = getArguments().getInt("length");
        count = getArguments().getInt("temp");
        attachmentsModelList = (List<AttachmentsModel>) getArguments().getSerializable("image");
           Log.d("TAG", "onViewCreated: " + attachmentsModelList.size());
           txt_DateTime=view.findViewById(R.id.txt_DateTimeForPic);
           bundle.putSerializable("resrvation",getArguments().getSerializable("resrvation"));
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");
        String datetime = dateformat.format(c.getTime());
        txt_DateTime.setText(datetime);
        imagecount = view.findViewById(R.id.imagecount);
        lblNext=view.findViewById(R.id.lblback_forntside);
        imgback=view.findViewById(R.id.back);
        defaultimage = view.findViewById(R.id.FrontImageView);
        carImageType = view.findViewById(R.id.carImageType);
        AgreementsBundle = getArguments().getBundle("AgreementsBundle");
        UploadImg= view.findViewById(R.id.FrontImage_Upload);
       /* if (getArguments().getInt("temp") == 0)
            count = 1;*/
        imagecount.setText("Image " + count +" of 10");
           binding.image.setBackground(userDraw.getImageUpload());
        if (count==1) {
            defaultimage.setImageDrawable(getContext().getDrawable(R.drawable.car_image_1));
            carImageType.setText("Left Side");
        } else if (count==2) {
            defaultimage.setImageDrawable(getContext().getDrawable(R.drawable.car_image_2));
            carImageType.setText("Right Side");
        } else if (count==3) {
            defaultimage.setImageDrawable(getContext().getDrawable(R.drawable.car_image_3));
            carImageType.setText("Front Side");
        } else if (count==4) {
            defaultimage.setImageDrawable(getContext().getDrawable(R.drawable.car_image_4));
            carImageType.setText("Right & Back Side");
        } else if (count==5) {
            defaultimage.setImageDrawable(getContext().getDrawable(R.drawable.car_image_5));
            carImageType.setText("Left Side");
        } else if (count==6) {
            defaultimage.setImageDrawable(getContext().getDrawable(R.drawable.car_image_6));
            carImageType.setText("Back Side");
        } else if (count==7) {
            defaultimage.setImageDrawable(getContext().getDrawable(R.drawable.car_image_7));
            carImageType.setText("Steering");
        } else if (count==8) {
            defaultimage.setImageDrawable(getContext().getDrawable(R.drawable.car_image_8));
            carImageType.setText("Interior Front Seat");
        }else if (count==9) {
            defaultimage.setImageDrawable(getContext().getDrawable(R.drawable.car_image_9));
            carImageType.setText("Interior Back Seat");
        } else if (count==10) {
            defaultimage.setImageDrawable(getContext().getDrawable(R.drawable.car_image_10));
            carImageType.setText("Boot Space");
        }

           lblNext.setOnClickListener(new View.OnClickListener()
           {
               @Override
               public void onClick(View v)
               {

                   bundle.putSerializable("image", (Serializable) attachmentsModelList);
                   Log.d("TAG", "onClick: " + attachmentsModelList.size() + " : " + getArguments().getInt("length"));
                   if (screen==1){
                       NavHostFragment.findNavController(Fragment_Vehicle_Image_1.this)
                               .navigate(R.id.allvechicelimage,bundle);
                   } else {

                       bundle.putInt("length",getArguments().getInt("length")-1);
                       bundle.putInt("temp",getArguments().getInt("temp")+1);
                       NavHostFragment.findNavController(Fragment_Vehicle_Image_1.this)
                               .navigate(R.id.same,bundle);
                   }


                   /*if (next){

                   bundle.putSerializable("image", (Serializable) attachmentsModelList);
                   Log.d("TAG", "onClick: " + attachmentsModelList.size() + " : " + getArguments().getInt("length"));
                   if (screen==1){
                       NavHostFragment.findNavController(Fragment_Vehicle_Image_1.this)
                               .navigate(R.id.allvechicelimage,bundle);
                   } else {

                       bundle.putInt("length",getArguments().getInt("length")-1);
                       bundle.putInt("temp",getArguments().getInt("temp")+1);
                       NavHostFragment.findNavController(Fragment_Vehicle_Image_1.this)
                               .navigate(R.id.same,bundle);
                   }

                   } else {
                       CustomToast.showToast(getActivity(),"Please Select Car Image",1);
                   }*/

               }
           });

           imgback.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Bundle Agreements=new Bundle();
                Agreements.putBundle("AgreementsBundle",AgreementsBundle);
               /* NavHostFragment.findNavController(Fragment_Vehicle_Image_1.this)
                        .navigate(R.id.action_Vehicle_Image_1_to_Self_check_Out,Agreements);*/
                NavHostFragment.findNavController(Fragment_Vehicle_Image_1.this).popBackStack();
            }
        });
           RelativeLayout sucessfullRegi = view.findViewById(R.id.sucessfull_regi);
           optionMenu.optionmenulist(sucessfullRegi, view);
           try {
           Intent intent = new Intent();
           TextView cameraopen = view.findViewById(R.id.cameraopen);
           cameraopen.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   activityResultLauncher.launch(getIntent(intent,true));
                   optionMenu.optionVisible(sucessfullRegi,false);
               }
           });

           TextView gallaryopen = view.findViewById(R.id.gallaryopen);
           gallaryopen.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                  // startActivityForResult(getIntent(intent,false), RESULT_LOAD_IMAGE);
                   Intent intent = new Intent(Intent.ACTION_PICK,
                           MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                   startActivityForResult(intent, RESULT_LOAD_IMAGE);

                   //startActivityForResult(getIntent(intent,false), RESULT_LOAD_IMAGE);
                   optionMenu.optionVisible(sucessfullRegi,false);
               }
           });

           activityResultLauncher = registerForActivityResult(
                   new ActivityResultContracts.StartActivityForResult(),
                   new ActivityResultCallback<ActivityResult>() {
                       @Override
                       public void onActivityResult(ActivityResult result) {
                           if (result.getResultCode() == Activity.RESULT_OK) {
                               Bundle buundal = result.getData().getExtras();
                               Bitmap bitmap = (Bitmap) buundal.get("data");
                               UploadImg.setImageBitmap(bitmap);

                               Uri tempUri = getImageUri(getActivity(), bitmap);
                               Log.e("TAG", "onActivityResult: " + tempUri );
                             //  storeImage(bitmap, true);
                               storeImage(bitmap);
                           }
                       }
                   }
           );

           } catch (Exception e){
               e.printStackTrace();
           }
        UploadImg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
            /*Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(intent, RESULT_LOAD_IMAGE);*/
                optionMenu.optionVisible(sucessfullRegi,true);
            }
        });


        txtDiscard=view.findViewById(R.id.discard);

        txtDiscard.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                NavHostFragment.findNavController(Fragment_Vehicle_Image_1.this).popBackStack();

                /*NavHostFragment.findNavController(Fragment_Vehicle_Image_1.this)
                        .navigate(R.id.action_Vehicle_Image_1_to_Agreements);*/
            }
        });
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK && null != data)
        {
           /// fullProgressbar.show();
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            File file=null;
            String[] imageProjection = {MediaStore.Images.Media.DATA};
           // String[] imageProjection = {selectedImage.getPath()};

            Cursor cursor =getContext().getContentResolver().query(selectedImage, imageProjection, null, null, null);
            if(cursor != null) {
                try {
                bitmap = getScaledBitmap(selectedImage,400,400);

                cursor.moveToFirst();
                int indexImage = cursor.getColumnIndex(imageProjection[0]);
                String  part_image = cursor.getString(indexImage);
                file = new File(part_image);
                bitmap = decodeFile(part_image);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
/*                BitmapFactory.Options op = new BitmapFactory.Options();
                op.inPreferredConfig = Bitmap.Config.ARGB_8888;
                bitmap = BitmapFactory.decodeFile(part_image,op);
                bitmap = bitmap.copy(op.inPreferredConfig, true);
                Canvas canvas = new Canvas(bitmap);
                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                paint.setTextSize(20);
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.RED);
                canvas.drawText(txt_DateTime.getText().toString(),bitmap.getWidth()-200,30,paint);
                // canvas.drawText("Rajkot", 10, bitmap.getWidth()-100, paint);

                Canvas canvas2 = new Canvas(bitmap);
                Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
                paint2.setTextSize(20);
                paint2.setStyle(Paint.Style.FILL);
                paint2.setColor(Color.RED);
                canvas2.drawText(useraddress, 10, bitmap.getHeight()-30,paint2);*/


               /* HashMap<String, String> header = new HashMap<>();
                header.put("FileUploadMasterId", String.valueOf(getArguments().getInt("Id")));
                header.put("Id", String.valueOf(getArguments().getInt("Id")));
                header.put("IsActive","true");
                header.put("CompanyId","1");
                header.put("fileUploadType", "17");
                AndroidNetworking.initialize(getActivity());
                ApiService apiService = new ApiService();*/

                Double file_size = Double.valueOf(String.valueOf(file.length()/1024));
                Double file_sizeMB = file_size/1024;

                Log.d("TAG", "onActivityResult: " + file_sizeMB);


                //apiService.UPLOAD_REQUEST(uploadImage,UPLOADIMAGE, header, file);

                    //bitmap = textOnImage(data.getData(), "Date", part_image);
                  //  bitmap = textOnImage2(part_image, "Date");
            }
            //imgUpload(bitmapToFile(bitmap,"jvm"));
            storeImage(bitmap);
     /*      String s =  BitMapToString(bitmap);
            Log.e("TAG", "onActivityResult: " + s );
            File file1 = new File(s);
            imgUpload(file1)*/;

      /*      try
            {
                bitmap = getScaledBitmap(selectedImage,400,400);
                JSONObject docObj = new JSONObject();
                docObj.put("Doc_For",9);
                docObj.put("VhlPictureSide", 1);
                docObj.put("fileBase64",getImagePathFromUri(selectedImage) );
                ImageList.put(docObj);
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }*/
          /*   *//*   Bitmap bitmap1 = bitmap;
                bitmap = writeTextOnDrawable(bitmap1, "Today");
                Bitmap bitmap2 = ProcessingBitmap(data.getData());*//*
                bitmap = Bitmap.createBitmap(200,200,Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                canvas.drawText("J",10,10,null);*/
                UploadImg.setImageBitmap(bitmap);

        }
    }

    public void imgUpload(File file){
        HashMap<String, String> header = new HashMap<>();
        header.put("FileUploadMasterId", String.valueOf(getArguments().getInt("Id")));
        header.put("Id", String.valueOf(UserData.reservationCheckout.ReservationCheckOutModel.Id));
        header.put("IsActive","true");
        header.put("CompanyId",String.valueOf(Helper.di));
        header.put("fileUploadType", "17");
        Log.e("TAG", "imgUpload: "+ header );
        AndroidNetworking.initialize(getActivity());
        ApiService apiService = new ApiService();
        apiService.UPLOAD_REQUEST(uploadImage,UPLOADIMAGE, header, file);
    }

    public String getImagePathFromUri(Uri contentUri)
    {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    public Bitmap textOnImage2(String image, String data) {
        File file = new File(image);
        String TAG = "Demo";
        Log.e(TAG, "textOnImage2: " + 1 );
        if (file.exists()) {
            BitmapFactory.Options op = new BitmapFactory.Options();
            op.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Log.e(TAG, "textOnImage2: " +2 );
            Bitmap bufferedImage = BitmapFactory.decodeFile(image, op);

            bufferedImage = bufferedImage.copy(op.inPreferredConfig, true);
            Log.e(TAG, "textOnImage2: "+3 );
            Canvas canvas = new Canvas(bufferedImage);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(Color.RED);
            Log.e(TAG, "textOnImage2: " +4 );
            paint.setTextSize((int) (50));
           // paint.setShadowLayer(1f, 0f, 1f, Color.WHITE);
            Rect bounds = new Rect();
            bounds.bottom =10;
            bounds.top=10;
            bounds.left=10;
            bounds.right = 10;
            Log.e(TAG, "textOnImage2: " + 5 );
            paint.getTextBounds(data, 0, data.length(), bounds);
            int x = (100)/2;
            int y = (100)/2;
            Log.e(TAG, "textOnImage2: "+ x + " : " + y);
            canvas.drawText(data, x, y, paint);
            canvas.drawBitmap(bufferedImage, null,new Rect(10,10,50,10),paint);


            return bufferedImage;
        }
        return null;

    }

    private Bitmap getScaledBitmap(Uri selectedImage, int width, int height) throws FileNotFoundException
    {
        BitmapFactory.Options sizeOptions = new BitmapFactory.Options();
        sizeOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(selectedImage), null, sizeOptions);

        int inSampleSize = calculateInSampleSize(sizeOptions, width, height);

        sizeOptions.inJustDecodeBounds = false;
        sizeOptions.inSampleSize = inSampleSize;


        return BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(selectedImage), null, sizeOptions);
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight)
    {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            // Calculate ratios of height and width to requested one
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    OnResponseListener uploadImage = new OnResponseListener() {
        @Override
        public void onSuccess(String response, HashMap<String, String> headers) {
            Log.d("TAG", "onSuccess: " + response);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                    JSONObject responseJSON = new JSONObject(response);
                    Boolean status = responseJSON.getBoolean("Status");

                        if (status)
                        {
                            JSONArray data = responseJSON.getJSONArray("Data");
                            for (int i = 0; i <data.length() ; i++) {
                                AttachmentsModel attachmentsModel = new AttachmentsModel();
                                attachmentsModel =    loginRes.getModel(data.get(i).toString(), AttachmentsModel.class);
                                attachmentsModelList.add(attachmentsModel);
                                next = true;
                                fullProgressbar.hide();
                            }



                        }
                        else
                        {
                            String msg = responseJSON.getString("Message");
                            CustomToast.showToast(getActivity(),msg,1);
                            fullProgressbar.hide();
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
            Log.d("TAG", "onError: " + error);
            fullProgressbar.hide();
        }
    };

    public Boolean CheckGpsStatus(){
        locationManager = (LocationManager)getContext().getSystemService(Context.LOCATION_SERVICE);
        assert locationManager != null;
        GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
     /*   if(GpsStatus == true) {
            textview.setText("GPS Is Enabled");
        } else {
            textview.setText("GPS Is Disabled");
        }*/
        return GpsStatus;
    }

    private void getCurrentLocation() {
        fullProgressbar.hide();
        LocationRequest locationRequest = LocationRequest.create()
                .setInterval(2000)
                .setFastestInterval(1000)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION);
            return;
        }
        LocationServices.getFusedLocationProviderClient(getActivity())
                .requestLocationUpdates(locationRequest, new LocationCallback() {
                    @Override
                    public void onLocationResult(@NonNull @NotNull LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(getActivity())
                                .removeLocationUpdates(this);
                        if (locationResult != null && locationResult.getLastLocation() != null){
                            Log.e("TAG", "onLocationResult: " + locationResult.getLocations().get(0).getLatitude() +  " : " + locationResult.getLocations().get(0).getLongitude() );
                            Geocoder geocoder;
                            List<Address> addresses;
                            geocoder = new Geocoder(getContext(), Locale.getDefault());

                            try {
                                addresses = geocoder.getFromLocation(locationResult.getLastLocation().getLatitude(), locationResult.getLastLocation().getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                                lat = String.valueOf(locationResult.getLastLocation().getLatitude());
                                log = String.valueOf(locationResult.getLastLocation().getLongitude());

                                Log.e("TAG", "onLocationResult: " + locationResult.getLastLocation().getLatitude() +  " : " + locationResult.getLastLocation().getLongitude() );
                                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                                String city = addresses.get(0).getLocality();
                                String state = addresses.get(0).getAdminArea();
                                String country = addresses.get(0).getCountryName();
                                String postalCode = addresses.get(0).getPostalCode();
                                String knownName = addresses.get(0).getFeatureName();
                                Log.e("TAG", "onLocationResult: " +  addresses.get(0));

                                Log.e("TAG", "onLocationResult: " + addresses.get(0).getMaxAddressLineIndex());

                                // useraddress =  addresses.get(0).getPremises() + " " +  knownName + " " + city + " " + state + " " + postalCode;
                                String[] a = address.split(",");
                                Log.e("TAG", "onLocationResult: " + a.length);
                                Log.e("TAG", "onLocationResult: " + a[0] + " " + a[1] + " " + a[2]   );
                                useraddress = address;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                        }
                    }
                }, Looper.getMainLooper());

    }

    public Bitmap decodeFile(String part_image){
        Bitmap bitmap =null;
        BitmapFactory.Options op = new BitmapFactory.Options();
        op.inPreferredConfig = Bitmap.Config.ARGB_8888;
        bitmap = BitmapFactory.decodeFile(part_image,op);
        bitmap = bitmap.copy(op.inPreferredConfig, true);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(10);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        canvas.drawText(txt_DateTime.getText().toString(),bitmap.getWidth()-200,30,paint);
        // canvas.drawText("Rajkot", 10, bitmap.getWidth()-100, paint);

        Canvas canvas2 = new Canvas(bitmap);
        Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2.setTextSize(10);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setColor(Color.RED);
        canvas2.drawText(useraddress, 10, bitmap.getHeight()-30,paint2);


        return bitmap;
    }

    public File bitmapToFile(Bitmap bitmap, String fileNameToSave) { // File name like "image.png"
        //create a file to write bitmap data
        File file = null;
        try {
            file = new File(Environment.getExternalStorageDirectory()
                    + File.separator
                    + fileNameToSave);
            file.createNewFile();

            Log.e("TAG", "bitmapToFile: " );

//Convert bitmap to byte array
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 0 , bos); // YOU can also save it in JPEG
            byte[] bitmapdata = bos.toByteArray();

            Log.e("TAG", "bitmapToFile: 1" );
//write the bytes in file
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
            return file;
        }catch (Exception e){
            e.printStackTrace();
            return file; // it will return null
        }
    }

    private void saveImage(Bitmap bitmap, @NonNull String name) throws IOException {
        boolean saved;
        OutputStream fos;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ContentResolver resolver = getContext().getContentResolver();
            ContentValues contentValues = new ContentValues();
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, name);
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, "DCIM/" );
            Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            fos = resolver.openOutputStream(imageUri);
        } else {
            String imagesDir = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DCIM).toString() + File.separator ;

            File file = new File(imagesDir);

            if (!file.exists()) {
                file.mkdir();
            }

            File image = new File(imagesDir, name + ".jpeg");
            fos = new FileOutputStream(image);

        }

        saved = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        fos.flush();
        fos.close();
    }
    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    private void storeImage(Bitmap image, Boolean value) {
        File pictureFile = getOutputMediaFile();
        if (pictureFile == null) {
            Log.e("TAG",
                    "Error creating media file, check storage permissions: ");// e.getMessage());
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("TAG", "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.e("TAG", "Error accessing file: " + e.getMessage());
        }
    }

    private void storeImage(Bitmap image) {
        File pictureFile = getOutputMediaFile();
        if (pictureFile == null) {
            Log.e("TAG",
                    "Error creating media file, check storage permissions: ");// e.getMessage());
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
            imgUpload(pictureFile);
        } catch (FileNotFoundException e) {
            Log.e("TAG", "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.e("TAG", "Error accessing file: " + e.getMessage());
        }
    }
    /** Create a File for saving an image or video */
    private  File getOutputMediaFile(){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
                 +"/Android/data/"
                + getContext().getPackageName()
                + "/Files");

        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
        File mediaFile;
        String mImageName="MI_"+ timeStamp+ count +".jpg";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);

        return mediaFile;
    }
    public Intent getIntent(Intent intents, boolean image){
        if(image){
            intents = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //startActivityForResult(intent,RESULT_LOAD_IMAGE);
        } else {
            //Intent i = new Intent();
            intents = new Intent();
            intents.setType("image/*");
            intents.setAction(Intent.ACTION_GET_CONTENT);
            //startActivityForResult(intent,RESULT_LOAD_IMAGE);
        }
        return intents;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fullProgressbar.hide();
        fullProgressbar.cancel();
        fullProgressbar.dismiss();
    }


    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    @Override
    public void onClick(View v) {

    }
}
