package com.pfe.backend.services.impl;


import com.pfe.backend.dao.UserDao;
import com.pfe.backend.dao.entity.User;
import com.pfe.backend.dao.enums.RoleEnum;
import com.pfe.backend.services.UserService;
import com.pfe.backend.services.mapper.UserMapper;
import com.pfe.backend.services.model.MakeUserAdminDto;
import com.pfe.backend.services.model.SignupRequest;
import com.pfe.backend.services.model.UserDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private  final UserDao userDao;
    private final UserMapper userMapper;
    private final ModelMapper modelMapper;

    @Override
    public UserDTO add(SignupRequest signupRequest) {
        User user=modelMapper.map(signupRequest, User.class);
        user.setRole(RoleEnum.USER_ROLE);
        return userMapper.mapToDTO(userDao.add(user));
    }

    @Override
    public List<UserDTO> findAll() {
        return userMapper.mapToDTOList(userDao.findAll());
    }

    @Override
    public UserDTO findByEmail( String email) {
        return userMapper.mapToDTO(userDao.findByEmail(email));
    }

    @Override
    public void deleteById(long id) {
        userDao.deleteById(id);
    }

    @Override
    public void makeUserAdmin(MakeUserAdminDto makeUserAdminDto) {
        userDao.makeUserAdmin(makeUserAdminDto.getEmail());
    }
}
