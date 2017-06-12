package br.com.geekfox.apps.DataMinions.json;

/**
 * Created by rafaelbrasileiro on 17/04/14.
 */
public class LibraryEntryData {
    private int id;
    private int dataMinionId;
    private int userId;
    private String dataMinionName;
    private String dataMinionType;
    private String description;
    private String location;
    private String locationName;
    private ServiceResult result;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDataMinionId() {
        return dataMinionId;
    }

    public void setDataMinionId(int dataMinionId) {
        this.dataMinionId = dataMinionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDataMinionName() {
        return dataMinionName;
    }

    public void setDataMinionName(String dataMinionName) {
        this.dataMinionName = dataMinionName;
    }

    public String getDataMinionType() {
        return dataMinionType;
    }

    public void setDataMinionType(String dataMinionType) {
        this.dataMinionType = dataMinionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public ServiceResult getResult() {
        return result;
    }

    public void setResult(ServiceResult result) {
        this.result = result;
    }
}
