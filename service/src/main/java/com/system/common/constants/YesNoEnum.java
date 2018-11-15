package com.system.common.constants;

public enum YesNoEnum {

    YES(1, "是"),
    NO(0, "否");

    private final int value;
    private final String desc;

    YesNoEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
