package com.example.sid.twotaptrain;

import java.util.Date;

/**
 * Created by sid on 10/11/2015.
 */
public class Information {

    //Long trainNo;
    String trainNo;
    String trainName;
    String sourceStation;
    String destinationName;
    //Date arrivalTime;
    //Date departureTime;
    String arrivalTime;
    String departureTime;

    public Information(){

        trainName="not applicable";

    }

    public Information(String trainNo,String trainName,String sourceStation,String destinationName,String arrivalTime,String departureTime){
        this.trainNo=trainNo;
        this.trainName=trainName;
        this.sourceStation=sourceStation;
        this.destinationName=destinationName;
        this.arrivalTime=arrivalTime;
        this.departureTime=departureTime;
    }


}
