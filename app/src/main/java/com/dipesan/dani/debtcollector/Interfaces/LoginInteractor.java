package com.dipesan.dani.debtcollector.Interfaces;

/**
 * Created by Dani Ramdan on 29/11/2016.
 */

public interface LoginInteractor {

    void validationUser(String username, String password, OnLoginFinishListener listener);
}
