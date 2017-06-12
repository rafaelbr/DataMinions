package br.com.geekfox.apps.DataMinions.domain;

/**
 * Created by rafaelbrasileiro on 24/04/14.
 */
public class DataMinion {
    private int id;
    private String name;
    private String type;
    private String description;
    private int atkBase;
    private int agiBase;
    private int defBase;
    private int staBase;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAtkBase() {
        return atkBase;
    }

    public void setAtkBase(int atkBase) {
        this.atkBase = atkBase;
    }

    public int getAgiBase() {
        return agiBase;
    }

    public void setAgiBase(int agiBase) {
        this.agiBase = agiBase;
    }

    public int getDefBase() {
        return defBase;
    }

    public void setDefBase(int defBase) {
        this.defBase = defBase;
    }

    public int getStaBase() {
        return staBase;
    }

    public void setStaBase(int staBase) {
        this.staBase = staBase;
    }
}
