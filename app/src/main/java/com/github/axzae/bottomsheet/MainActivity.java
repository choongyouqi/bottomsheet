package com.github.axzae.bottomsheet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        findViewById(R.id.showBottomSheetBtn).setOnClickListener(this);

        setSupportActionBar(toolbar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.showBottomSheetBtn) {
            showBottomSheetDialogFullscreen();
        }
    }

    private void showBottomSheetDialogFullscreen() {
        new StatisticFragment().show(getSupportFragmentManager(), "dialog");
    }
}
