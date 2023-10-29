package com.rslakra.microservice.yatrawebapp.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.rslakra.microservice.common.YatraUtils;
import com.rslakra.microservice.common.exception.InvalidValueException;
import org.junit.jupiter.api.Test;

/**
 * Misc math tests
 */

public class WebUtilsTest {

    @Test
    public void asIntegerBatteryLevelTest() throws InvalidValueException {
        String batteryStr = "45";
        Integer battery = 45;
        assertEquals(battery, YatraUtils.asIntegerBatteryLevel(batteryStr));
        assertThrows(InvalidValueException.class, () -> YatraUtils.asIntegerBatteryLevel("garbage"));
        assertThrows(InvalidValueException.class, () -> YatraUtils.asIntegerBatteryLevel("-5"));
        assertThrows(InvalidValueException.class, () -> YatraUtils.asIntegerBatteryLevel("110"));
    }

    @Test
    public void asDoubleLatitudeTest() throws InvalidValueException {
        String latStr = "45.5";
        Double latitude = 45.5d;
        assertEquals(latitude, YatraUtils.asDoubleLatitude(latStr));
        assertThrows(InvalidValueException.class, () -> YatraUtils.asDoubleLatitude("garbage"));
        assertThrows(InvalidValueException.class, () -> YatraUtils.asDoubleLatitude("-94"));
        assertThrows(InvalidValueException.class, () -> YatraUtils.asDoubleLatitude("100"));
    }

    @Test
    public void asDoubleLongitudeTest() throws InvalidValueException {
        String lonStr = "-73.5";
        Double longitude = -73.5d;
        assertEquals(longitude, YatraUtils.asDoubleLongitude(lonStr));
        assertThrows(InvalidValueException.class, () -> YatraUtils.asDoubleLongitude("garbage"));
        assertThrows(InvalidValueException.class, () -> YatraUtils.asDoubleLongitude("-194"));
        assertThrows(InvalidValueException.class, () -> YatraUtils.asDoubleLongitude("310"));
    }

}
