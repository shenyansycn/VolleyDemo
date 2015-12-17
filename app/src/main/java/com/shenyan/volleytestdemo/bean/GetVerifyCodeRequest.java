package com.shenyan.volleytestdemo.bean;

/**
 * Created by ShenYan on 15/12/14. Project VolleyTestDemo
 */
public class GetVerifyCodeRequest {
    private String mobile = "";
    private String smsBusiType = "";
    private String IPv4 = "";
    private String timestamp = "";

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSmsBusiType() {
        return smsBusiType;
    }

    public void setSmsBusiType(String smsBusiType) {
        this.smsBusiType = smsBusiType;
    }

    public String getIPv4() {
        return IPv4;
    }

    public void setIPv4(String IPv4) {
        this.IPv4 = IPv4;
    }
}
