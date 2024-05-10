package com.ohgiraffers.hitechautoworks.auth.dto.resdto;

import java.util.ArrayList;
import java.util.List;

public class SomoDTO {

    private String engineoil;
    private String noice;
    private String aircon;
    private String battery;
    private String wiper;
    private String breakpad;

    public SomoDTO() {
    }

    public SomoDTO(String engineoil, String noice, String aircon, String battery, String wiper, String breakpad) {
        this.engineoil = engineoil;
        this.noice = noice;
        this.aircon = aircon;
        this.battery = battery;
        this.wiper = wiper;
        this.breakpad = breakpad;
    }

    public String getEngineoil() {
        return engineoil;
    }

    public void setEngineoil(String engineoil) {
        this.engineoil = engineoil;
    }

    public String getNoice() {
        return noice;
    }

    public void setNoice(String noice) {
        this.noice = noice;
    }

    public String getAircon() {
        return aircon;
    }

    public void setAircon(String aircon) {
        this.aircon = aircon;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getWiper() {
        return wiper;
    }

    public void setWiper(String wiper) {
        this.wiper = wiper;
    }

    public String getBreakpad() {
        return breakpad;
    }

    public void setBreakpad(String breakpad) {
        this.breakpad = breakpad;
    }

    @Override
    public String toString() {
        return "SomoDTO{" +
                "engineoil='" + engineoil + '\'' +
                ", noice='" + noice + '\'' +
                ", aircon='" + aircon + '\'' +
                ", battery='" + battery + '\'' +
                ", wiper='" + wiper + '\'' +
                ", breakpad='" + breakpad + '\'' +
                '}';
    }

    public List<String> getNonNullValues() {
        List<String> nonNullValues = new ArrayList<>();

        if (engineoil != null) {
            nonNullValues.add(engineoil);
        }
        if (noice != null) {
            nonNullValues.add(noice);
        }
        if (aircon != null) {
            nonNullValues.add(aircon);
        }
        if (battery != null) {
            nonNullValues.add(battery);
        }
        if (wiper != null) {
            nonNullValues.add(wiper);
        }
        if (breakpad != null) {
            nonNullValues.add(breakpad);
        }

        return nonNullValues;
    }
}
