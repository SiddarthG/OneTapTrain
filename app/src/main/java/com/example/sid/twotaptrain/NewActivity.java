package com.example.sid.twotaptrain;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by sid on 10/8/2015.
 */
public class NewActivity extends AppCompatActivity {
    RequestQueue nRequestQueue;

    RecyclerView.Adapter sidAdapter;
    ArrayList<Information> iList= new ArrayList<>();
    Information data;

    DateFormat dateFormat=new SimpleDateFormat("HH:mm:ss");

    private RecyclerView mRecyclerView;

    Toolbar toolbar;

    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        toolbar= (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                navigateUpTo(new Intent(NewActivity.this, MainActivity.class));

            }
        });


        mRecyclerView = (RecyclerView) findViewById(R.id.recyle);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        sidAdapter=new SidAdapter(new Information());
        mRecyclerView.setAdapter(sidAdapter);





       /* listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        });*/
    }

    @Override
    public void onStart() {
        super.onStart();
        String url= null;
        try {
            url = "https://cube26-1337.0x10.info/trains?source="+ URLEncoder.encode(MainActivity.src, "UTF-8")+"&destination="+URLEncoder.encode(MainActivity.dstn,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        nRequestQueue = VolleySingleton.getsInstance().getmRequestQueue();
        if(nRequestQueue==null){

            Log.d("Custom", "null");
        }
        if(nRequestQueue!=null) {

            Log.d("Custom", "not null");
            try {
                JsonArrayRequest req = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Custom", "response" + response.toString());

                        for (int i = 0; i < response.length(); i++) {

                      /*  try {
                            Toast.makeText(NewActivity.this, "" + response.getString(i), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(NewActivity.this, " null" , Toast.LENGTH_LONG).show();

                        }*/

                            try {


                                // Long trainNo=response.getJSONObject(i).getLong("trainNo");
                                String trainNo = response.getJSONObject(i).getString("trainNo");
                                String trainName = response.getJSONObject(i).getString("trainName");
                                //Date arrivalTime=dateFormat.parse(response.getJSONObject(i).getString("arrivalTime"));
                                //Date departureTime=dateFormat.parse(response.getJSONObject(i).getString("departureTime"));
                                String arrivalTime = response.getJSONObject(i).getString("arrivalTime");
                                String departureTime = response.getJSONObject(i).getString("departureTime");
                                String sourceStation = response.getJSONObject(i).getString("sourceStationName");
                                String destinationName = response.getJSONObject(i).getString("destinationStationName");
                                data = new Information(trainNo, trainName, sourceStation, destinationName, arrivalTime, departureTime);
                                Toast.makeText(NewActivity.this, "" + data.trainName, Toast.LENGTH_LONG).show();
                                //list.add(response.getJSONObject(i).getString("trainName"));
                                iList.add(data);
                                //Toast.makeText(NewActivity.this,""+iList,Toast.LENGTH_LONG).show();


                                //sidAdapter.add();


                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(NewActivity.this, "exception", Toast.LENGTH_LONG).show();
                                Log.d("Custom", "Exception");
                            }


                        }
                        sidAdapter = new SidAdapter(iList);
                        mRecyclerView.setAdapter(sidAdapter);


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d("Custom", "response error");
                    }
                });

                nRequestQueue.add(req);
            }catch (Exception e){}
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new, menu);
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
