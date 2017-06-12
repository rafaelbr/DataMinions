package br.com.geekfox.apps.DataMinions.domain;

import com.google.android.gms.maps.model.Marker;

/**
 * Created by rafaelbrasileiro on 07/05/14.
 */
public class MinionMarker {
    private int minionId;
    private String name;
    private String location;
    private Marker marker;

    public int getMinionId() {
        return minionId;
    }

    public void setMinionId(int minionId) {
        this.minionId = minionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }
}
