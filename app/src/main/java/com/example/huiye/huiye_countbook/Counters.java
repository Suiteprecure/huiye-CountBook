package com.example.huiye.huiye_countbook;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by huiye on 22/09/17.
 */
/*
 * this is the object class counters.
 */
public class Counters implements Serializable {
    private String name;
    private Date dateInitial;
    private String date;
    private String comment;
    private int initialValue;
    private int currentValue;


    public Counters(String name, String comment, int initialValue){
        this.name = name;
        dateInitial = new Date();
        date = new SimpleDateFormat("yyyy-MM-dd").format(dateInitial);
        //initialValue = 0;
        this.initialValue = initialValue;
        this.currentValue = initialValue;
        this.comment = comment;
    }

    public Counters(String name, int initialValue){
        this.name = name;
        dateInitial = new Date();
        date = new SimpleDateFormat("yyyy-MM-dd").format(dateInitial);
        this.initialValue = initialValue;
        this.currentValue = initialValue;
    }

    /**
     * set current value to initial value
     */
    public void resetCurrentValue() {
        currentValue = initialValue;
    }

    /**
     * return counter name
     * @return counter name
     */
    public String getName() {return name; }

    /**
     * reuturn initial value for counter
     * @return initial value
     */
    public int getInitialValue() {return initialValue;}

    /**
     * return current value for counter
     * @return current value
     */
    public int getCurrentValue() {return currentValue; }

    /**
     * return comments for counter
     * @return comment
     */
    public String getComment(){
        return comment;
    }

    /**
     * return date for counter
     * @return date
     */
    public String getDate() {
        return date.toString();
    }

    /*increment a counter's current value by one*/
    public void incrementCurrent() {
        currentValue = currentValue + 1;
    }

    /*decrement a counter's current value by one*/
    public boolean decrementCurrent() {
        if (currentValue > 0) {
            currentValue = currentValue - 1;
            return false;
        }else{
            //it is equal 0
            return true;
        }
    }

}
