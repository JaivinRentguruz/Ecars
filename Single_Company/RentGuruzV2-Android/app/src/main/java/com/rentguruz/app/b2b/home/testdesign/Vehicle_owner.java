package com.rentguruz.app.b2b.home.testdesign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.fragment.NavHostFragment;

import com.rentguruz.app.b2b.R;
import com.rentguruz.app.b2b.base.BaseFragment;
import com.rentguruz.app.b2b.databinding.QmembershipBinding;
import com.rentguruz.app.b2b.databinding.QvehicleSliderBinding;

public class Vehicle_owner extends BaseFragment {

    QvehicleSliderBinding binding;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = QvehicleSliderBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        binding.setUiColor(UiColor);
        binding.header.discard.setText("Next Screen");
        binding.header.discard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(Vehicle_owner.this).navigate(R.id.membership2);
            }
        });

        actionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(), binding.slider,R.string.nav_open,R.string.nav_close);
        binding.slider.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        binding.slider.closeDrawer(GravityCompat.START);

        binding.header.back.setOnClickListener(this);
    }


    @Override
    protected int getFragmentLayout() {
        return binding.getRoot().getId();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                binding.slider.openDrawer(GravityCompat.START);
               // binding.slider.closeDrawer(GravityCompat.START);
                break;
        }
    }
}
