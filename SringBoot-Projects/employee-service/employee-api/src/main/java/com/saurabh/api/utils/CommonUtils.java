package com.saurabh.api.utils;


import com.saurabh.api.models.Department;

import java.util.StringJoiner;
import java.util.UUID;

public final class CommonUtils {
    public static UUID convertToUUID(String id) {
        // Since we strip the "-" from the string, we need to break them back out
        final StringJoiner joiner = new StringJoiner("-");
        joiner.add(id.substring(0, 8));
        joiner.add(id.substring(8, 12));
        joiner.add(id.substring(12, 16));
        joiner.add(id.substring(16, 20));
        joiner.add(id.substring(20, 32));
        final UUID uuid = UUID.fromString(joiner.toString());
        return uuid;
    }


    public static String getStringIdFromUUID(UUID uuid) {
        return uuid.toString().replace("-", "");
    }
}

