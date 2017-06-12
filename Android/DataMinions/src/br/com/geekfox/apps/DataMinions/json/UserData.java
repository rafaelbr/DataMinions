package br.com.geekfox.apps.DataMinions.json;

/**
 * Created by rafaelbrasileiro on 17/04/14.
 */
public class UserData {

    private int id;
    private String email;
    private String facebookId;
    private String image64;
    private String password;
    private String nickname;
    private int level;
    private int xp;
    private int money;
    private int gold;
    private ServiceResult result;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getImage64() {
        return image64;
    }

    public void setImage64(String image64) {
        this.image64 = image64;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public ServiceResult getResult() {
        return result;
    }

    public void setResult(ServiceResult result) {
        this.result = result;
    }
}
