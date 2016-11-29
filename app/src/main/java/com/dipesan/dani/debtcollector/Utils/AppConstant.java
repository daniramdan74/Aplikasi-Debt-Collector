package com.dipesan.dani.debtcollector.Utils;

import android.support.v4.app.Fragment;

import com.dipesan.dani.debtcollector.R;
import com.dipesan.dani.debtcollector.Views.Bill.BillDetailsFragment;
import com.dipesan.dani.debtcollector.Views.Bill.BillFragment;
import com.dipesan.dani.debtcollector.Views.Settings.SettingsFragment;

/**
 * Created by Dani Ramdan on 29/11/2016.
 */

public class AppConstant {
    public static final String MENU = "MENU";

    //Menu

    public static final int MENU_BILL = 1;
    public static final int MENU_SETTINGS = 2;
    public static final int MENU_BILLLING_DETAILS = 3;


    public static int title (int id){
        switch (id){
            //menu
            case MENU_BILL : return R.string.bill;
            case MENU_SETTINGS: return R.string.settings;
            case MENU_BILLLING_DETAILS: return R.string.billing_details;



        default:return R.string.app_name;
        }


    }
    public static Fragment fragment (int id){
        switch (id){

            case MENU_BILL : return BillFragment.newInstance();
            case MENU_SETTINGS : return SettingsFragment.newInstance();


            //details
            case MENU_BILLLING_DETAILS: return BillDetailsFragment.newInstance();


            default:return SettingsFragment.newInstance();
        }

    }




}
