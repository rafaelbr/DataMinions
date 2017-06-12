package br.com.geekfox.apps.DataMinions.json;

import java.util.List;

/**
 * Created by rafaelbrasileiro on 07/05/14.
 */
public class DataHubData {
    private int id;
    private String name;
    private String type;
    private String location;
    private List<String> minionsData;
    private ServiceResult result;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getMinionsData() {
        return minionsData;
    }

    public void setMinionsData(List<String> minionsData) {
        this.minionsData = minionsData;
    }

    public ServiceResult getResult() {
        return result;
    }

    public void setResult(ServiceResult result) {
        this.result = result;
    }
}
