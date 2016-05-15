package com.github.jwxa.service.impl;

import com.github.jwxa.service.IStatusService;
import org.springframework.stereotype.Service;

/**
 * <BR>
 * User: Jwxa<BR>
 * Date: 2015/11/5  Time: 19:49<BR>
 */
@Service
public class StatusServiceImpl implements IStatusService {


    public volatile int STATUS = 0;


    public String startTask() throws InterruptedException {
        synchronized (this) {
            STATUS = 0;
            Thread.sleep(10000);

        }
        return "OK";
    }

    public int getTaskStatus() {
        return STATUS;
    }
}
