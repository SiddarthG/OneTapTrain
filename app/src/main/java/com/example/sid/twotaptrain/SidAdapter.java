package com.example.sid.twotaptrain;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sid on 10/11/2015.
 */
public class SidAdapter extends RecyclerView.Adapter<SidAdapter.ViewHolder> {
    ArrayList<Information> mDataset= new ArrayList<>();
Context m;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView trainNo;
        public TextView trainName;
        public TextView arrival;
        public TextView departure;
        public TextView source;
        public TextView destination;

        public ViewHolder(View v) {
            super(v);
            trainNo = (TextView)v.findViewById(R.id.trainNo);
            trainName=(TextView)v.findViewById(R.id.trainName);
            arrival = (TextView)v.findViewById(R.id.arrival);
            departure=(TextView)v.findViewById(R.id.departure);
            source = (TextView)v.findViewById(R.id.source);
            destination=(TextView)v.findViewById(R.id.destination);
        }
    }

    public void add(Information item,Context m) {
        mDataset.add(item);
        this.m=m;
        notifyItemRangeChanged(0, mDataset.size());
      //  Toast.makeText(m,"adapter"+mDataset,Toast.LENGTH_LONG).show();

    }



    // Provide a suitable constructor (depends on the kind of dataset)
    public SidAdapter(ArrayList myDataset) {
        mDataset=myDataset;
       // notifyItemRangeChanged(0, mDataset.size());
    }



    public SidAdapter(Information myDataset) {
        mDataset.add(myDataset);

    }

    // Create new views (invoked by the layout manager)
    @Override
    public SidAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_items, parent, false);
        // set the view's size, margins, paddings and layout parameters


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Information current=mDataset.get(position);

       // Toast.makeText(m,"adapter"+current.trainName,Toast.LENGTH_LONG).show();
         holder.trainNo.setText(current.trainNo);
        holder.trainName.setText(current.trainName);
        holder.arrival.setText(current.arrivalTime);
       holder.departure.setText(current.departureTime);
        holder.source.setText(current.sourceStation);
        holder.destination.setText(current.destinationName);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        //Toast.makeText(m,"adapter"+mDataset.size(),Toast.LENGTH_LONG).show();
        return mDataset.size();
    }


}