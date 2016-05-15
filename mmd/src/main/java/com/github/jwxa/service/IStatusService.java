package com.github.jwxa.service;

/**
 * <BR>
 * User: Jwxa<BR>
 * Date: 2015/11/5  Time: 19:49<BR>
 */
public interface IStatusService {

    public String startTask() throws InterruptedException;


    public int getTaskStatus();
}
