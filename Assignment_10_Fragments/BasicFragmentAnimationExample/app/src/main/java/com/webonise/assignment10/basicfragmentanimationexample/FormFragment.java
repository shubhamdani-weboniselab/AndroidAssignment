package com.webonise.assignment10.basicfragmentanimationexample;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by webonise on 20/8/15.
 */
public class FormFragment extends android.app.Fragment implements View.OnClickListener {
    EditText etName;
    EditText etAge;
    EditText etWeight;
    EditText etHeight;
    Button btnSave;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.form_fragment, container, false);
        initializeView(view);
        return view;
    }

    @Override
    public void onClick(View view) {
        String name;
        String age;
        String height;
        String weight;

        name = etName.getText().toString();
        age = etAge.getText().toString();
        height = etHeight.getText().toString();
        weight = etWeight.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getActivity(), getString(R.string.no_name), Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(age)) {
            Toast.makeText(getActivity(), getString(R.string.no_age), Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(height)) {
            Toast.makeText(getActivity(), getString(R.string.no_height), Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(weight)) {
            Toast.makeText(getActivity(), getString(R.string.no_weight), Toast.LENGTH_LONG).show();
        } else {
            PersonDatabaseHelper personDatabaseHelper = new PersonDatabaseHelper(getActivity());
            PersonDetails personDetails = new PersonDetails();
            personDetails.setName(name);
            personDetails.setHeight(Double.parseDouble(height));
            personDetails.setWeight(Double.parseDouble(weight));
            personDetails.setAge(Integer.parseInt(age));
            personDatabaseHelper.addPersonDetailToDB(personDetails);
            personDatabaseHelper.close();

            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
            FormFragment formFragment = new FormFragment();
            transaction.remove(formFragment);
            transaction.commit();
            Toast.makeText(getActivity(), getString(R.string.success), Toast.LENGTH_LONG).show();

        }
    }

     public void initializeView (View view ) {
         btnSave = (Button) view.findViewById(R.id.btnSave);
         etName = (EditText) view.findViewById(R.id.etName);
         etAge = (EditText) view.findViewById(R.id.etAge);
         etHeight = (EditText) view.findViewById(R.id.etHeight);
         etWeight = (EditText) view.findViewById(R.id.etWeight);
         btnSave = (Button) view.findViewById(R.id.btnSave);
         btnSave.setOnClickListener(this);
     }
}

