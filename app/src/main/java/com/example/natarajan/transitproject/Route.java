package com.example.natarajan.transitproject;


import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.nearby.messages.Distance;

import java.util.List;

import javax.xml.datatype.Duration;

/**
 * Created by Ashish on 11/1/2016.
 */

public class Route {
    public Distance distance1;
    public Duration duration1;
    public String endAddress;
    public LatLng endLocation;
    public String startAddress;
    public LatLng startLocation;
    public String distance;
    public String duration;

    public List<LatLng> points;
}