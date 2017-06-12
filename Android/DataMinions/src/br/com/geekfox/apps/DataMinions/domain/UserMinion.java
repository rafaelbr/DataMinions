package br.com.geekfox.apps.DataMinions.domain;

import br.com.geekfox.apps.DataMinions.json.DataMinionData;

/**
 * Created by rafaelbrasileiro on 24/04/14.
 */
public class UserMinion {
    private int id;
    private int dataminionId;
    private String nickname;
    private int level;
    private int xp;
    private String status;
    private int health;
    private int atk1Id;
    private int atk2Id;
    private int atk3Id;
    private int atk4Id;
    private DataMinionData currentMinionData;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDataminionId() {
        return dataminionId;
    }

    public void setDataminionId(int dataminionId) {
        this.dataminionId = dataminionId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAtk1Id() {
        return atk1Id;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setAtk1Id(int atk1Id) {
        this.atk1Id = atk1Id;
    }

    public int getAtk2Id() {
        return atk2Id;
    }

    public void setAtk2Id(int atk2Id) {
        this.atk2Id = atk2Id;
    }

    public int getAtk3Id() {
        return atk3Id;
    }

    public void setAtk3Id(int atk3Id) {
        this.atk3Id = atk3Id;
    }

    public int getAtk4Id() {
        return atk4Id;
    }

    public void setAtk4Id(int atk4Id) {
        this.atk4Id = atk4Id;
    }

    public DataMinionData getCurrentMinionData() {
        return currentMinionData;
    }

    public void setCurrentMinionData(DataMinionData currentMinionData) {
        this.currentMinionData = currentMinionData;
    }
}
