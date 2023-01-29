package com.pfe.backend.services.mapper;


import com.pfe.backend.dao.entity.User;
import com.pfe.backend.services.model.UserDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper implements GenericMapper<User,UserDTO>{

private final ModelMapper modelMapper;

    @Override
    public User mapToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO,User.class);
    }

    @Override
    public UserDTO mapToDTO(User user) {
        if(user!=null){
            UserDTO userDTO= modelMapper.map(user,UserDTO.class);
            userDTO.setRole(user.getRole().getName());
            return userDTO;
        }
        return null;
    }
}
