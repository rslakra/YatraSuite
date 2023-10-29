package com.rslakra.microservice.common;

import com.rslakra.microservice.common.exception.InvalidValueException;
import com.rslakra.microservice.framework.CommonUtils;

import java.math.BigDecimal;

/**
 * The <code>YatraUtils</code> handles the generic operations.
 *
 * @author Rohtash Lakra
 * @created 7/25/23 8:50 PM
 */
public enum YatraUtils {

    INSTANCE;

    /**
     * @param batteryLevel
     * @return
     * @throws InvalidValueException
     */
    public static Integer asIntegerBatteryLevel(String batteryLevel) throws InvalidValueException {
        Integer battery = CommonUtils.asInteger(batteryLevel, Constants.ERR_BATTERY_INVALID);
        if ((battery < 0) || (battery > 100)) {
            throw new InvalidValueException(Constants.ERR_BATTERY_INVALID);
        }

        return battery;
    }

    /**
     * @param rawString
     * @return
     * @throws InvalidValueException
     */
    public static Double asDoubleLatitude(String rawString) throws InvalidValueException {
        return CommonUtils.asDoubleLatitude(rawString, Constants.ERR_LAT_INVALID);
    }

    /**
     * @param rawString
     * @return
     * @throws InvalidValueException
     */
    public static Double asDoubleLongitude(String rawString) throws InvalidValueException {
        return CommonUtils.asDoubleLongitude(rawString, Constants.ERR_LON_INVALID);
    }


    /**
     * @param rawString
     * @return
     * @throws InvalidValueException
     */
    public static BigDecimal asBigDecimalLatitude(String rawString) throws InvalidValueException {
        return CommonUtils.asBigDecimalLatitude(rawString, Constants.ERR_LAT_INVALID);
    }

    /**
     * @param rawString
     * @return
     * @throws InvalidValueException
     */
    public static BigDecimal asBigDecimalLongitude(String rawString) throws InvalidValueException {
        return CommonUtils.asBigDecimalLongitude(rawString, Constants.ERR_LON_INVALID);
    }

    /**
     * @param serialNumber
     * @return
     * @throws InvalidValueException
     */
    public static boolean validateSerialNumber(String serialNumber) throws InvalidValueException {
        try {
            Integer.parseInt(serialNumber);
        } catch (NumberFormatException ex) {
            throw new InvalidValueException(Constants.ERR_SERIAL_INVALID);
        }

        return true;
    }

}
