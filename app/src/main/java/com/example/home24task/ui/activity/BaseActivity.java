package com.example.home24task.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.home24task.R;
import com.example.home24task.app.Home24App;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by srd on 9/29/2015.
 */
public class BaseActivity extends AppCompatActivity {

    protected CompositeSubscription mCompositeSubscription;
    protected Home24App mApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCompositeSubscription = new CompositeSubscription();
        mApplication = (Home24App) getApplication();
    }

    @Override
    public void onDestroy() {
        mCompositeSubscription.unsubscribe();
        super.onDestroy();
    }

    protected void initToolbar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    public void startSingleActivity(Class<?> cls) {
        Intent intent = new Intent().setClass(BaseActivity.this, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
