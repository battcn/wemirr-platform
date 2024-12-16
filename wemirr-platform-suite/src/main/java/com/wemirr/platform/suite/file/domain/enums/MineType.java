package com.wemirr.platform.suite.file.domain.enums;

import java.util.Arrays;

/**
 * @author xiao1
 * @since 2024-12
 */
public enum MineType {
    IMAGE("image/jpeg", "image/png", "image/gif"),
    AUDIO("audio/mpeg", "audio/wav", "audio/ogg"),
    VIDEO("video/mp4", "video/quicktime", "video/x-msvideo"),
    DOCUMENT("application/pdf",
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
        return Arrays.stream(values())
                .filter(type -> type != OTHER && Arrays.asList(type.mimeTypes).contains(mimeType.toLowerCase()))
                .findFirst()
                .orElse(OTHER);
    }

    public static String ofName(String mimeType) {
        return Arrays.stream(values())
                .filter(type -> type != OTHER && Arrays.asList(type.mimeTypes).contains(mimeType.toLowerCase()))
                .map(Enum::name)
                .findFirst()
                .orElse(OTHER.name());
    }

}
