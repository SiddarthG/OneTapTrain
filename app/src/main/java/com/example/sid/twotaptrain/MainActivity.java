package com.example.sid.twotaptrain;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.support.v7.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

   static Button button1;
   static Button button2;
    static RequestQueue RequestQueue;

    Toolbar toolbar;

    static int id;
    static String src;
    static String dstn;
    static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=(Button)findViewById(R.id.button);
        button2= (Button) findViewById(R.id.button2);
        toolbar= (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
context=this;

    }



    public void ShowList(View view){

       id= view.getId();
        FragmentManager manager = getFragmentManager();

        StationFragment dialog = new StationFragment();

        dialog.show(manager, "dialog");
    }

    public static void pass(String s){

        RequestQueue = VolleySingleton.getsInstance().getmRequestQueue();

        if(id==R.id.button){
             src=s;
            String url= null;
            try {
                url = "https://cube26-1337.0x10.info/trains?source="+ URLEncoder.encode(s, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

             if(RequestQueue==null){

                Log.d("Custom", "null");
            }
            if(RequestQueue!=null) {

                Log.d("Custom", "not null");
                JsonArrayRequest req = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Custom", "response" + response.toString());





                            try {

                                String stncode=response.getJSONObject(0).getString("sourceStationCode");


                                button1.setText(stncode);

                            }catch(Exception e) {
                                e.printStackTrace();
                                Toast.makeText(context,"exception",Toast.LENGTH_LONG).show();

                                Log.d("Custom", "Exception");
                            }






                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d("Custom","response error");
                    }
                });

                RequestQueue.add(req);
            }

        }
        if(id==R.id.button2) {
             dstn=s;
            String url = null;
            try {
                url = "https://cube26-1337.0x10.info/trains?destination="+ URLEncoder.encode(s, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if(RequestQueue==null){

                Log.d("Custom", "null");
            }
            if(RequestQueue!=null) {

                Log.d("Custom", "not null");
                JsonArrayRequest req = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Custom", "response" + response.toString());





                            try {


                                    String stncode=response.getJSONObject(0).getString("destinationStationCode");

                                button2.setText(stncode);
                            }catch(Exception e) {
                                e.printStackTrace();

                                Log.d("Custom", "Exception");
                            }






                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d("Custom","response error");
                    }
                });

                RequestQueue.add(req);
            }

        }
    }

    public void showDialogDate(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void search(View view){
        Intent intent=new Intent(MainActivity.this,NewActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
