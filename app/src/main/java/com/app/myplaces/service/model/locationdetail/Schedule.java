package com.app.myplaces.service.model.locationdetail;


import com.google.gson.annotations.SerializedName;

public class Schedule {
    @SerializedName("sunday")
    private Days sunday;
    @SerializedName("monday")
    private Days monday;
    @SerializedName("tuesday")
    private Days tuesday;
    @SerializedName("wednesday")
    private Days wednesday;
    @SerializedName("thursday")
    private Days thursday;
    @SerializedName("friday")
    private Days friday;
    @SerializedName("saturday")
    private Days saturday;

    public Days getSunday() {
        return sunday;
    }

    public Days getMonday() {
        return monday;
    }

    public Days getTuesday() {
        return tuesday;
    }

    public Days getWednesday() {
        return wednesday;
    }

    public Days getThursday() {
        return thursday;
    }

    public Days getFriday() {
        return friday;
    }

    public Days getSaturday() {
        return saturday;
    }
}
