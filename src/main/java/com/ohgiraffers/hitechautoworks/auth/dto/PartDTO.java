package com.ohgiraffers.hitechautoworks.auth.dto;

public class PartDTO {
    private int partCode;
    private String partName;
    private int partstock;
    private int partPrice;

    public PartDTO() {
    }

    public PartDTO(int partCode, String partName, int partstock, int partPrice) {
        this.partCode = partCode;
        this.partName = partName;
        this.partstock = partstock;
        this.partPrice = partPrice;
    }

    public int getPartCode() {
        return partCode;
    }

    public void setPartCode(int partCode) {
        this.partCode = partCode;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public int getPartstock() {
        return partstock;
    }

    public void setPartstock(int partstock) {
        this.partstock = partstock;
    }

    public int getPartPrice() {
        return partPrice;
    }

    public void setPartPrice(int partPrice) {
        this.partPrice = partPrice;
    }

    @Override
    public String toString() {
        return "PartDTO{" +
                "partCode=" + partCode +
                ", partName='" + partName + '\'' +
                ", partstock=" + partstock +
                ", partPrice=" + partPrice +
                '}';
    }
}
