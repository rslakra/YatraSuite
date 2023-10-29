package com.rslakra.microservice.rideservice.persistence.repository;

import com.devamatre.framework.spring.persistence.repository.BaseRepository;
import com.rslakra.microservice.rideservice.persistence.entity.Ride;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Handles ride CRUD operations.
 *
 * @author Rohtash Lakra
 * @created 7/23/23 9:16 PM
 */
@Repository
public interface RideRepository extends BaseRepository<Ride, Long> {

    /**
     * Query to return all rides for a current user, with most recent rides first.
     * <p>
     * The @Query annotation asks JPA to build a SQL query using the database relationships described in the DAO
     * entities (in this case Ride and User) for this vehicle/user combination and sort it by newest to oldest
     *
     * @param email the email address identifying the user to retrieve rides for
     * @return a list of Ride entities
     */
    @Query("select r from Ride r where r.userEmail = :email order by r.startTime desc")
    List<Ride> findRidesByUser(@Param("email") String email);

    /**
     * Query to return all active rides for a vehicle.
     *
     * @param vehicleId the id of the vehicle to search for.
     * @return a list of Ride entities
     */
    @Query("select r from Ride r where r.vehicleId = :vehicleId and r.endTime is null order by r.startTime desc")
    List<Ride> getActiveRidesByVehicle(@Param("vehicleId") UUID vehicleId);

    /**
     * Query to get the most recent active ride for a specific user/vehicle combination.
     * <p>
     * The @Query annotation asks JPA to build a SQL query using the database relationships for this described in the
     * DAO entities (in this case Ride, Vehicle and User) for this vehicle/user combination where the vehicle is still
     * marked "in use"
     *
     * @param email     the email address that identifies the user
     * @param vehicleId the UUID vehicle that the user is riding
     * @return a list of Ride entities
     */
    @Query("select r from Ride r where r.userEmail = :email and r.vehicleId = :vehicleId and r.endTime is null order by r.startTime desc")
    List<Ride> getActiveRides(@Param("email") String email, @Param("vehicleId") UUID vehicleId);
}
