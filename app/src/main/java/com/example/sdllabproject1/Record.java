package com.example.sdllabproject1;

public class Record {
    String date;
    String eod;


    public Record()
    {

    }
    public Record(String date, String eod) {
        this.date = date;
        this.eod = eod;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEod() {
        return eod;
    }

    public void setEod(String eod) {
        this.eod = eod;
    }




}
