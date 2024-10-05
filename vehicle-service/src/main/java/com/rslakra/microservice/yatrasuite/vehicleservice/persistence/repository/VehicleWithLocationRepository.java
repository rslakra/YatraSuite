package com.rslakra.microservice.yatrasuite.vehicleservice.persistence.repository;

import com.rslakra.appsuite.spring.persistence.repository.BaseRepository;
import com.rslakra.microservice.yatrasuite.vehicleservice.persistence.entity.VehicleWithLocation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * JPA Repository for VehiclesWithLocation -- basically, a vehicle with its most current location - but we'll need to
 * perform a custom query here.
 */

@Repository
public interface VehicleWithLocationRepository extends BaseRepository<VehicleWithLocation, UUID> {

    String
        QRY_VEHICLE_WITH_LOCATIONS =
        "SELECT vehicles.id AS id, last_ride_start, last_ride_end, serial_number, battery_level, vehicle_info, longitude AS last_longitude, latitude AS last_latitude, vehicles.created_at AS timestamp FROM (SELECT * FROM vehicles AS v LIMIT 20) AS vehicles INNER JOIN location_history ON vehicles.id = location_history.vehicle_id INNER JOIN (SELECT vehicle_id, max(created_at) AS max_ts FROM location_history GROUP BY vehicle_id) AS grouped_location_history ON grouped_location_history.max_ts = location_history.created_at WHERE location_history.vehicle_id = vehicles.id";

    /**
     * Custom query to get 20 (or whatever `limit` is equal to) vehicles and the current location for each, based on the
     * most recent timestamp.
     * <p>
     * There are two sub-queries. One is just used to make it explicit that the LIMIT is on vehicles (and vehicles
     * alone). The other (with GROUP BY) is to find only the most recent timestamp for each vehicle.
     * <p>
     * <p>
     * The query:
     * <pre>
     *     SELECT vehicles.id, vehicles.last_ride_start, vehicles.last_ride_end,
     *            vehicles.serial_number,
     *            vehicles.battery, vehicles.vehicle_info,
     *            longitude AS last_longitude, latitude AS last_latitude,
     *            ts AS timestamp
     *       FROM (SELECT * FROM vehicles AS v LIMIT 20) AS vehicles
     * INNER JOIN location_history
     *         ON vehicles.id = location_history.vehicle_id
     * INNER JOIN (  SELECT vehicle_id, max(ts) AS max_ts
     *                 FROM location_history
     *              GROUP BY vehicle_id) AS grouped_location_history
     *         ON grouped_location_history.max_ts = location_history.ts
     *      WHERE location_history.vehicle_id = vehicles.id;
     * </pre>
     *
     * @param limit
     * @return a list of VehicleWithLocation entities
     */
    @Query(value = QRY_VEHICLE_WITH_LOCATIONS, nativeQuery = true)
    List<VehicleWithLocation> getVehiclesWithLocation(@Param("limit") int limit);

}
