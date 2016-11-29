package com.dipesan.dani.debtcollector.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.dipesan.dani.debtcollector.Interfaces.LoginPresenter;
import com.dipesan.dani.debtcollector.Interfaces.LoginView;
import com.dipesan.dani.debtcollector.Presenters.LoginPresenterImpl;
import com.dipesan.dani.debtcollector.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Login extends AppCompatActivity implements LoginView {
    @BindView(R.id.login_id_edit_text) EditText loginIdEditText;
    @BindView(R.id.login_password_edit_text) EditText loginPasswordEditText;
    @BindView(R.id.login_sign_in_button) Button loginSignInButton;
    @BindView(R.id.login_load_progress_Bar) ProgressBar loginLoadProgressBar;
    private ImageView imageView;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        imageView = (ImageView) findViewById(R.id.login_image_view_logo);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, MainActivity.class));
                finish();
            }
        });
        presenter = new LoginPresenterImpl(this);
    }

    @Override
    public void showProgress() {
        loginLoadProgressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        loginLoadProgressBar.setVisibility(View.GONE);

    }

    @Override
    public void setErorrUser() {
        loginIdEditText.setError("ID Salah");
    }

    @Override
    public void setErorrPassword() {
        loginPasswordEditText.setError("Password Salah");
    }

    @Override
    public void setSuccess() {
        startActivity(new Intent(this, MainActivity.class));

    }

    @Override
    public void usernameEmpty() {
        loginIdEditText.setError("Masih Kosong");

    }

    @Override
    public void passwordEmpty() {
        loginPasswordEditText.setError("Masih Kosong");
    }

    @OnClick(R.id.login_sign_in_button)
    public void onClick() {
        presenter.validationUser(loginIdEditText.getText().toString(), loginPasswordEditText.getText().toString());
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }
}
