package com.example.golfth1.golfrestaurant;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class ServiceActivity extends AppCompatActivity {

    //Explicit
    private TextView textView;
    private Spinner spinner;
    private ListView listView;

    private String officerString, deskString, foodString, amountString;
    private String[] foodStrings,priceStrings, iconStrings;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        //blind Widget
        textView = (TextView) findViewById(R.id.textView2);
        spinner = (Spinner) findViewById(R.id.spinner);
        listView = (ListView) findViewById(R.id.listView);

        //Show Officer
        officerString = getIntent().getStringExtra("Officer");
        textView.setText(officerString);

        //Create Spinner
        createSpinner();
        {
        MyConnectedFood myConnectedFood = new MyConnectedFood();
            myConnectedFood.execute();

        } //create Spinner


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }// Main Method

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Service Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.golfth1.golfrestaurant/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Service Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.golfth1.golfrestaurant/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    public class MyConnectedFood extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url("http://swiftcodingthai.com/9Apr/php_get_food.php").build();
                Response response = okHttpClient.newCall(request).execute();

                return response.body().string();

            } catch (Exception e) {
                Log.d("10April", "Connected Error ==>" + e.toString());
                return null;
            }

        } // doInback

        @Override
        protected void onPostExecute(String strJSON) {
            super.onPostExecute(strJSON);

            Log.d("10April", "strJSON ===>>>" + strJSON);
            try {
                JSONArray jsonArray = new JSONArray(strJSON);

                foodStrings = new String[jsonArray.length()];
                priceStrings= new String[jsonArray.length()];
                iconStrings= new  String[jsonArray.length()];

                for (int i=0;i<jsonArray.length();i++){

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    foodStrings[i]= jsonObject.getString(MyManage.column_food);
                    priceStrings[i]= jsonObject.getString(MyManage.column_price);
                    iconStrings[i]= jsonObject.getString(MyManage.column_source);

                }//for

                //Create ListView
                MyAdapter myAdapter = new MyAdapter(ServiceActivity.this,
                        foodStrings, priceStrings, iconStrings);
                listView.setAdapter(myAdapter);

            } catch (Exception e){
                Log.d("10April", "Error strJSON ===>>>" + e.toString());
            }
        } // onPost
    }// MyconnectedFood Class

    private void createSpinner() {
        final String[] deskStrings = {"โต๊ะที่ 1", "โต๊ะที่ 2", "โต๊ะที่ 3", "โต๊ะที่ 4",
                "โต๊ะที่ 5", "โต๊ะที่ 6", "โต๊ะที่ 7", "โต๊ะที่ 8", "โต๊ะที่ 9", "โต๊ะที่ 10"};
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, deskStrings
        );

        spinner.setAdapter(stringArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                deskString = deskStrings[1];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                deskString = deskStrings[0];
            }
        });

    }// createSpinner

}//Main Class
