package com.claymore.bamder.cybersecurity.event.enums;

public enum ChuzhiStatus {

    NO_PROCESS(0, "未处置"),
    PROCESSING(1, "在处置"),
    FINISH(2, "已完成")
    ;
    private int value;
    private String desc;

    ChuzhiStatus(int value, String desc){
        this.value = value;
        this.desc = desc;
    }

    public static ChuzhiStatus getByValue(int value) {
        for (ChuzhiStatus status : ChuzhiStatus.values()) {
            if (status.value == value) {
                return status;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
