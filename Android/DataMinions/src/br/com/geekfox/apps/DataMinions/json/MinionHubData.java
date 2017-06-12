package br.com.geekfox.apps.DataMinions.json;

/**
 * Created by rafaelbrasileiro on 12/05/14.
 */
public class MinionHubData {

    private int userId;
    private DataMinionData minionData;
    private DataHubData hubData;
    private ServiceResult result;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public DataMinionData getMinionData() {
        return minionData;
    }

    public void setMinionData(DataMinionData minionData) {
        this.minionData = minionData;
    }

    public DataHubData getHubData() {
        return hubData;
    }

    public void setHubData(DataHubData hubData) {
        this.hubData = hubData;
    }

    public ServiceResult getResult() {
        return result;
    }

    public void setResult(ServiceResult result) {
        this.result = result;
    }
}
