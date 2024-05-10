package com.ohgiraffers.hitechautoworks.auth.dto.resdto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class NormalDTO {
    private String meteor;
    private String cold;
    private String wheel;
    private String lamp;
    private String dooropen;
    private String switch1;

    public List<String> getNonNullValues() {
        List<String> nonNullValues = new ArrayList<>();

        if (meteor != null) {
            nonNullValues.add(meteor);
        }
        if (cold != null) {
            nonNullValues.add(cold);
        }
        if (wheel != null) {
            nonNullValues.add(wheel);
        }
        if (lamp != null) {
            nonNullValues.add(lamp);
        }
        if (dooropen != null) {
            nonNullValues.add(dooropen);
        }
        if (switch1 != null) {
            nonNullValues.add(switch1);
        }

        return nonNullValues;
    }

    public String getMeteor() {
        return meteor;
    }

    public void setMeteor(String meteor) {
        this.meteor = meteor;
    }

    public String getCold() {
        return cold;
    }

    public void setCold(String cold) {
        this.cold = cold;
    }

    public String getWheel() {
        return wheel;
    }

    public void setWheel(String wheel) {
        this.wheel = wheel;
    }

    public String getLamp() {
        return lamp;
    }

    public void setLamp(String lamp) {
        this.lamp = lamp;
    }

    public String getDooropen() {
        return dooropen;
    }

    public void setDooropen(String dooropen) {
        this.dooropen = dooropen;
    }

    public String getSwitch1() {
        return switch1;
    }

    public void setSwitch1(String switch1) {
        this.switch1 = switch1;
    }

    @Override
    public String toString() {
        return "NormalDTO{" +
                "meteor='" + meteor + '\'' +
                ", cold='" + cold + '\'' +
                ", wheel='" + wheel + '\'' +
                ", lamp='" + lamp + '\'' +
                ", dooropen='" + dooropen + '\'' +
                ", switch1='" + switch1 + '\'' +
                '}';
    }
}
