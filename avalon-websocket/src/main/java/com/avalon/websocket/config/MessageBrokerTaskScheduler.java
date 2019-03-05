package com.avalon.websocket.config;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;

public class MessageBrokerTaskScheduler implements TaskScheduler {

    public ScheduledFuture<?> schedule(Runnable task, Trigger trigger) {
        // TODO Auto-generated method stub
        return null;
    }

    public ScheduledFuture<?> schedule(Runnable task, Date startTime) {
        // TODO Auto-generated method stub
        return null;
    }

    public ScheduledFuture<?> scheduleAtFixedRate(Runnable task, long period) {
        // TODO Auto-generated method stub
        return null;
    }

    public ScheduledFuture<?> scheduleAtFixedRate(Runnable task, Date startTime, long period) {
        // TODO Auto-generated method stub
        return null;
    }

    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, long delay) {
        // TODO Auto-generated method stub
        return null;
    }

    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, Date startTime, long delay) {
        // TODO Auto-generated method stub
        return null;
    }

}
