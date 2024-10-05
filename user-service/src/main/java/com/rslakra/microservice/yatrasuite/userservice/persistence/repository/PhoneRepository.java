package com.rslakra.microservice.yatrasuite.userservice.persistence.repository;

import com.rslakra.appsuite.spring.persistence.repository.BaseRepository;
import com.rslakra.microservice.yatrasuite.userservice.persistence.entity.Phone;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Rohtash Lakra
 * @created 8/20/21 7:21 PM
 */
@Repository
public interface PhoneRepository extends BaseRepository<Phone, Long> {

    /**
     * @param userId
     * @return
     */
    public List<Phone> findByUserId(@Param("userId") Long userId);

    /**
     * @param number
     * @return
     */
    public Optional<Phone> findByNumber(@Param("number") String number);

    /**
     * Deletes the user's phone numbers.
     *
     * @param userId
     */
    public void deleteByUserId(@Param("userId") Long userId);

}
