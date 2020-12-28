package com.monkeyladder.ui.mainscreen;

import com.monkeyladder.game.Location;

class LocationData {
    private final Location location;

    private final int data;

    LocationData( Location location, int data ) {
        this.location = location;
        this.data = data;
    }

    public Location getLocation( ) {
        return location;
    }

    public int getData( ) {
        return data;
    }

    @Override
    public String toString( ) {
        return "LocationData{" +
                "location=" + location +
                ", data=" + data +
                '}';
    }
}
