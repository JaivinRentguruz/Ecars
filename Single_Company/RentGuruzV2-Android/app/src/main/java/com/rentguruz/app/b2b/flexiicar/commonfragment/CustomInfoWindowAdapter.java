package com.rentguruz.app.b2b.flexiicar.commonfragment;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.rentguruz.app.b2b.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.rentguruz.app.b2b.adapters.Helper;
import com.rentguruz.app.b2b.databinding.CustomInfowindowBinding;
import com.rentguruz.app.b2b.model.UserModel;
import com.rentguruz.app.b2b.model.base.UserData;
import com.sdsmdg.harjot.vectormaster.enums.TintMode;

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter
{
    private Activity context;

    public CustomInfoWindowAdapter(Activity context)
    {
        this.context = context;
    }
    @Override
    public View getInfoWindow(Marker marker)
    {
        View view = context.getLayoutInflater().inflate(R.layout.custom_infowindow, null);
        CustomInfowindowBinding customInfowindowBinding = CustomInfowindowBinding.inflate( context.getLayoutInflater()) ;

/*
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView tvSubTitle = (TextView) view.findViewById(R.id.tv_subtitle);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.background);
        //linearLayout.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(UserData.UiColor.primary)));
        linearLayout.getBackground().setColorFilter(Color.parseColor(UserData.UiColor.primary), PorterDuff.Mode.ADD);
        tvTitle.setText(marker.getTitle());
        tvSubTitle.setText(marker.getSnippet());
*/
       // customInfowindowBinding.background.getBackground().setColorFilter(Color.parseColor(UserData.UiColor.primary), PorterDuff.Mode.ADD);
        customInfowindowBinding.man.getPathModelByName("one").setFillColor(Color.parseColor(UserData.UiColor.primary));
       customInfowindowBinding.setUiColor(UserData.UiColor);
       customInfowindowBinding.tvTitle.setText(marker.getTitle());
       customInfowindowBinding.tvSubtitle.setText(marker.getSnippet());
        return customInfowindowBinding.getRoot();
    }

    @Override
    public View getInfoContents(Marker marker)
    {

        return null;
    }


}
