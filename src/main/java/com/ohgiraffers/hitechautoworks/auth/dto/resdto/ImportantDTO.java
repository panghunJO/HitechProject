package com.ohgiraffers.hitechautoworks.auth.dto.resdto;

import java.util.ArrayList;
import java.util.List;

public class ImportantDTO {
    private String drive;
    private String shift;
    private String tilt;
    private String stop;
    private String feul;
    private String light;

    public ImportantDTO() {
    }

    public ImportantDTO(String drive, String shift, String tilt, String stop, String feul, String light) {
        this.drive = drive;
        this.shift = shift;
        this.tilt = tilt;
        this.stop = stop;
        this.feul = feul;
        this.light = light;
    }

    public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getTilt() {
        return tilt;
    }

    public void setTilt(String tilt) {
        this.tilt = tilt;
    }

    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public String getFeul() {
        return feul;
    }

    public void setFeul(String feul) {
        this.feul = feul;
    }

    public String getLight() {
        return light;
    }

    public void setLight(String light) {
        this.light = light;
    }

    @Override
    public String toString() {
        return "ImportantDTO{" +
                "drive='" + drive + '\'' +
                ", shift='" + shift + '\'' +
                ", tilt='" + tilt + '\'' +
                ", stop='" + stop + '\'' +
                ", feul='" + feul + '\'' +
                ", light='" + light + '\'' +
                '}';
    }

    public List<String> getNonNullValues() {
        List<String> nonNullValues = new ArrayList<>();

        if (drive != null) {
            nonNullValues.add(drive);
        }
        if (shift != null) {
            nonNullValues.add(shift);
        }
        if (tilt != null) {
            nonNullValues.add(tilt);
        }
        if (stop != null) {
            nonNullValues.add(stop);
        }
        if (feul != null) {
            nonNullValues.add(feul);
        }
        if (light != null) {
            nonNullValues.add(light);
        }

        return nonNullValues;
    }
}
