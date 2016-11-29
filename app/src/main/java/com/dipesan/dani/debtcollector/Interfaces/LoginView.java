package com.dipesan.dani.debtcollector.Interfaces;

/**
 * Created by Dani Ramdan on 29/11/2016.
 */

public interface LoginView {
    void showProgress();
    void hideProgress();
    void setErorrUser();
    void setErorrPassword();
    void setSuccess();
    void usernameEmpty();
    void passwordEmpty();
}
