package com.ohgiraffers.hitechautoworks.auth.dto;

public class RepairPartDTO {
    private ResDTO  resDTO;
    private PartDTO partDTO;

    public RepairPartDTO() {
    }

    public RepairPartDTO(ResDTO resDTO, PartDTO partDTO) {
        this.resDTO = resDTO;
        this.partDTO = partDTO;
    }

    public ResDTO getResDTO() {
        return resDTO;
    }

    public void setResDTO(ResDTO resDTO) {
        this.resDTO = resDTO;
    }

    public PartDTO getPartDTO() {
        return partDTO;
    }

    public void setPartDTO(PartDTO partDTO) {
        this.partDTO = partDTO;
    }

    @Override
    public String toString() {
        return "RepairPartDTO{" +
                "resDTO=" + resDTO +
                ", partDTO=" + partDTO +
                '}';
    }
}
