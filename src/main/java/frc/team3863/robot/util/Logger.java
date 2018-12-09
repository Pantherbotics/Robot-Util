package frc.team3863.robot.util;

import java.io.File;

public class Logger {
    private File logFile;
    private StringBuilder logData;
    private double startTime, currentTime, timeSinceStart;
    public Logger(String logFilePath){
        logFile = new File(logFilePath);
        logData = new StringBuilder("Time,Subsystem,Message\n");
        startTime = System.currentTimeMillis();
    }

    public void addMessage(String subsystem, String message){
        logData.append(getTimeString()+"," + subsystem + "," + message);
    }


    public void updateTime(){
        currentTime = System.currentTimeMillis();
        timeSinceStart = currentTime - startTime;
    }

    private String formatTime(double milliseconds){
        String minutes = Integer.toString((int)milliseconds/60000);
        String seconds = Integer.toString((int)(milliseconds%60000)/1000);
        if(seconds.length()==1)
            seconds = "0" + seconds;
        String millis = Double.toString(milliseconds%1000);
        switch(millis.length()){
            case 1:
                millis = "00" + millis;
                break;
            case 2:
                millis = "0" + millis;
                break;
            case 3:
                break;
        }
        return minutes + ":" + seconds + "." +millis;
    }

    private String getTimeString(){
        updateTime();
        return formatTime(currentTime);
    }
}
