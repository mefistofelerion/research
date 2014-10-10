package com.mefistofelerion.justrun;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * will be used to collect data to be send later
 * Created by ivan on 18/08/14.
 */
public class LoggerConf {
    private final Logger log = LogManager.getLogger(LoggerConf.class);

    public void test() {
        log.info("This message should be seen in log file and logcat");
    }

    public void error(String error){
        log.error(error);
    }

    public void debug(String msg){
        log.debug(msg);
    }

}
