// UserService.java

package com.ohgiraffers.hitechautoworks.auth.service;

import com.ohgiraffers.hitechautoworks.auth.dao.UserMapper;
import com.ohgiraffers.hitechautoworks.auth.dto.*;
import com.ohgiraffers.hitechautoworks.part.dto.PartDTO;
import com.ohgiraffers.hitechautoworks.repair.dto.RepairDTO;
import com.ohgiraffers.hitechautoworks.res.dto.ResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Date;
import java.util.*;

@Service
public class UserService  {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;



    public String overlappedID(String id) {
        String result = userMapper.overlappedID(id);
        return result;
    }

    @Async
    public int regist(UserRegistDTO registDTO) {
        registDTO.setPass(passwordEncoder.encode(registDTO.getPass()));
        System.out.println("registDTO = " + registDTO);
        int result = 0;
        try {
            result = userMapper.regist(registDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public UserDTO findByUsername(String username) {

        UserDTO login = userMapper.findByUsername(username);

        if(!Objects.isNull(login)){
            return login;
        } else {
            return null;
        }
    }

    public int findcheck(int checknumber, String userId) {
        userMapper.findcheck(checknumber,userId);

        return 1;
    }



    public UserRegistDTO getAll(int userCode) {
        return userMapper.getAll(userCode);
    }

    public void deletePeople(int userCode) {
        userMapper.deletePeople(userCode);
    }

    public UserDTO findUserCode(int userCode) {
        return userMapper.findUserCode(userCode);
    }

    public int changepass(String currentPassword, String newPassword, String pw, int userCode) {
        if(passwordEncoder.matches(currentPassword,pw)) {
            String encodepw = passwordEncoder.encode(newPassword);
            userMapper.changepass(encodepw,userCode);
            return 1;
        } else {
            System.out.println("불일치");
            return 0;
        }
    }

    public void updateUser(Map<String, String> myprofile) {
        userMapper.updateUser(myprofile);
    }


    public ChartResponseDTO getpartchart() {

        List<Map<String, Object>> chart = userMapper.getPartChart();
        List<Integer> partStock = new ArrayList<>();
        List<String> partName = new ArrayList<>();

        for (Map<String, Object> entry : chart) {
            partStock.add((Integer) entry.get("part_stock"));
            partName.add((String) entry.get("part_name"));
        }

        return new ChartResponseDTO(partStock, partName);
    }

    public Map<String, Integer> getpersonchart() {
        List<Map<String,Integer>> listPerson = userMapper.getPersonChart();
        Map<String,Integer> map = new HashMap<>();

        for(Map<String,Integer> result : listPerson ){
            String role = String.valueOf(result.get("user_role"));
            Integer count = ((Number) result.get("role_count")).intValue();
            map.put(role, count);
        }

        return map;
    }

    public List<String>getTime(Date date1) {

//        for( Map<String,Integer> a : count){
//            System.out.println(a.get("time"));
//            System.out.println(a.get("timeCount"));
//            switch (a.get("time")) {
//                case 12:
//                    checkNull = employeeCount - a.get("timeCount");
//                    break;
//                case 14:
//                    checkNull = employeeCount - a.get("timeCount");
//                    break;
//                case 11:
//                    checkNull = employeeCount - a.get("timeCount");
//                    break;
//            }
//            System.out.println("checkNull = " + checkNull);
//        }
        int AllemployeeCount = userMapper.getCustomerCount();
        List<Map<String,Object>> count = userMapper.getTimeCount(date1);

        System.out.println("count = " + count);
        int checkNull = 0;
        String thisTime = "";
        List<String> disabledTimesList = new ArrayList<>();
        for (Map<String, Object> a : count) {


            Object rawTime = a.get("time");
            Object rawTimeCount = a.get("employeeCount");
            Object extraTime = a.get("extraTime");

            int time = Integer.parseInt(rawTime.toString());
            int employeeCount = ((Number) rawTimeCount).intValue();
            int extraTimeCount = ((Number) extraTime).intValue();

            System.out.println("time = " + time);
            System.out.println("employeeCount = " + employeeCount);
            System.out.println("extraTimeCount = " + extraTimeCount);
            for(int i = 0; i < extraTimeCount; i++) {

                switch (time) {
                    case 9:
                        checkNull = AllemployeeCount - employeeCount;
                        thisTime = "09:00:00"; time++;
                        break;
                    case 10:
                        checkNull = AllemployeeCount - employeeCount;
                        thisTime = "10:00:00"; time++;
                        break;
                    case 11:
                        checkNull = AllemployeeCount - employeeCount;
                        thisTime = "11:00:00"; time++;
                        break;
                    case 12:
                        checkNull = AllemployeeCount - employeeCount;
                        thisTime = "12:00:00"; time++;
                        break;
                    case 13:
                        checkNull = AllemployeeCount - employeeCount;
                        thisTime = "13:00:00"; time++;
                        break;
                    case 14:
                        checkNull = AllemployeeCount - employeeCount;
                        thisTime = "14:00:00"; time++;
                        break;
                    case 15:
                        checkNull = AllemployeeCount - employeeCount;
                        thisTime = "15:00:00"; time++;
                        break;
                    case 16:
                        checkNull = AllemployeeCount - employeeCount;
                        thisTime = "16:00:00"; time++;
                        break;
                    case 17:
                        checkNull = AllemployeeCount - employeeCount;
                        thisTime = "17:00:00"; time++;
                        break;
                    default:
                        System.out.println("Default case");
                        break;
                }

                System.out.println("checkNull = " + checkNull);
                if (checkNull <= 0) {
                    disabledTimesList.add(thisTime);
                }
            }
        }
        return disabledTimesList;
    }
}
