package com.dipesan.dani.debtcollector.Interactors;

import android.os.Handler;

import com.dipesan.dani.debtcollector.Interfaces.LoginInteractor;
import com.dipesan.dani.debtcollector.Interfaces.OnLoginFinishListener;

/**
 * Created by Dani Ramdan on 29/11/2016.
 */

public class LoginInteractorImpl implements LoginInteractor {
    @Override
    public void validationUser(final String username, final String password, final OnLoginFinishListener listener) {
        new Handler().postDelayed (new Runnable(){

            @Override
            public void run() {

                if (username.equals("1234")&&password.equals("123")){
                    listener.exitOperation();
                }else {
                    if (!username.equals("1234")) {
                        listener.usernameErorr();
                    }
                    if (!password.equals("123")) {
                        listener.passwordErorr();
                    }
                    if (username.equals("")){
                        listener.usernameEmpty();
                    }
                    if (password.equals("")){
                        listener.passwordEmpty();
                    }
                }
            }
        },1000);

    }
}
