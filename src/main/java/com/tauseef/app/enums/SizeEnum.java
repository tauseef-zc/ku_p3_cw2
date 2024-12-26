package com.tauseef.app.enums;

public enum SizeEnum {

    SMALL(0),
    MEDIUM(1),
    LARGE(2);

    private final int code;

    SizeEnum(int code) {
        this.code = code;
    }

    public static SizeEnum fromCode(int code) {
        for (SizeEnum size : SizeEnum.values()) {
            if (size.getCode() == code) {
                return size;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

}