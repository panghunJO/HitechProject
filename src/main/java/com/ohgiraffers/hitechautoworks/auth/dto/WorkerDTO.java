package com.ohgiraffers.hitechautoworks.auth.dto;

public class WorkerDTO {
    private ResDTO rssDTO;
    private UserDTO userDTO;

    public WorkerDTO(ResDTO rssDTO, UserDTO userDTO) {
        this.rssDTO = rssDTO;
        this.userDTO = userDTO;
    }

    public WorkerDTO() {
    }

    public ResDTO getRssDTO() {
        return rssDTO;
    }

    public void setRssDTO(ResDTO rssDTO) {
        this.rssDTO = rssDTO;
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
                "rssDTO=" + rssDTO +
                ", userDTO=" + userDTO +
                '}';
    }

}
