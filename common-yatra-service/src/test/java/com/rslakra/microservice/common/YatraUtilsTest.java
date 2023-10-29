package com.rslakra.microservice.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rslakra.microservice.common.exception.DeserializationException;
import com.rslakra.microservice.common.exception.InvalidValueException;
import com.rslakra.microservice.framework.CommonUtils;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Misc math tests
 */
public class YatraUtilsTest {

    public void convertBatteryToInt_shouldReturnTheIntegerValue_ifTheValueIsValid() throws InvalidValueException {

        String batteryStr = "45";
        Integer battery = 45;
        assertEquals(battery, YatraUtils.asIntegerBatteryLevel(batteryStr));
    }

    @Test
    public void convertBatteryToInt_shouldThrowAnException_ifTheValueIsNotAnInteger() {
        assertThrows(InvalidValueException.class, () -> YatraUtils.asIntegerBatteryLevel("garbage"));
    }

    @Test
    public void convertBatteryToInt_shouldThrowAnException_ifTheValueIsOutsideTheValidRange() {
        assertThrows(InvalidValueException.class, () -> YatraUtils.asIntegerBatteryLevel("-5"));
        assertThrows(InvalidValueException.class, () -> YatraUtils.asIntegerBatteryLevel("110"));
    }

    @Test
    public void validateSerialNumber_shouldReturnTrue_ifTheSerialNumberIsAValidInteger() throws InvalidValueException {
        assertTrue(YatraUtils.validateSerialNumber("1234"));
    }

    @Test
    public void validateSerialNumber_shouldThrowAnException_ifTheSerialNumberIsNotAnInteger() {
        assertThrows(InvalidValueException.class, () -> YatraUtils.validateSerialNumber("Invalid"));
    }

    @Test
    public void validateSerialNumber_shouldThrowAnException_ifTheSerialNumberIsOutsideTheValidRange() {
        assertThrows(InvalidValueException.class, () -> YatraUtils.validateSerialNumber("111111111111111111111"));
        assertThrows(InvalidValueException.class, () -> YatraUtils.validateSerialNumber("-111111111111111111111"));
    }

    public static class DeserializationTestClass {

        private String stringField;
        private int intField;

        public String getStringField() {
            return stringField;
        }

        public void setStringField(String stringField) {
            this.stringField = stringField;
        }

        public int getIntField() {
            return intField;
        }

        public void setIntField(int intField) {
            this.intField = intField;
        }
    }

    @Test
    public void deserialize_shouldThrowAnException_ifTheObjectCantBeDeserialized() {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> data = new HashMap<>();
        data.put("invalidField", "someValue");
        assertThrows(DeserializationException.class,
                     () -> CommonUtils.deserialize(mapper, data, DeserializationTestClass.class));
    }

    @Test
    public void deserialize_shouldReturnTheObject_ifTheObjectIsValid() throws DeserializationException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> data = new HashMap<>();
        data.put("stringField", "value");
        data.put("intField", 5);

        DeserializationTestClass result = CommonUtils.deserialize(mapper, data, DeserializationTestClass.class);

        assertEquals("value", result.getStringField());
        assertEquals(5, result.getIntField());
    }
}
