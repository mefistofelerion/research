package com.mefistofelerion.justrun;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * will be used to collect data to be send later
 * Created by Ivan Guerra on 18/08/14.
 */
public class LoggerHelper {

    private final Logger log = LogManager.getLogger(LoggerHelper.class);

    public void error(String error){
        log.error(error);
    }

    public void debug(String msg){
        log.debug(msg);
    }

    public void info(String msg){
        log.info(msg);
    }

}
