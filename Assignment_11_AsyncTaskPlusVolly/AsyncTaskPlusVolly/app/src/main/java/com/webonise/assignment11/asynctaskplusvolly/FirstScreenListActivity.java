package com.webonise.assignment11.asynctaskplusvolly;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.gson.Gson;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FirstScreenListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen_list);
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*Json response from async task*/

        //new JsonResponseAsyncTask(this).execute();

        /*Json response from volley*/
        JsonObjectRequest jsonObjectRequest = getJsonObjectRequest();
        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }



    private JsonObjectRequest getJsonObjectRequest() {

        return new JsonObjectRequest(Constant.URL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                int listIndex =Constant.ZERO;


                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();


                JsonDataParser jsonDataParser = new JsonDataParser();
                jsonDataParser = gson.fromJson(jsonObject.toString(),JsonDataParser.class);

                List<JsonDataParser.ContactsEntity> list = new ArrayList<JsonDataParser.ContactsEntity>();
                list = jsonDataParser.getContacts();

                String[] listOfNames = new String[list.size()];

                for(JsonDataParser.ContactsEntity contactsEntity : list){
                    listOfNames[listIndex] = contactsEntity.getName();
                    listIndex++;
                }

                setListView(listOfNames);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d(getClass().getSimpleName(), volleyError.toString());
            }
        });
    }

    private void setListView(String[] listData) {
        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(arrayAdapter);
    }


}
