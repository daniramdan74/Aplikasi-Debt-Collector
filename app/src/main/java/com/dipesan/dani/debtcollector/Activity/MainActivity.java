package com.dipesan.dani.debtcollector.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import com.dipesan.dani.debtcollector.services.BluetoothConnexionManager;
import com.dipesan.dani.debtcollector.services.YoucubeService;
import com.youTransactor.uCube.LogManager;
import com.youTransactor.uCube.mdm.MDMManager;
import com.youTransactor.uCube.rpc.RPCManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.dipesan.dani.debtcollector.Utils.AppConstant.MENU;
import static com.dipesan.dani.debtcollector.Utils.AppConstant.MENU_SETTINGS;

public class MainActivity extends AppCompatActivity implements MainView {
    @BindView(R.id.main_information_card_view) CardView mainInformationCardView;
    @BindView(R.id.main_progress_bar) ProgressBar mainProgressBar;
    @BindView(R.id.main_customer_id_edit_text) EditText mainCustomerIdEditText;
    @BindView(R.id.main_proses_button) Button mainProsesButton;
    @BindView(R.id.main_information_customer_relative_layout) RelativeLayout mainInformationCustomerRelativeLayout;
    private MainPresenter presenter;
    private ProgressDialog progressDialog;
    private YoucubeService youcubeService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainInformationCardView.setVisibility(View.INVISIBLE);
        presenter = new MainPresenterImpl(this);
        youcubeService = new YoucubeService(this);
        initBluetoothConnection();
    }

    private void initBluetoothConnection() {
        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        getBaseContext().registerReceiver(BluetoothConnexionManager.getInstance(), filter);
        LogManager.initialize(this);

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        BluetoothConnexionManager.getInstance().initialize(settings);
        MDMManager.getInstance().initialize(this);
        RPCManager.getInstance().setConnexionManager(BluetoothConnexionManager.getInstance());
    }


    @OnClick({R.id.main_settings_image_button, R.id.main_proses_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_settings_image_button:
                Intent intentSettings = new Intent(this, MainDetailsActivity.class);
                intentSettings.putExtra(MENU, MENU_SETTINGS);
                startActivity(intentSettings);
                overridePendingTransition(0, R.anim.fade_out);
                finish();
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
        final Button payButton = (Button) viewLayout.findViewById(R.id.main_details_pay_button);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(viewLayout);
        builder.setCancelable(true);
        final AlertDialog dialog = builder.create();
        dialog.show();
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                youcubeService.setAmount(Double.parseDouble("5000000"));
                youcubeService.setIsMessage(true);
                youcubeService.setMessage("insert/swipe Card");
                youcubeService.enterCard(new YoucubeService.OnEnterCardListener() {
                    @Override
                    public void onApproved() {
                        dialog.dismiss();
                        Print();

//                        Toast.makeText(MainActivity.this, "Transaksi Selesai", Toast.LENGTH_SHORT).show();
//                        mainInformationCardView.setVisibility(View.INVISIBLE);
//                        mainCustomerIdEditText.setText(null);
//                        Toast.makeText(MainActivity.this, "Print", Toast.LENGTH_SHORT).show();
                    }
                });
                }
            }

            );
        }

    private void Print() {
        LayoutInflater inflater = getLayoutInflater();
        View viewLayout = inflater.inflate(R.layout.print_receipt, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(viewLayout);
        builder.setCancelable(true);
        final AlertDialog dialog = builder.create();
        Toast.makeText(MainActivity.this, "Transaksi Selesai", Toast.LENGTH_SHORT).show();
        mainInformationCardView.setVisibility(View.INVISIBLE);
        mainCustomerIdEditText.setText(null);
        dialog.show();

    }
}
