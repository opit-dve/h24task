package com.example.home24task.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.home24task.R;
import com.example.home24task.ui.fragment.ReviewFragment;

/**
 * Created by srd on 10/1/2015.
 */
public class ReviewActivity extends BaseActivity {

    private final String FRAGMENT_TAG = "review_fragment";
    private ReviewFragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_selection);

        initToolbar();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.screen_title_review));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            mCurrentFragment = ReviewFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, mCurrentFragment, FRAGMENT_TAG)
                    .commit();
        }
        else {
            mCurrentFragment = (ReviewFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_review, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_grid:
                if (mCurrentFragment != null && mCurrentFragment.isAdded()) {
                    mCurrentFragment.setRecyclerViewLayoutManager(
                            ReviewFragment.LayoutManagerType.GRID_LAYOUT_MANAGER);
                }
                return true;
            case R.id.action_list:
                if (mCurrentFragment != null && mCurrentFragment.isAdded()) {
                    mCurrentFragment.setRecyclerViewLayoutManager(
                            ReviewFragment.LayoutManagerType.LINEAR_LAYOUT_MANAGER);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
