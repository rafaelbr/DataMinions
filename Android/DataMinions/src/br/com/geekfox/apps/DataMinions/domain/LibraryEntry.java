package br.com.geekfox.apps.DataMinions.domain;

/**
 * Created by rafaelbrasileiro on 24/04/14.
 */
public class LibraryEntry {

    private int dataminionId;
    private String location;
    private String locationName;

    public int getDataminionId() {
        return dataminionId;
    }

    public void setDataminionId(int dataminionId) {
        this.dataminionId = dataminionId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
