package com.webonise.assignment10.basicfragmentanimationexample;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;


public class FragmentContainerActivity extends Activity implements Communicator {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_out_right, R.anim.slide_in_left);
        DetailListFragment detailListFragment = new DetailListFragment();
        transaction.add(R.id.detailListFragment, detailListFragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {

        if (getFragmentManager().getBackStackEntryCount() > Constants.ZERO) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onDeleted() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        DetailListFragment detailListFragment = new DetailListFragment();
        transaction.replace(R.id.detailListFragment, detailListFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}
