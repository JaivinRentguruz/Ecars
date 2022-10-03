package com.rentguruz.app.b2b.home.vehicles;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;

import com.rentguruz.app.b2b.R;
import com.rentguruz.app.b2b.adapters.CustomBindingAdapter;
import com.rentguruz.app.b2b.base.BaseFragment;
import com.rentguruz.app.b2b.databinding.FragmentVehicleInspectionBinding;
import com.rentguruz.app.b2b.model.response.VehicleModel;

public class Fragment_Vehicle_Inspection extends BaseFragment {

    FragmentVehicleInspectionBinding binding;
    VehicleModel vehicleModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentVehicleInspectionBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setUiColor(UiColor);
        binding.next.setOnClickListener(this);
        binding.basic.setOnClickListener(this);
        binding.header.screenHeader.setText(companyLabel.Vehicle  + " Inspection");
        // binding.damage.setTextColor();

        binding.header.back.setOnClickListener(this);
        binding.header.discard.setOnClickListener(this);

        binding.basic.setTextColor(Color.parseColor(UiColor.primary));
        binding.basic.setOnClickListener(this);
        binding.detailed.setOnClickListener(this);
        binding.damage.setOnClickListener(this);

        vehicleModel = new VehicleModel();
        vehicleModel  = (VehicleModel) getArguments().getSerializable("Model");
        bundle.putSerializable("Model", vehicleModel);

        CustomBindingAdapter.loadImage(binding.car.VehImage, vehicleModel.DefaultImagePath);
        binding.car.txtvehicleName.setText(vehicleModel.VehicleName);
        binding.car.txtVehicleNumber.setText(vehicleModel.VinNumber);
    }

    @Override
    protected int getFragmentLayout() {
        return binding.getRoot().getId();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next:
                /*NavHostFragment.findNavController(Fragment_Vehicle_Inspection.this)
                        .navigate(R.id.action_VehiclesInspection_to_VehiclesInspectionBasic);*/
                break;

            case R.id.discard:
            case R.id.back:
                NavHostFragment.findNavController(Fragment_Vehicle_Inspection.this).popBackStack();
                break;


            case R.id.basic:
                NavHostFragment.findNavController(Fragment_Vehicle_Inspection.this).navigate(R.id.action_vehicle_master_to_vehicle_inspection_basic,bundle);
                break;

            case R.id.detailed:
                NavHostFragment.findNavController(Fragment_Vehicle_Inspection.this).navigate(R.id.action_vehicle_master_to_vehicle_inspection_detail,bundle);
                break;
        }
    }
}
