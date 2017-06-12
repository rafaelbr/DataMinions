package br.com.geekfox.apps.DataMinions.domain;

/**
 * Created by rafaelbrasileiro on 24/04/14.
 */
public class Attack {

    private int id;
    private String type;
    private String description;
    private int level;
    private int atkPower;
    private int defBonus;
    private int agiBonus;
    private int atkBonus;
    private int staBonus;
    private int defDamage;
    private int agiDamage;
    private int staDamage;
    private int atkDamage;
    private String statusApply;
    private int statusTurns;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAtkPower() {
        return atkPower;
    }

    public void setAtkPower(int atkPower) {
        this.atkPower = atkPower;
    }

    public int getDefBonus() {
        return defBonus;
    }

    public void setDefBonus(int defBonus) {
        this.defBonus = defBonus;
    }

    public int getAgiBonus() {
        return agiBonus;
    }

    public void setAgiBonus(int agiBonus) {
        this.agiBonus = agiBonus;
    }

    public int getAtkBonus() {
        return atkBonus;
    }

    public void setAtkBonus(int atkBonus) {
        this.atkBonus = atkBonus;
    }

    public int getStaBonus() {
        return staBonus;
    }

    public void setStaBonus(int staBonus) {
        this.staBonus = staBonus;
    }

    public int getDefDamage() {
        return defDamage;
    }

    public void setDefDamage(int defDamage) {
        this.defDamage = defDamage;
    }

    public int getAgiDamage() {
        return agiDamage;
    }

    public void setAgiDamage(int agiDamage) {
        this.agiDamage = agiDamage;
    }

    public int getStaDamage() {
        return staDamage;
    }

    public void setStaDamage(int staDamage) {
        this.staDamage = staDamage;
    }

    public int getAtkDamage() {
        return atkDamage;
    }

    public void setAtkDamage(int atkDamage) {
        this.atkDamage = atkDamage;
    }

    public String getStatusApply() {
        return statusApply;
    }

    public void setStatusApply(String statusApply) {
        this.statusApply = statusApply;
    }

    public int getStatusTurns() {
        return statusTurns;
    }

    public void setStatusTurns(int statusTurns) {
        this.statusTurns = statusTurns;
    }
}
