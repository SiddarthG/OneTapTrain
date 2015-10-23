package com.example.sid.twotaptrain;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by sid on 10/8/2015.
 */
public class StationFragment extends DialogFragment {
    ListView listview;
    EditText inputSearch;
    RequestQueue sRequestQueue;

    ArrayList<String> list;

    String url = "https://cube26-1337.0x10.info/stations";
    ArrayAdapter<String> adapter;
    Context mcontext;







    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mcontext=getActivity();

        sRequestQueue = VolleySingleton.getsInstance().getmRequestQueue();
        if(sRequestQueue==null){

            Log.d("Custom", "null");
        }
        if(sRequestQueue!=null) {

            Log.d("Custom", "not null");
            JsonArrayRequest req = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    Log.d("Custom","response"+response.toString());

                    for(int i=0;i<response.length();i++){



                        try {

                           list.add(response.getString(i));
                            adapter.notifyDataSetChanged();
                        }catch(Exception e) {
                            e.printStackTrace();

                            Log.d("Custom", "Exception");
                        }


                    }



                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {

                    Log.d("Custom","response error");
                }
            });

            sRequestQueue.add(req);
        }

    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        View v=getActivity().getLayoutInflater().inflate(R.layout.list_source,null);
        list=new ArrayList<String>();

        listview = (ListView) v.findViewById(R.id.listView);
        inputSearch=(EditText)v.findViewById(R.id.searchView);
        adapter= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);

            listview.setAdapter(adapter);


            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    buttonChange(position);
                    //  Toast.makeText(getActivity(),"clicked"+position,Toast.LENGTH_LONG).show();
                    dismiss();
                }
            });

        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });
        return new AlertDialog.Builder(getActivity()).setView(v).create();




    }



    public void buttonChange(int index) {


       MainActivity.pass(list.get(index));
       // Toast.makeText(getActivity(),"list"+list.get(index),Toast.LENGTH_LONG).show();
    }
}
