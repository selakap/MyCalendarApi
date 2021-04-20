package com.my.CalendarApi;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class customClass extends AbstractMediator {

    private static final Log log = LogFactory.getLog(customClass.class);

    public boolean mediate(MessageContext mc) {

        Calendar C = Calendar.getInstance();
        Date currentDate = C.getTime();
        Integer expiryLimit = (Integer) mc.getProperty("expiry");
        currentDate.setSeconds(expiryLimit + currentDate.getSeconds());

        SimpleDateFormat formatOfDate =
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String myDate = formatOfDate.format(currentDate);
        log.info("current_date: " + myDate);
        mc.setProperty("concatTime", myDate);

        return true;
    }

}