package com.webonise.assignment9.basiclistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;


/**
 * Created by webonise on 14/8/15.
 */
public class PersonListAdapter extends BaseAdapter {
    List<PersonDetails> personDetailsList;
    Context context;

    public PersonListAdapter(Context context, int layout, List<PersonDetails> personDetailsList) {
        this.context = context;
        this.personDetailsList = personDetailsList;
    }

    @Override
    public int getCount() {
        return personDetailsList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {


        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.person_list_item, null);
        }
        final TextView tvName = (TextView) view.findViewById(R.id.tvName);
        TextView tvAge = (TextView) view.findViewById(R.id.tvAge);

        TextView tvWeight = (TextView) view.findViewById(R.id.tvWeight);
        TextView tvHeight = (TextView) view.findViewById(R.id.tvHeight);
        ImageButton imgButton = (ImageButton) view.findViewById(R.id.btnImage);

        tvName.setText(personDetailsList.get(position).getName());
        tvAge.setText("" + personDetailsList.get(position).getAge());
        tvWeight.setText(personDetailsList.get(position).getWeight().toString());
        tvHeight.setText(personDetailsList.get(position).getHeight().toString());

        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonDatabaseHelper personDatabaseHelper = new PersonDatabaseHelper(context);
                int id = personDetailsList.get(position).getId();
                personDatabaseHelper.deleteFromDataBase(id);
                personDatabaseHelper.close();
                ((ListViewActivity) context).onDeleted();

            }


        });


        return view;
    }


    public interface CallBack {
        void onDeleted();
    }


}
