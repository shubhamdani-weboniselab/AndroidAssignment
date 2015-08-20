package com.webonise.assignment10.basicfragmentanimationexample;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by webonise on 20/8/15.
 */
public class DetailListFragment extends android.app.Fragment implements View.OnClickListener {
    private Button btnAdd;
    private ListView listView;
    private PersonListAdapter listAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        btnAdd = (Button) view.findViewById(R.id.btnAdd);
        listView = (ListView) view.findViewById(R.id.list);
        btnAdd.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations( R.anim.slide_out_right,R.anim.slide_in_left);
        FormFragment formFragment = new FormFragment();
        transaction.replace(R.id.detailListFragment, formFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        PersonDatabaseHelper personDatabaseHelper = new PersonDatabaseHelper(getActivity());
        listAdapter = new PersonListAdapter(getActivity(), R.layout.person_list_item, personDatabaseHelper.getPersonDetailFromDB());
        listView.setAdapter(listAdapter);
        personDatabaseHelper.close();
    }


}
