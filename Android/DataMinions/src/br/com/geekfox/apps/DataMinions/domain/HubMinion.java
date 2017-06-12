package br.com.geekfox.apps.DataMinions.domain;

import br.com.geekfox.apps.DataMinions.json.DataMinionData;

/**
 * Created by rafaelbrasileiro on 07/05/14.
 */
public class HubMinion {
    private int minion_id;
    private DataMinionData minionData;
    private int hub_id;
    private String location;

    public int getMinion_id() {
        return minion_id;
    }

    public void setMinion_id(int minion_id) {
        this.minion_id = minion_id;
    }

    public DataMinionData getMinionData() {
        return minionData;
    }

    public void setMinionData(DataMinionData minionData) {
        this.minionData = minionData;
    }

    public int getHub_id() {
        return hub_id;
    }

    public void setHub_id(int hub_id) {
        this.hub_id = hub_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
