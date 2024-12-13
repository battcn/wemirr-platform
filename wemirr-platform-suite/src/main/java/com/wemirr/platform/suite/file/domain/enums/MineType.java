package com.wemirr.platform.suite.file.domain.enums;

/**
 * @author xiao1
 * @date 2024-12
 */
public enum MineType {
    IMAGE("image/jpeg", "image/png", "image/gif"),
    AUDIO("audio/mpeg", "audio/wav", "audio/ogg"),
    VIDEO("video/mp4", "video/quicktime", "video/x-msvideo"),
    DOCUMENT( "application/pdf",
                            "application/msword",
                            "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                            "application/vnd.ms-excel",
                            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                            "application/vnd.ms-powerpoint",
                            "application/vnd.openxmlformats-offirected.presentationml.presentation"),
    OTHER;

    private final String[] mimeTypes;

    MineType(String... mimeTypes) {
        this.mimeTypes = mimeTypes;
    }

    public static MineType of(String mimeType) {
        for (MineType type : values()) {
            if (type == OTHER) continue;
            for (String mType : type.mimeTypes) {
                if (mType.equalsIgnoreCase(mimeType)) {
                    return type;
                }
            }
        }
        return OTHER;
    }
    public static String ofName(String mimeType) {
        for (MineType type : values()) {
            if (type == OTHER) continue;
            for (String mType : type.mimeTypes) {
                if (mType.equalsIgnoreCase(mimeType)) {
                    return type.name();
                }
            }
        }
        return OTHER.name();
    }
}
