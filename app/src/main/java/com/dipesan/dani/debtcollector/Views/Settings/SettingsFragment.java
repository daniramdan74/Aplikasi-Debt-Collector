package com.dipesan.dani.debtcollector.Views.Settings;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dipesan.dani.debtcollector.Activity.Login;
import com.dipesan.dani.debtcollector.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {


    @BindView(R.id.settings_youcube_settings_button) Button settingsYoucubeSettingsButton;
    @BindView(R.id.settings_sign_out_button) Button settingsSignOutButton;

    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.settings_youcube_settings_button, R.id.settings_sign_out_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.settings_youcube_settings_button:
                break;
            case R.id.settings_sign_out_button:
                Intent intentSettings = new Intent(getActivity(), Login.class);
                startActivity(intentSettings);
                getActivity().finish();
                getActivity().overridePendingTransition(0, R.anim.fade_out);
                break;
        }
    }

}
