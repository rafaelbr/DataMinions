package br.com.geekfox.apps.DataMinions.json;

/**
 * Created by rafaelbrasileiro on 12/05/14.
 */
public class DeviceData {
    private int id;
    private int userId;
    private String type;
    private String hash;
    private String additionalData;
    private ServiceResult result;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData;
    }

    public ServiceResult getResult() {
        return result;
    }

    public void setResult(ServiceResult result) {
        this.result = result;
    }
}
