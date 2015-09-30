package com.example.home24task.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.home24task.app.Home24App;

/**
 * Created by srd on 9/30/2015.
 */
public class BaseFragment extends Fragment {

    protected Home24App mApplication;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApplication = (Home24App) getActivity().getApplication();
    }

}
