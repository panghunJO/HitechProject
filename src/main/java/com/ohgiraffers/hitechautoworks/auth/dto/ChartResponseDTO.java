package com.ohgiraffers.hitechautoworks.auth.dto;

import java.util.List;

public class ChartResponseDTO {

    private List<Integer> partStock;
    private List<String> partName;

    public ChartResponseDTO(List<Integer> partStock, List<String> partName) {
        this.partStock = partStock;
        this.partName = partName;
    }

    // Getters and Setters
    public List<Integer> getPartStock() {
        return partStock;
    }

    public void setPartStock(List<Integer> partStock) {
        this.partStock = partStock;
    }

    public List<String> getPartName() {
        return partName;
    }

    public void setPartName(List<String> partName) {
        this.partName = partName;
    }
}
