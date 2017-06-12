package br.com.geekfox.apps.DataMinions.json;

/**
 * Created by rafaelbrasileiro on 17/04/14.
 */
public class DataMinionData {

    private int id;
    private int minionId;
    private String name;
    private String nickname;
    private String description;
    private int level;
    private int xp;
    private String status;
    private String type;
    private int atkBase;
    private int defBase;
    private int agiBase;
    private int staBase;
    private int health;
    private int slot1AtkId;
    private int slot2AtkId;
    private int slot3AtkId;
    private int slot4AtkId;
    private ServiceResult result;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getNickname() {
        return nickname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAtkBase() {
        return atkBase;
    }

    public void setAtkBase(int atkBase) {
        this.atkBase = atkBase;
    }

    public int getDefBase() {
        return defBase;
    }

    public void setDefBase(int defBase) {
        this.defBase = defBase;
    }

    public int getAgiBase() {
        return agiBase;
    }

    public void setAgiBase(int agiBase) {
        this.agiBase = agiBase;
    }

    public int getStaBase() {
        return staBase;
    }

    public void setStaBase(int staBase) {
        this.staBase = staBase;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSlot1AtkId() {
        return slot1AtkId;
    }

    public void setSlot1AtkId(int slot1AtkId) {
        this.slot1AtkId = slot1AtkId;
    }

    public int getSlot2AtkId() {
        return slot2AtkId;
    }

    public void setSlot2AtkId(int slot2AtkId) {
        this.slot2AtkId = slot2AtkId;
    }

    public int getSlot3AtkId() {
        return slot3AtkId;
    }

    public void setSlot3AtkId(int slot3AtkId) {
        this.slot3AtkId = slot3AtkId;
    }

    public int getSlot4AtkId() {
        return slot4AtkId;
    }

    public void setSlot4AtkId(int slot4AtkId) {
        this.slot4AtkId = slot4AtkId;
    }

    public ServiceResult getResult() {
        return result;
    }

    public void setResult(ServiceResult result) {
        this.result = result;
    }
}
