package com.rentguruz.app.b2b.home;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import com.rentguruz.app.b2b.R;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;

import com.rentguruz.app.b2b.adapters.CustomBindingAdapter;
import com.rentguruz.app.b2b.adapters.CustomToast;
import com.rentguruz.app.b2b.adapters.CustomeDialog;
import com.rentguruz.app.b2b.adapters.Helper;
import com.rentguruz.app.b2b.adapters.ImageOptionMenu;
import com.rentguruz.app.b2b.apicall.ApiService;
import com.rentguruz.app.b2b.apicall.ApiService2;
import com.rentguruz.app.b2b.apicall.OnResponseListener;
import com.rentguruz.app.b2b.apicall.RequestType;
import com.rentguruz.app.b2b.base.BaseFragment;
import com.rentguruz.app.b2b.databinding.FragmentAdditionalDriverProfile2Binding;
import com.rentguruz.app.b2b.model.parameter.AttachmentType;
import com.rentguruz.app.b2b.model.response.UpdateDL;
import com.androidnetworking.AndroidNetworking;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.rentguruz.app.b2b.apicall.ApiEndPoint.BASE_URL_CUSTOMER;
import static com.rentguruz.app.b2b.apicall.ApiEndPoint.INSERTLICENCE;
import static com.rentguruz.app.b2b.apicall.ApiEndPoint.UPDATELICENCE;
import static com.rentguruz.app.b2b.apicall.ApiEndPoint.UPLOADIMAGE;

public class Fragment_Additional_Driver_Profile2 extends BaseFragment {
    private static final int RESULT_LOAD_IMAGE = 1;
    FragmentAdditionalDriverProfile2Binding binding;
    List<String> relation;
    ArrayList<String> scanData;
    UpdateDL updateDL;
    CustomeDialog dialog;
    ImageOptionMenu optionMenu;
    ActivityResultLauncher<Intent> activityResultLauncher;
    String imgId = "";
    public static Bitmap Image1, Image2;
    Intent intent;
    String imagestr;
    String storeimagepath;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        relation = new ArrayList<>();
        binding = FragmentAdditionalDriverProfile2Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        binding.setUiColor(UiColor);
        binding.test.setBackground(userDraw.getImageUpload());
        binding.test2.setBackground(userDraw.getImageUpload());
        optionMenu = new ImageOptionMenu(getActivity());
        binding.lblnextscreen.setOnClickListener(this);
        binding.edtExpiryDateDL.setOnClickListener(this);
        binding.CusDateofBirth.setOnClickListener(this);
        binding.ISSueDate.setOnClickListener(this);
        binding.DLBacksideImg.setOnClickListener(this);
        binding.DLFronsideImg.setOnClickListener(this);
        binding.header.discard.setOnClickListener(this);
        binding.header.back.setOnClickListener(this);
        dialog = new CustomeDialog(getContext());
        updateDL = new UpdateDL();
        updateDL = (UpdateDL) getArguments().getSerializable("drivinglist");

        relation.add("Parent");
        relation.add("Child");
        relation.add("Spouse");
        relation.add("Sibling");
        relation.add("GrandParents");
        relation.add("GrandChild");
        relation.add("Friend");
        relation.add("Other");



        try {
            scanData = getActivity().getIntent().getStringArrayListExtra("scanData");
            if (scanData != null) {
                for (String data : scanData) {
                    String[] datas = data.split(":");
                    if (datas[0].equals("DD Number"))
                        binding.edtDriverLicenseNO.setText(datas[1]);
                    else if (datas[0].equals("Issue Date"))
                        binding.ISSueDate.setText(userDate(datas[1], "yyyy-MM-dd"));
                    else if (datas[0].equals("Expiration Date"))
                        binding.edtExpiryDateDL.setText(userDate(datas[1], "yyyy-MM-dd"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        preference.stateCountry(binding.SpCountry,binding.SpState, updateDL.DrivingLicenceModel.IssueByCountryName,
                updateDL.DrivingLicenceModel.IssuedByStateName);

        ArrayAdapter<String> adapterCategories = new ArrayAdapter<String>( context, R.layout.spinner_layout, R.id.text1,relation);
        binding.edtRelationship.setAdapter(adapterCategories);
      //  binding.edtRelationship.setSelection(3);
        if (getArguments().getBoolean("updateLic")) {
            binding.edtRelationship.setSelection(updateDL.RelationId, true);
            try {
                if (updateDL.DrivingLicenceModel.DrivingLicenseFront.AttachmentPath.length() != 0) {
                    CustomBindingAdapter.loadImage(binding.DLFronsideImg, updateDL.DrivingLicenceModel.DrivingLicenseFront.AttachmentPath);
                }
                if (updateDL.DrivingLicenceModel.DrivingLicenseBack.AttachmentPath.length() != 0) {
                    CustomBindingAdapter.loadImage(binding.DLBacksideImg, updateDL.DrivingLicenceModel.DrivingLicenseBack.AttachmentPath);
                }
            } catch (Exception e){
                e.printStackTrace();
            }

        }

        try {
            binding.edtExpiryDateDL.setText(dialog.dateFullFormattt(updateDL.DrivingLicenceModel.ExpiryDate));
            binding.ISSueDate.setText(dialog.dateFullFormattt(updateDL.DrivingLicenceModel.IssueDate));
            binding.CusDateofBirth.setText(dialog.dateFullFormattt(updateDL.DrivingLicenceModel.DOB));
            binding.edtDriverLicenseNO.setText(updateDL.DrivingLicenceModel.Number);
            binding.edtEmail.setText(updateDL.Email);
            binding.edtTelephone.setText(updateDL.PhoneNo);
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            binding.option.cameraopen.setOnClickListener(this);
            binding.option.gallaryopen.setOnClickListener(this);
            optionMenu.optionmenulist(binding.sucessfullRegi, view);

            activityResultLauncher = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == Activity.RESULT_OK) {
                                Bundle buundal = result.getData().getExtras();
                                Bitmap bitmap = (Bitmap) buundal.get("data");
                                if (imgId.equals("2")) {
                                    binding.DLFronsideImg.setImageBitmap(bitmap);
                                    Image1 = bitmap;
                                }
                                if (imgId.equals("3")) {
                                    binding.DLBacksideImg.setImageBitmap(bitmap);
                                    Image2 = bitmap;
                                }
                            }
                        }
                    }
            );
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected int getFragmentLayout() {
        return binding.getRoot().getId();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lblnextscreen:
                if (validation()){
                    if (getArguments().getBoolean("updateLic")) {
                        new ApiService2<UpdateDL>(addLicence, RequestType.PUT,
                                UPDATELICENCE, BASE_URL_CUSTOMER,header,getModel());
                    }else {
                        new ApiService2<>(addLicence,
                                RequestType.POST, INSERTLICENCE, BASE_URL_CUSTOMER, header, getModel());
                    }
                }
                break;

            case R.id.Cus_DateofBirth:
                dialog.getMaxDate(dialog.getDOB(), "", string -> binding.CusDateofBirth.setText(string));
                break;

            case R.id.ISSueDate:
                dialog.getMinDate(dialog.getIssueDate(binding.CusDateofBirth.getText().toString()),dialog.getToday(),string -> binding.ISSueDate.setText(string));
                break;

            case R.id.edt_ExpiryDateDL:
                dialog.getFullDate(dialog.getToday(), "", string -> binding.edtExpiryDateDL.setText(string));
                break;

            case R.id.back:
            case R.id.discard:
                NavHostFragment.findNavController(Fragment_Additional_Driver_Profile2.this).popBackStack();
                break;
            case R.id.DLFronsideImg:
                imgId = "2";
                optionMenu.optionVisible(binding.sucessfullRegi,true);
                break;

            case R.id.DLBacksideImg:
                imgId = "3";
                optionMenu.optionVisible(binding.sucessfullRegi,true);
                break;

            case R.id.cameraopen:
                activityResultLauncher.launch(getIntent(intent,true));
                optionMenu.optionVisible(binding.sucessfullRegi,false);
                break;

            case R.id.gallaryopen:
                //activityResultLauncher.launch(getIntent(intent,false));
                startActivityForResult(getIntent(intent,false), RESULT_LOAD_IMAGE);
                optionMenu.optionVisible(binding.sucessfullRegi,false);

                break;
        }
    }

    public String userDate(String timemills, String dateFormat) {
        timemills = timemills.replace("/Date(", "");
        timemills = timemills.replace(")/", "");
        Log.d("Mungara", "userDate: " + timemills);

        return DateFormat.format(dateFormat, Long.parseLong(timemills)).toString();
    }


    private boolean validation(){
        boolean value = false;
        if (binding.edtDriverLicenseNO.getText().toString().equals(""))
            CustomToast.showToast(getActivity(),"Please Enter Your Driving License NO.",1);
        else if (binding.CusDateofBirth.getText().toString().equals(""))
            CustomToast.showToast(getActivity(),"Please Enter Your Date Of Birth",1);
        else if (binding.ISSueDate.getText().toString().equals(""))
            CustomToast.showToast(getActivity(),"Please Enter License Issue Date",1);
        else if (binding.edtExpiryDateDL.getText().toString().equals(""))
            CustomToast.showToast(getActivity(),"Please Enter Your Driving License Expiry Date",1);
        else if (binding.edtEmail.getText().toString().equals(""))
            CustomToast.showToast(getActivity(),"Please Enter EmailId",1);
        else if (binding.edtTelephone.getText().toString().equals(""))
            CustomToast.showToast(getActivity(),"Please Enter Contact Number",1);
        else {
            value = true;
        }
        return value;
    }

    private UpdateDL getModel(){
        updateDL.RelationId = binding.edtRelationship.getSelectedItemPosition();
        updateDL.RelationDesc = binding.edtRelationship.getSelectedItem().toString();
        updateDL.DrivingLicenceModel.DOB = Helper.setPostDate(binding.CusDateofBirth.getText().toString());
        updateDL.DrivingLicenceModel.ExpiryDate = Helper.setPostDate(binding.edtExpiryDateDL.getText().toString());
        updateDL.DrivingLicenceModel.IssueDate = Helper.setPostDate(binding.ISSueDate.getText().toString());
        updateDL.DrivingLicenceModel.Number = binding.edtDriverLicenseNO.getText().toString();
        updateDL.DOB = Helper.setPostDate(binding.CusDateofBirth.getText().toString());
        updateDL.Email = binding.edtEmail.getText().toString();
        updateDL.PhoneNo = binding.edtTelephone.getText().toString();
        updateDL.MobileNo = binding.edtTelephone.getText().toString();

        return updateDL;
    }

    OnResponseListener addLicence = new OnResponseListener() {
        @Override
        public void onSuccess(String response, HashMap<String, String> headers) {
            handler.post(() -> {
                try {
                    System.out.println("Success");
                    System.out.println(response);

                    JSONObject responseJSON = new JSONObject(response);
                    Boolean status = responseJSON.getBoolean("Status");

                    if (status) {
                        fullProgressbar.hide();
                        bundle.putSerializable("drivinglist", getModel());
                        String msg = responseJSON.getString("Message");
                        CustomToast.showToast(getActivity(), msg, 0);
                        NavHostFragment.findNavController(Fragment_Additional_Driver_Profile2.this).navigate(R.id.adddriver2_to_driverdetails,bundle);


                        try {
                            storeImage(Image1, true);
                        } catch (Exception e){
                            e.printStackTrace();
                        }

                        try {
                            storeImage(Image2, false);
                        } catch (Exception e){
                            e.printStackTrace();
                        }

                    } else {
                        String msg = responseJSON.getString("Message");
                        CustomToast.showToast(getActivity(), msg, 1);
                        fullProgressbar.hide();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        @Override
        public void onError(String error) {
            System.out.println("Error-" + error);
        }
    };

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
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK && null != data)
        {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            Bitmap bitmap = null;
            Uri targetUri = data.getData();
            try
            {
                bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(targetUri));
                Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, 500, 500, false);
                imagestr = ConvertBitmapToString(resizedBitmap);
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }

            try {
                bitmap = getScaledBitmap(selectedImage,400,400);
                Bitmap temp = bitmap;

                ByteArrayOutputStream stream=new ByteArrayOutputStream();
                temp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] image=stream.toByteArray();
                String img_str_base64 = Base64.encodeToString(image, Base64.NO_WRAP);

                if(imgId.equals("2"))
                {
                    Image1 = bitmap;
                }
                else if (imgId.equals("3"))
                {
                    Image2 = bitmap;
                }


            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(imgId.equals("2"))
            {
                binding.DLFronsideImg.setImageBitmap(bitmap);
            }
            else if (imgId.equals("3"))
            {
                binding.DLBacksideImg.setImageBitmap(bitmap);
            }
        }
    }

    private  File getOutputMediaFile(Boolean value){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
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
        String mImageName;
        if (value) {
            mImageName  = "MI_" + timeStamp + 1 + ".jpg";
        } else {
            mImageName  = "MI_" + timeStamp + 2 + ".jpg";
        }
        storeimagepath = mImageName;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        return mediaFile;
    }

    private void storeImage(Bitmap image, Boolean value) {
        File pictureFile = getOutputMediaFile(value);
        if (pictureFile == null) {
            Log.e("TAG",
                    "Error creating media file, check storage permissions: ");// e.getMessage());
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
            imgUpload(pictureFile,value);
        } catch (FileNotFoundException e) {
            Log.e("TAG", "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.e("TAG", "Error accessing file: " + e.getMessage());
        }
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

    //method to convert the selected image to base64 encoded string
    public static String ConvertBitmapToString(Bitmap bitmap)
    {
        String encodedImage = "";

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        try {
            encodedImage= URLEncoder.encode(Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT), "UTF-8");
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        return encodedImage;
    }

    public void imgUpload(File file, Boolean value){
        HashMap<String, String> headers = new HashMap<>();
        headers.put("FileUploadMasterId", String.valueOf(updateDL.DrivingLicenceModel.Id));
        headers.put("Id", String.valueOf(updateDL.DrivingLicenceModel.Id));
        headers.put("IsActive","true");
        headers.put("CompanyId", String.valueOf(Helper.id));
        headers.put("exptime",header.exptime);
        headers.put("islogin",header.islogin);
        headers.put("token",header.token);
        headers.put("mut",header.mut);
        headers.put("ut",header.ut);
        if (value) {
            headers.put("fileUploadType", String.valueOf(AttachmentType.DrivingLicenseFront));
        } else {
            headers.put("fileUploadType", String.valueOf(AttachmentType.DrivingLicenseBack));
        }
        AndroidNetworking.initialize(getActivity());
        ApiService apiService = new ApiService();
        apiService.UPLOAD_REQUEST(uploadImage,UPLOADIMAGE, header ,headers, file);
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

                          /*  NavHostFragment.findNavController(Fragment_Driver_Profile_4.this)
                                    .navigate(R.id.action_DriverProfile4_to_Complete_Register,Registration);*/


                        }
                        else
                        {
                            String msg = responseJSON.getString("Message");
                            CustomToast.showToast(getActivity(),msg,1);
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
        }
    };
}
