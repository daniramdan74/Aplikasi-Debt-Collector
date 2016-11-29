package com.dipesan.dani.debtcollector.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.dipesan.dani.debtcollector.R;
import com.dipesan.dani.debtcollector.Utils.AppConstant;

import static com.dipesan.dani.debtcollector.Utils.AppConstant.MENU;

public class MainDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_details);
        int titleId = getIntent().getIntExtra(MENU, 0);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_main_detail_activity));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(AppConstant.title(titleId)));

        setTitle(getString(AppConstant.title(titleId)));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_detail_activity_frame_layout, AppConstant.fragment(titleId)).commit();
        overridePendingTransition(0, R.anim.fade_out);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(0, R.anim.fade_out);
        super.onBackPressed();

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        overridePendingTransition(0, R.anim.fade_out);
        return super.onSupportNavigateUp();
    }
}
