package com.rslakra.microservice.framework;

import com.devamatre.framework.core.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Rohtash Lakra
 * @created 4/26/23 9:55 PM
 */
public enum TimeUtils {
    INSTANCE;
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeUtils.class);
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
    private static final SimpleDateFormat SDF = new SimpleDateFormat();

    /**
     * @return
     */
    public static LocalDate now() {
        return LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Converts the <code>Date</code> into <code>LocalDate</code> object.
     *
     * @param date
     * @return
     */
    public static LocalDate toLocalDate(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Converts the <code>Date</code> into <code>LocalTime</code> object.
     *
     * @param date
     * @return
     */
    public static LocalTime toLocalTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalTime();
    }

    /**
     * Converts the <code>Date</code> into <code>LocalDateTime</code> object.
     *
     * @param date
     * @return
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * @param dateString
     * @param pattern
     * @param timeZone
     * @return
     */
    public static Date parseDate(String dateString, String pattern, String timeZone) {
        LOGGER.debug("+parseDate({}, {}, {})", dateString, pattern, timeZone);
        Date date = null;
        if (BeanUtils.isNotEmpty(dateString)) {
            try {
                final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                if (BeanUtils.isNotEmpty(timeZone)) {
                    simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
                }

                // parse date
                date = simpleDateFormat.parse(dateString);
            } catch (ParseException ex) {
                LOGGER.error("Error parsing date: {} with pattern:{} and timeZone:{}", dateString, pattern, timeZone);
            }
        }

        LOGGER.debug("-parseDate(), date:{}", date);
        return date;
    }

    /**
     * @param dateString
     * @param pattern
     * @return
     */
    public static Date parseDate(String dateString, String pattern) {
        return parseDate(dateString, pattern, TimeZone.getDefault().getDisplayName());
    }

    /**
     * @param dateString
     * @return
     */
    public static Date parseDate(String dateString) {
        return parseDate(dateString, DATE_TIME_PATTERN);
    }
}
