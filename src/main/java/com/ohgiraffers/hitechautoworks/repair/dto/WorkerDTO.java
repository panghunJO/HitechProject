package com.ohgiraffers.hitechautoworks.repair.dto;

import com.ohgiraffers.hitechautoworks.res.dto.ResDTO;
import com.ohgiraffers.hitechautoworks.auth.dto.UserDTO;

public class WorkerDTO {
    private ResDTO resDTO;
    private UserDTO userDTO;

    public WorkerDTO(ResDTO resDTO, UserDTO userDTO) {
        this.resDTO = resDTO;
        this.userDTO = userDTO;
    }

    public WorkerDTO() {
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
