package com.department.api.utils;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CommonUtilsTest {

    @Test
    void getStringIdFromUUID() {

        String uuid = CommonUtils.getStringIdFromUUID(UUID.fromString("4ed52a88-2470-4d0b-9adb-c59da76a6b84"));

        assertEquals(32, uuid.length());
        assertEquals("4ed52a8824704d0b9adbc59da76a6b84", uuid);
    }

    @Test
    void convertToUUID() {

        UUID uuid = CommonUtils.convertToUUID("4ed52a8824704d0b9adbc59da76a6b84");

        // assertEquals(32, uuid);
        assertEquals(UUID.fromString("4ed52a88-2470-4d0b-9adb-c59da76a6b84"), uuid);
    }
}