package br.com.geekfox.apps.DataMinions.json;

/**
 * Created by rafaelbrasileiro on 17/02/14.
 */
public class NotificationParcel {

    public static final String REQUEST_HUB_UPDATE = "hubupdate";
    public static final String UPDATE_USERDATA = "updateuser";
    public static final String TELL_USER_LVLUP = "userlvlup";
    public static final String TELL_MINION_LVLUP = "minionlvlup";

    private String type;
    private String message;
    private Object data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
