package com.ohgiraffers.hitechautoworks.repair.dto;

import com.ohgiraffers.hitechautoworks.res.dto.ResDTO;
import com.ohgiraffers.hitechautoworks.auth.dto.UserDTO;

public class WorkerDTO {
    private ResDTO resDTO;
    private UserDTO userDTO;
    private String repairWorkers;

    public WorkerDTO(ResDTO resDTO, UserDTO userDTO,String repairWorkers) {
        this.resDTO = resDTO;
        this.userDTO = userDTO;
        this.repairWorkers = repairWorkers;
    }

    public WorkerDTO() {
    }

    public String getRepairWorkers() {
        return repairWorkers;
    }

    public void setRepairWorkers(String repairWorkers) {
        this.repairWorkers = repairWorkers;
    }

    public ResDTO getResDTO() {
        return resDTO;
    }

    public void setResDTO(ResDTO resDTO) {
        this.resDTO = resDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public String toString() {
        return "WorkerDTO{" +
                "resDTO=" + resDTO +
                ", userDTO=" + userDTO +
                '}';
    }

}
