package com.rentguruz.app.b2b.galadariauto.flexiicar.selfcheckin;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.rentguruz.app.b2b.galadariauto.R;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.androidnetworking.AndroidNetworking;
import com.rentguruz.app.b2b.galadariauto.adapters.CustomToast;
import com.rentguruz.app.b2b.galadariauto.apicall.ApiService;
import com.rentguruz.app.b2b.galadariauto.apicall.OnResponseListener;
import com.rentguruz.app.b2b.galadariauto.apicall.RequestType;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;
import static com.rentguruz.app.b2b.galadariauto.apicall.ApiEndPoint.BASE_URL_CHECKIN;
import static com.rentguruz.app.b2b.galadariauto.apicall.ApiEndPoint.GETAGREEMENTREPORT;

public class Fragment_Waiver_Signature_For_SelfCheckIn extends Fragment
{
    LinearLayout lblNext;
    ImageView imgback;
    WebView web_view;
    String serverpath="";
    Handler handler = new Handler();
    public static Context context;
    Bundle AgreementsBundle;
    TextView txtDateTime,txtDiscard;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_waiver_signature, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        try {
            AgreementsBundle = getArguments().getBundle("AgreementsBundle");
            txtDateTime=view.findViewById(R.id.DateTimeForSign);
            Calendar c = Calendar.getInstance();
            SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");
            String datetime = dateformat.format(c.getTime());
            txtDateTime.setText(datetime);
            web_view = view.findViewById(R.id.webview123);
            lblNext = view.findViewById(R.id.lblNEXT_WS);
            imgback = view.findViewById(R.id.backimg_WS);
            txtDiscard=view.findViewById(R.id.txtDiscardSign);
            txtDiscard.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    NavHostFragment.findNavController(Fragment_Waiver_Signature_For_SelfCheckIn.this)
                            .navigate(R.id.action_WaiverSign_ForSelfCheckIn_to_Agreements);
                }
            });
            lblNext.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    NavHostFragment.findNavController(Fragment_Waiver_Signature_For_SelfCheckIn.this)
                            .navigate(R.id.action_WaiverSign_ForSelfCheckIn_to_Agreements);
                }
            });
            imgback.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    String msg=getArguments().getString("message");
                    String transactionId=getArguments().getString("transactionId");
                    Double total=getArguments().getDouble("total");
                    Bundle Agreements = new Bundle();
                    Agreements.putInt("sendTo",4);
                    Agreements.putString("message",msg);
                    Agreements.putString("transactionId",transactionId);
                    Agreements.putDouble("total",total);
                    Agreements.putBundle("AgreementsBundle", AgreementsBundle);
                    NavHostFragment.findNavController(Fragment_Waiver_Signature_For_SelfCheckIn.this)
                            .navigate(R.id.action_WaiverSign_ForSelfCheckIn_to_Payment_Success,Agreements);
                }
            });

            SharedPreferences sp = getActivity().getSharedPreferences("FlexiiCar", MODE_PRIVATE);
            serverpath = sp.getString("serverPath", "");

            String bodyParam = "";
            try
            {
                bodyParam += "AgreementId=" + AgreementsBundle.getInt("agreement_ID");
                System.out.println(bodyParam);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            AndroidNetworking.initialize(getActivity());
            Fragment_Waiver_Signature_For_SelfCheckIn.context = getActivity();

            ApiService ApiService = new ApiService(GetAgreementReport, RequestType.GET,
                    GETAGREEMENTREPORT, BASE_URL_CHECKIN, new HashMap<String, String>(), bodyParam);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    OnResponseListener GetAgreementReport = new OnResponseListener()
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
                        Boolean status = responseJSON.getBoolean("status");

                        if (status)
                        {
                            JSONObject resultSet = responseJSON.getJSONObject("resultSet");

                            JSONObject reports=resultSet.getJSONObject("reports");
                            String reportPdfPath=reports.getString("reportPdfPath");

                            String url1 = serverpath + reportPdfPath.substring(2);
                            System.out.println(url1);
                            web_view.setWebViewClient(new WebViewClient());
                            web_view.getSettings().setJavaScriptEnabled(true);
                            web_view.getSettings().setLoadWithOverviewMode(true);
                            web_view.getSettings().setUseWideViewPort(true);
                            web_view.getSettings().setAllowFileAccess(true);
                            web_view.loadUrl("https://docs.google.com/gview?embedded=true&url="+url1);
                        }
                        else
                        {
                            String errorString = responseJSON.getString("message");
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
}

