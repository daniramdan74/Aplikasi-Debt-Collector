package com.dipesan.dani.debtcollector.Presenters;

import com.dipesan.dani.debtcollector.Interactors.LoginInteractorImpl;
import com.dipesan.dani.debtcollector.Interfaces.LoginInteractor;
import com.dipesan.dani.debtcollector.Interfaces.LoginPresenter;
import com.dipesan.dani.debtcollector.Interfaces.LoginView;
import com.dipesan.dani.debtcollector.Interfaces.OnLoginFinishListener;

/**
 * Created by Dani Ramdan on 29/11/2016.
 */

public class LoginPresenterImpl implements LoginPresenter, OnLoginFinishListener{

    private LoginView view;
    private LoginInteractor interactor;

    public LoginPresenterImpl(LoginView view) {
        this.view = view;
        interactor = new LoginInteractorImpl();
    }

    @Override
    public void validationUser(String id, String password) {
        if (view!=null){
            view.showProgress();
        }
        interactor.validationUser(id,password,this);
    }

    @Override
    public void usernameErorr() {
        if (view!= null){
         view.hideProgress();
            view.setErorrUser();
        }

    }

    @Override
    public void passwordErorr() {
        if (view!= null){
            view.hideProgress();
            view.setErorrPassword();

        }
    }

    @Override
    public void exitOperation() {
        if (view!= null){
            view.hideProgress();
            view.setSuccess();
        }
    }

    @Override
    public void usernameEmpty() {
        if (view!= null){
            view.hideProgress();
            view.usernameEmpty();
        }
    }

    @Override
    public void passwordEmpty() {
        if (view!= null) {
            view.hideProgress();
            view.passwordEmpty();
        }
}
}
