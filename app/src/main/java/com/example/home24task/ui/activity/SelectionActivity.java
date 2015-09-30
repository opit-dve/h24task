package com.example.home24task.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.example.home24task.R;
import com.example.home24task.ui.fragment.SelectionFragment;

import timber.log.Timber;

/**
 * Created by srd on 9/30/2015.
 */
public class SelectionActivity extends BaseActivity
        implements SelectionFragment.SelectionFragmentCallbacks {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_selection);

        initToolbar();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.screen_title_article_selection));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, SelectionFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public void onSelectionFragmentViewClick(View v) {
        switch (v.getId()) {
            case R.id.btn_review:
                Timber.v("Start Review Activity");
                startSingleActivity(ReviewActivity.class);
                break;
        }
    }
}
