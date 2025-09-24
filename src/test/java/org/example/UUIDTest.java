package org.example;

import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UUIDTest {
    @Test
    public void testUuid() {
        for (int i = 0; i < 10000; i++) {
            System.out.println(UUID.randomUUID().toString());
        };
    }
}
