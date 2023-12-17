package com.rslakra.microservice.yatrasuite.framework;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rslakra.microservice.yatrasuite.common.exception.DeserializationException;
import com.rslakra.microservice.yatrasuite.common.exception.InvalidUUIDException;
import com.rslakra.microservice.yatrasuite.common.exception.InvalidValueException;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

/**
 * Utility class to handle common operations
 */

public enum CommonUtils {

    INSTANCE;

    public static final String REGEX_EMAIL = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$";

    public static final String
        ERROR_DIVIDE_BY_ZERO =
        "Cannot calculate an average velocity when the time interval is 0!";

    public static final String ERROR_DESERIALIZATION_FAILED = "Unable to deserialize the requested object!";

    /**
     * Converts String to UUID and throws exception if string is not a valid UUID
     *
     * @param id
     * @param errorMessage
     * @return
     * @throws InvalidUUIDException
     */
    public static UUID toUUID(String id, String errorMessage) throws InvalidUUIDException {
        UUID uuid = null;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException ex) {
            throw new InvalidUUIDException(String.format(errorMessage, id, ex.getMessage()));
        }

        return uuid;
    }


    /**
     * @param rawString
     * @param errorMessage
     * @return
     * @throws InvalidValueException
     */
    public static Integer asInteger(String rawString, String errorMessage) throws InvalidValueException {
        Integer value = null;
        try {
            value = Integer.parseInt(rawString);
        } catch (NumberFormatException ex) {
            throw new InvalidValueException(errorMessage);
        }

        return value;
    }

    /**
     * @param rawString
     * @param errorMessage
     * @return
     * @throws InvalidValueException
     */
    public static Double asDouble(String rawString, String errorMessage) throws InvalidValueException {
        Double value = null;
        try {
            value = Double.parseDouble(rawString);
        } catch (NumberFormatException e) {
            throw new InvalidValueException(errorMessage);
        }

        return value;
    }

    /**
     * @param rawString
     * @param errorMessage
     * @return
     * @throws InvalidValueException
     */
    public static Double asDoubleLatitude(String rawString, String errorMessage) throws InvalidValueException {
        Double latitude = asDouble(rawString, errorMessage);
        if ((latitude < -90) || (latitude > 90)) {
            throw new InvalidValueException(errorMessage);
        }

        return latitude;
    }

    /**
     * @param rawString
     * @param errorMessage
     * @return
     * @throws InvalidValueException
     */
    public static Double asDoubleLongitude(String rawString, String errorMessage) throws InvalidValueException {
        Double longitude = asDouble(rawString, errorMessage);
        if ((longitude < -180) || (longitude > 180)) {
            throw new InvalidValueException(errorMessage);
        }

        return longitude;
    }

    /**
     * @param rawString
     * @param errorMessage
     * @return
     * @throws InvalidValueException
     */
    public static BigDecimal asBigDecimal(String rawString, String errorMessage) throws InvalidValueException {
        BigDecimal value = null;
        try {
            value = new BigDecimal(rawString);
        } catch (NumberFormatException ex) {
            throw new InvalidValueException(errorMessage);
        }

        return value;
    }

    /**
     * @param rawString
     * @param errorMessage
     * @return
     * @throws InvalidValueException
     */
    public static BigDecimal asBigDecimalLatitude(String rawString, String errorMessage) throws InvalidValueException {
        BigDecimal latitude = asBigDecimal(rawString, errorMessage);
        if (latitude != null && ((latitude.doubleValue() < -90) || (latitude.doubleValue() > 90))) {
            throw new InvalidValueException(errorMessage);
        }

        return latitude;
    }

    /**
     * @param rawString
     * @param errorMessage
     * @return
     * @throws InvalidValueException
     */
    public static BigDecimal asBigDecimalLongitude(String rawString, String errorMessage) throws InvalidValueException {
        BigDecimal longitude = asBigDecimal(rawString, errorMessage);
        if (longitude != null && ((longitude.doubleValue() < -180) || (longitude.doubleValue() > 180))) {
            throw new InvalidValueException(errorMessage);
        }

        return longitude;
    }

    /**
     * @param latitude1
     * @param longitude1
     * @param latitude2
     * @param longitude2
     * @return
     */
    private static double distance(double latitude1, double longitude1, double latitude2, double longitude2) {
        GeodeticCalculator geoCalc = new GeodeticCalculator();
        // select a reference ellipsoid
        Ellipsoid reference = Ellipsoid.WGS84;
        // set first coordinates
        GlobalCoordinates start = new GlobalCoordinates(latitude1, longitude1);
        // set second coordinates
        GlobalCoordinates end = new GlobalCoordinates(latitude2, longitude2);
        // calculate the geodetic curve
        GeodeticCurve geoCurve = geoCalc.calculateGeodeticCurve(reference, start, end);

        return (geoCurve.getEllipsoidalDistance() / 1000.0);
    }

    /**
     * Finds the distance between two points, in kilometers, to a precision of 10 meters.
     *
     * @param latitude1
     * @param longitude1
     * @param latitude2
     * @param longitude2
     * @return
     */
    public static double calculateDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
        return Math.round(distance(latitude1, longitude1, latitude2, longitude2) * 100d) / 100d;
    }

    /**
     * returns the time between two timestamps, in decimal minutes
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static double calculateDurationMinutes(LocalDateTime startTime, LocalDateTime endTime) {
        return calculateDuration(startTime, endTime).toMillis() / 60000d;
    }

    /**
     * @param startTime
     * @param endTime
     * @return
     */
    private static Duration calculateDuration(LocalDateTime startTime, LocalDateTime endTime) {
        return Duration.between(startTime, endTime);
    }

    /**
     * Finds the magnitude of the velocity, in kilometers per hour
     *
     * @param latitude1
     * @param longitude1
     * @param startTime
     * @param latitude2
     * @param longitude2
     * @param endTime
     * @return
     * @throws InvalidValueException
     */
    public static double calculateVelocity(double latitude1, double longitude1, LocalDateTime startTime,
                                           double latitude2, double longitude2, LocalDateTime endTime)
        throws InvalidValueException {
        double distanceTravelled = calculateDistance(latitude1, longitude1, latitude2, longitude2);
        if (distanceTravelled == 0d) {
            return 0d;
        }

        double hoursElapsed = calculateDurationMinutes(startTime, endTime) / 60d;
        if (hoursElapsed == 0d) {
            throw new InvalidValueException(ERROR_DIVIDE_BY_ZERO);
        }

        // round to 2 dec places to make it look nice :)
        return Math.round(distanceTravelled / hoursElapsed * 100d) / 100d;
    }

    /**
     * @param mapper
     * @param data
     * @param type
     * @param <T>
     * @return
     * @throws DeserializationException
     */
    public static <T> T deserialize(ObjectMapper mapper, Map<String, Object> data, Class<T> type)
        throws DeserializationException {
        try {
            T result = mapper.convertValue(data, type);
            return result;
        } catch (IllegalArgumentException ex) {
            throw new DeserializationException(ERROR_DESERIALIZATION_FAILED + " " + ex.getMessage());
        }
    }
}
