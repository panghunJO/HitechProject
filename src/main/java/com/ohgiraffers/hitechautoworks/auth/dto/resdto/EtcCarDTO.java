package com.ohgiraffers.hitechautoworks.auth.dto.resdto;

import java.util.ArrayList;
import java.util.List;

public class EtcCarDTO {
    private String sunloof;
    private String glass;
    private String audio;
    private String sheet;
    private String etccar;

    public List<String> getNonNullValues() {
        List<String> nonNullValues = new ArrayList<>();

        if (sunloof != null) {
            nonNullValues.add(sunloof);
        }
        if (glass != null) {
            nonNullValues.add(glass);
        }
        if (audio != null) {
            nonNullValues.add(audio);
        }
        if (sheet != null) {
            nonNullValues.add(sheet);
        }
        if (etccar != null) {
            nonNullValues.add(etccar);
        }

        return nonNullValues;
    }

    public String getSunloof() {
        return sunloof;
    }

    public void setSunloof(String sunloof) {
        this.sunloof = sunloof;
    }

    public String getGlass() {
        return glass;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getSheet() {
        return sheet;
    }

    public void setSheet(String sheet) {
        this.sheet = sheet;
    }

    public String getEtccar() {
        return etccar;
    }

    public void setEtccar(String etccar) {
        this.etccar = etccar;
    }
}
