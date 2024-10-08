package com.rslakra.microservice.yatrasuite.vehicleservice.persistence.repository;

import com.rslakra.appsuite.spring.persistence.repository.BaseRepository;
import com.rslakra.microservice.yatrasuite.vehicleservice.persistence.entity.LocationHistory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * JPA Repository for Location History
 */

@Repository
public interface LocationHistoryRepository extends BaseRepository<LocationHistory, Long> {

    /**
     * Returns the location history for a specific vehicle, with more recent entries first.
     * <p>
     * Spring JPA converts the name of this method into a SQL statement that does the work for us in this case, it
     * creates a SQL query that fetches all location rows for the specified vehicle, orders them by timestamp descending
     * (which puts newest ones first) and then returns the first one it finds.  This gives us the most recent location
     * history record for the requested vehicle.
     *
     * @param vehicleId The UUID of the vehicle to retrieve history for
     * @return a single LocationHistory entity
     */
    LocationHistory findFirstByVehicleIdOrderByCreatedAtDesc(UUID vehicleId);

    /**
     * @param vehicleId
     * @return
     */
    List<LocationHistory> findAllByVehicleId(UUID vehicleId);
}
