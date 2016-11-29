package com.dipesan.dani.debtcollector.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dipesan.dani.debtcollector.Interfaces.MainPresenter;
import com.dipesan.dani.debtcollector.Interfaces.MainView;
import com.dipesan.dani.debtcollector.Presenters.MainPresenterImpl;
import com.dipesan.dani.debtcollector.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.dipesan.dani.debtcollector.Utils.AppConstant.MENU;
import static com.dipesan.dani.debtcollector.Utils.AppConstant.MENU_SETTINGS;

public class MainActivity extends AppCompatActivity implements MainView {
    @BindView(R.id.main_information_card_view) CardView mainInformationCardView;
    @BindView(R.id.main_progress_bar) ProgressBar mainProgressBar;
    private MainPresenter presenter;
    private ProgressDialog progressDialog;

    @BindView(R.id.main_customer_id_edit_text) EditText mainCustomerIdEditText;
    @BindView(R.id.main_proses_button) Button mainProsesButton;
    @BindView(R.id.main_information_customer_relative_layout) RelativeLayout mainInformationCustomerRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainInformationCardView.setVisibility(View.INVISIBLE);
        presenter = new MainPresenterImpl(this);
    }

    @OnClick({R.id.main_settings_image_button, R.id.main_proses_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_settings_image_button:
                Intent intentSettings = new Intent(this, MainDetailsActivity.class);
                intentSettings.putExtra(MENU, MENU_SETTINGS);
                startActivity(intentSettings);
                overridePendingTransition(0, R.anim.fade_out);
                break;
            case R.id.main_proses_button:
                presenter.ValidationCustomerId(mainCustomerIdEditText.getText().toString());
                mainInformationCardView.setVisibility(View.INVISIBLE);
                break;
        }
    }

    @Override
    public void showProgress() {
        mainProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mainProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setSuccess() {
        mainInformationCardView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setErorrCustomerId() {
        mainCustomerIdEditText.setError("Data Tidak Ada");
        mainInformationCardView.setVisibility(View.INVISIBLE);

    }

    @Override
    public void setEmptyCustomerId() {
        mainCustomerIdEditText.setError("Masih Kosong");
        mainInformationCardView.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.main_detail_button)
    public void onClickDetail() {
        LayoutInflater inflater = getLayoutInflater();
        View viewLayout = inflater.inflate(R.layout.layout_main_details, null);
        final Button payButton=(Button)viewLayout.findViewById(R.id.main_details_pay_button);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(viewLayout);
        builder.setCancelable(true);
        final AlertDialog dialog = builder.create();
        dialog.show();
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Transaksi Selesai", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                mainInformationCardView.setVisibility(View.INVISIBLE);
                mainCustomerIdEditText.setText(null);
                Toast.makeText(MainActivity.this, "Print", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
