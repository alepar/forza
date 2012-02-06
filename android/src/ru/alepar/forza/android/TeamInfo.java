package ru.alepar.forza.android;

public class TeamInfo {
    
    public final long id;
    public final String name;
    public final long lastLap;
    public final long avgLap;
    public final long delta;

    public TeamInfo(long id, String name, long lastLap, long avgLap, long delta) {
        this.id = id;
        this.name = name;
        this.lastLap = lastLap;
        this.avgLap = avgLap;
        this.delta = delta;
    }

}