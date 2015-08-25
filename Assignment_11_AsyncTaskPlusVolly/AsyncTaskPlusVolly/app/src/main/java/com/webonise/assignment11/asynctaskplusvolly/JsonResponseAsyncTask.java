package com.webonise.assignment11.asynctaskplusvolly;


import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by webonise on 21/8/15.
 */

public class JsonResponseAsyncTask extends AsyncTask<String, String, JSONObject> {
    Activity activity;

    JsonResponseAsyncTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        ServerResponse jsonParser = new ServerResponse();
        return jsonParser.getJSONResponse(Constant.URL);

    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
        try {
            JSONArray jsonArray  = jsonObject.getJSONArray(Constant.CONTACTS);
            String[] listData = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                listData[i] = jsonArray.getJSONObject(i).getString(Constant.CONTACT_NAME);
            }
            ListView listView = (ListView) activity.findViewById(R.id.listView);
            ArrayAdapter arrayAdapter = new ArrayAdapter(activity, android.R.layout.simple_list_item_1, listData);
            listView.setAdapter(arrayAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
