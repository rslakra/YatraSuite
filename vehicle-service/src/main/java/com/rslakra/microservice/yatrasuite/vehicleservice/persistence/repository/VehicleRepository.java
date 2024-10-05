package com.rslakra.microservice.yatrasuite.vehicleservice.persistence.repository;

import com.rslakra.appsuite.spring.persistence.repository.BaseRepository;
import com.rslakra.microservice.yatrasuite.vehicleservice.persistence.entity.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * JPA Repository for Vehicles - we have no specific requirements for Vehicle other than the standard Create, Update,
 * Delete options we inherit from the JpaRepository interface we're sub-classing, so we don't need any special logic
 * here
 */

@Repository
public interface VehicleRepository extends BaseRepository<Vehicle, UUID> {

}
