package com.rslakra.microservice.rideservice.persistence.repository;

import com.rslakra.frameworks.spring.persistence.repository.BaseRepository;
import com.rslakra.microservice.rideservice.persistence.entity.EventEntity;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Handles ride CRUD operations.
 *
 * @author Rohtash Lakra
 * @created 7/23/23 9:16 PM
 */
@Repository
public interface EventRepository extends BaseRepository<EventEntity, UUID> {

}