package com.lolatech.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

/**
 * Created by razvan.vuscan on 3/16/17.
 */
public class LoggerHelper {
    private static Logger logger;

    private long startTime;
    private long endTime;

    private String elapsedTimeAsString;

    /**
     * Class constructor.
     *
     * @param thisClass the class that needs to be passed to the logger.
     */
    public LoggerHelper(Class<?> thisClass) {
        logger = LogManager.getLogger(thisClass);
    }

    /**
     * Method used to log info.
     *
     * @param infoBit the info bit, as a String.
     */
    public void logInfo(String infoBit) {
        logger.info(infoBit);
    }

    /**
     * Method used to get the start time of an action.
     *
     * @param message the message to trigger at start time.
     */
    public void getStartTime(String message) {
        startTime = System.currentTimeMillis();

        logInfo(message);
    }

    /**
     * Method used to get the start time of an action.
     *
     * @param action the action to be performed, as a String.
     * @param component the component, as a String.
     */
    public void getEndTime(String action, String component) {
        endTime = System.currentTimeMillis();

        Period period = new Period(startTime, endTime);

        PeriodFormatter formatter = new PeriodFormatterBuilder().appendSecondsWithMillis().printZeroNever().toFormatter();

        elapsedTimeAsString = formatter.print(period);

        logInfo(String.format("Time taken to %s the %s : %s seconds.", action, component, elapsedTimeAsString));
    }
}
