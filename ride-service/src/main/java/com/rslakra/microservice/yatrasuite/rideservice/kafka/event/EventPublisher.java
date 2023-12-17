package com.rslakra.microservice.yatrasuite.rideservice.kafka.event;

import com.devamatre.appsuite.core.Payload;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rslakra.microservice.yatrasuite.rideservice.persistence.entity.EventEntity;
import com.rslakra.microservice.yatrasuite.rideservice.persistence.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Rohtash Lakra
 * @created 7/31/23 1:30 PM
 */

@Component
public class EventPublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventPublisher.class);
    private EventRepository eventRepository;
    private ObjectMapper mapper;

    /**
     * @param eventRepository
     * @param mapper
     */
    @Autowired
    public EventPublisher(EventRepository eventRepository, ObjectMapper mapper) {
        this.eventRepository = eventRepository;
        this.mapper = mapper;
    }

    /**
     * @param eventType
     * @param event
     */
    public void publish(String eventType, Event event) {
        LOGGER.debug("+publish({}, {})", eventType, event);
        Payload<String, Object> eventData = mapper.convertValue(event, Payload.class);
        EventEntity eventEntity = new EventEntity();
        eventEntity.setId(UUID.randomUUID());
        eventEntity.setEventType(eventType);
        eventEntity.setEventData(eventData);
        eventEntity = eventRepository.save(eventEntity);
        LOGGER.debug("-publish()");
    }
}
