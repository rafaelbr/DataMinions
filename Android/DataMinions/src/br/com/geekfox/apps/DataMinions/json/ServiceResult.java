package br.com.geekfox.apps.DataMinions.json;

/**
 * Created by rafaelbrasileiro on 30/01/14.
 */
public class ServiceResult {

    int code;
    String description;
    String detail;

    public ServiceResult(int code, String description, String detail) {
        this.code = code;
        this.description = description;
        this.detail = detail;
    }

    public ServiceResult() {

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
