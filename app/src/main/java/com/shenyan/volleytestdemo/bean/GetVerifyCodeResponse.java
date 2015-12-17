package com.shenyan.volleytestdemo.bean;

/**
 * Created by ShenYan on 15/12/16. Project VolleyTestDemo
 */
public class GetVerifyCodeResponse {


    /**
     * resend_timestamp : 1450258331
     */

    private DataEntity data;
    /**
     * message : success
     * code : 200
     */

    private MetaEntity meta;

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setMeta(MetaEntity meta) {
        this.meta = meta;
    }

    public DataEntity getData() {
        return data;
    }

    public MetaEntity getMeta() {
        return meta;
    }

    public static class DataEntity {
        private int resend_timestamp;

        public void setResend_timestamp(int resend_timestamp) {
            this.resend_timestamp = resend_timestamp;
        }

        public int getResend_timestamp() {
            return resend_timestamp;
        }
    }

    public static class MetaEntity {
        private String message;
        private String code;

        public void setMessage(String message) {
            this.message = message;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public String getCode() {
            return code;
        }
    }
}
