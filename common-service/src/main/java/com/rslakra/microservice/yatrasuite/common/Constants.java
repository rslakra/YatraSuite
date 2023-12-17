package com.rslakra.microservice.yatrasuite.common;

/**
 * The <code>Constants</code> contains the configuration of generic constants.
 * <p>
 * TODO: Moves these messages to specific service if possible.
 *
 * @author Rohtash Lakra
 * @created 7/25/23 8:50 PM
 */
public interface Constants {

    /**
     * Generic Messages
     */
    String ERR_VEHICLE_NOT_FOUND = "Vehicle id <%s> not found!";
    String ERR_INVALID_VEHICLE_ID = "Vehicle id <%s> is not valid id : %s!";
    String ERR_VEHICLE_IN_USE = "Vehicle id <%s> is currently in use!";
    String ERR_VEHICLE_NOT_IN_USE = "Vehicle id <%s> is not currently being used!";
    String ERR_DIVIDE_BY_ZERO = "Cannot calculate an average velocity when the time interval is 0!";
    String ERR_BATTERY_INVALID = "Battery (percent) must be between 0 and 100!";
    String ERR_LAT_INVALID = "Latitude must be between -90 and 90!";
    String ERR_LON_INVALID = "Longitude must be between -180 and 180!";
    String ERR_SERIAL_INVALID = "Serial Number must be a number!";
    String ERR_DESERIALIZATION_FAILED = "Unable to deserialize the requested object!";

    /**
     * Ride service messages
     */

    // success messages
    String MSG_DELETED_VEHICLE = "Deleted vehicle with id <%s> from database!";

    /**
     * Vehicle service messages
     */
    String ERR_NO_ACTIVE_RIDE = "No active ride for this vehicle <%s> and user <%s> combination!";
    String MSG_RIDE_STARTED = "Ride started with vehicle %s!";
    String MSG_RIDE_ENDED = "You have completed your ride on vehicle %s!";
}
