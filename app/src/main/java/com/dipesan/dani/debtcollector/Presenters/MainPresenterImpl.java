package com.dipesan.dani.debtcollector.Presenters;

import com.dipesan.dani.debtcollector.Interactors.MainInteractorImpl;
import com.dipesan.dani.debtcollector.Interfaces.MainInteractor;
import com.dipesan.dani.debtcollector.Interfaces.MainPresenter;
import com.dipesan.dani.debtcollector.Interfaces.MainView;
import com.dipesan.dani.debtcollector.Interfaces.OnProccessFinish;

/**
 * Created by Dani Ramdan on 29/11/2016.
 */

public class MainPresenterImpl implements MainPresenter, OnProccessFinish{

    private MainView view;
    private MainInteractor interactor;

    public MainPresenterImpl(MainView view) {
        this.view = view;
        interactor = new MainInteractorImpl();
    }

    @Override
    public void ValidationCustomerId(String customerId) {
        if (view != null){
            view.showProgress();
        }
        interactor.ValidationCustomerId(customerId, this);

    }

    @Override
    public void customerIdErorr() {
        if (view!= null){
            view.hideProgress();
            view.setErorrCustomerId();
        }
    }

    @Override
    public void cutomerIdEmpty() {
        if (view!= null){
            view.hideProgress();
            view.setEmptyCustomerId();
        }
    }

    @Override
    public void exitProccess() {
        if (view!= null){
            view.hideProgress();
            view.setSuccess();
        }
    }
}
