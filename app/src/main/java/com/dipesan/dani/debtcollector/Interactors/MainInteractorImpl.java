package com.dipesan.dani.debtcollector.Interactors;

import android.os.Handler;

import com.dipesan.dani.debtcollector.Interfaces.MainInteractor;
import com.dipesan.dani.debtcollector.Interfaces.OnProccessFinish;

/**
 * Created by Dani Ramdan on 29/11/2016.
 */

public class MainInteractorImpl implements MainInteractor {
    @Override
    public void ValidationCustomerId(final String id, final OnProccessFinish proccessFinish) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (id.equals("12345678")){
                    proccessFinish.exitProccess();
                }else {
                    if (!id.equals("12345678")) {
                        proccessFinish.customerIdErorr();
                    }
                    if (id.equals("")){
                        proccessFinish.cutomerIdEmpty();
                    }
                }
            }
        },1000);
            }

}
