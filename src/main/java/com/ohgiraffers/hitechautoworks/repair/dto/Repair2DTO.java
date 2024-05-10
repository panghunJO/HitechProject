package com.ohgiraffers.hitechautoworks.repair.dto;

import com.ohgiraffers.hitechautoworks.auth.dto.UserDTO;
import com.ohgiraffers.hitechautoworks.part.dto.PartDTO;
import com.ohgiraffers.hitechautoworks.res.dto.ResDTO;

public class Repair2DTO {
    private RepairDTO repairDTO;
    private UserDTO userDTO;
    private PartDTO partDTO;
    private WorkerDTO workerDTO;
    private RepairPartDTO repairPartDTO;
    private ResDTO resDTO;

    public Repair2DTO() {
    }

    public Repair2DTO(RepairDTO repairDTO, UserDTO userDTO, PartDTO partDTO, WorkerDTO workerDTO, RepairPartDTO repairPartDTO, ResDTO resDTO) {
        this.repairDTO = repairDTO;
        this.userDTO = userDTO;
        this.partDTO = partDTO;
        this.workerDTO = workerDTO;
        this.repairPartDTO = repairPartDTO;
        this.resDTO = resDTO;
    }

    public RepairDTO getRepairDTO() {
        return repairDTO;
    }

    public void setRepairDTO(RepairDTO repairDTO) {
        this.repairDTO = repairDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public PartDTO getPartDTO() {
        return partDTO;
    }

    public void setPartDTO(PartDTO partDTO) {
        this.partDTO = partDTO;
    }

    public WorkerDTO getWorkerDTO() {
        return workerDTO;
    }

    public void setWorkerDTO(WorkerDTO workerDTO) {
        this.workerDTO = workerDTO;
    }

    public RepairPartDTO getRepairPartDTO() {
        return repairPartDTO;
    }

    public void setRepairPartDTO(RepairPartDTO repairPartDTO) {
        this.repairPartDTO = repairPartDTO;
    }

    public ResDTO getResDTO() {
        return resDTO;
    }

    public void setResDTO(ResDTO resDTO) {
        this.resDTO = resDTO;
    }

    @Override
    public String toString() {
        return "Repair2DTO{" +
                "repairDTO=" + repairDTO +
                ", userDTO=" + userDTO +
                ", partDTO=" + partDTO +
                ", workerDTO=" + workerDTO +
                ", repairPartDTO=" + repairPartDTO +
                ", resDTO=" + resDTO +
                '}';
    }
}
