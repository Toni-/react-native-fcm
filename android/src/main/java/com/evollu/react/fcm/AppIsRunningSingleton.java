package com.evollu.react.fcm;

public class AppIsRunningSingleton {
    private boolean appIsRunning;

    private static final AppIsRunningSingleton instance = new AppIsRunningSingleton();
    public static AppIsRunningSingleton getInstance() {return instance;}

    public boolean isAppRunning() {
        return appIsRunning;
    }

    public void setAppIsRunning(boolean appIsRunning) {
        this.appIsRunning = appIsRunning;
    }
}