package ejb;

/*import jakarta.annotation.Resource;
import jakarta.ejb.*;
import jakarta.faces.bean.ApplicationScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
*//*
import jakarta.annotation.Resource;
import jakarta.ejb.ScheduleExpression;
import jakarta.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.TimerService;

import jakarta.annotation.Resource;
import javax.ejb.*;
import jakarta.faces.bean.ApplicationScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
*/
import javax.annotation.Resource;
import javax.ejb.*;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.bean.ApplicationScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;


@Named
@jakarta.ejb.Singleton(name = "timerEJB")
//@Singleton
//@ApplicationScoped
//@SessionScoped
@Startup
//public class timerBean implements Serializable {
public class timerBean{
    @Resource
    private TimerService timerService;

    private String sessionTime = "0 seconds";

    public int getSessionSeconds() {
        return sessionSeconds;
    }

    public void setSessionSeconds(int sessionSeconds) {
        this.sessionSeconds = sessionSeconds;
    }

    private int sessionSeconds;
    private int durationSeconds = 0;
    private boolean isSet = false;

    public Timer setTimerService(long duration){
        if(!isSet) {
            this.durationSeconds = Math.round(duration / 1000);
            isSet = true;

            return timerService.createIntervalTimer(duration, duration, new TimerConfig("timer", false));
        }
        else{
            return null;
        }

    }

    //Update session time every DURATION seconds
    @Timeout
    public void execute(Timer timer){

        // Update session time
        if(durationSeconds == 0){
            durationSeconds = 1;
        }
        sessionSeconds = sessionSeconds + durationSeconds;
        int minutes = (int) Math.floor(sessionSeconds/60);
        int hours = (int) Math.floor(minutes/60);
        int seconds = sessionSeconds - minutes*60;
        minutes = minutes - hours*60;

        //setSessionTime(hours + " hours, " + minutes + " minutes and " + seconds + " seconds!");
        setSessionTime(sessionSeconds + " seconds!");
    }

    public String getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(String sessionTime) {
        this.sessionTime = sessionTime;
    }
}
