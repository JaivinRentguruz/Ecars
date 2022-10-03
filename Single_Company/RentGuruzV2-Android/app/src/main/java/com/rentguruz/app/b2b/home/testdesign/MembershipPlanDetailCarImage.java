package com.rentguruz.app.b2b.home.testdesign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.rentguruz.app.b2b.base.BaseFragment;
import com.rentguruz.app.b2b.databinding.QmembershipConvertibleCarimageBinding;
import com.rentguruz.app.b2b.databinding.QmembershipConvertiblePlanBinding;

public class MembershipPlanDetailCarImage extends BaseFragment {

    QmembershipConvertibleCarimageBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = QmembershipConvertibleCarimageBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        binding.setUiColor(UiColor);
    }


    @Override
    protected int getFragmentLayout() {
        return binding.getRoot().getId();
    }

    @Override
    public void onClick(View v) {

    }
}
