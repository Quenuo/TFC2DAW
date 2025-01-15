package com.mapper;

import com.dto.BanInfoDTO;
import com.dto.UserProfileDTO;
import com.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {

    public UserProfileDTO toDTO(User user,String parkName){
        UserProfileDTO userProfileDTO=new UserProfileDTO();
        userProfileDTO.setId(user.getId());
        userProfileDTO.setRol(user.getRol());
        userProfileDTO.setEmail(user.getEmail());
        userProfileDTO.setParkName(parkName);
        userProfileDTO.setBanned(user.getBanned());
        userProfileDTO.setUsername(user.getName());

        BanInfoDTO banInfoDTO=new BanInfoDTO(user.getBanReason(), LocalDateTime.now(),user.getBanExpirationDate());
        userProfileDTO.setBanInfoDTO(banInfoDTO);

        return userProfileDTO;
    }

}
