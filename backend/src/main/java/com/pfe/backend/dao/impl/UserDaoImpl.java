package com.pfe.backend.dao.impl;

import com.pfe.backend.dao.entity.User;
import com.pfe.backend.dao.UserDao;
import com.pfe.backend.dao.enums.RoleEnum;
import com.pfe.backend.dao.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    private final UserRepository userRepository;



    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public void update(User user, long id) {
        User oldUser= findById(id);
        if (oldUser!=null){
            user.setId(id);
            userRepository.save(user);
        }
    }

    @Override
    public void deleteById(long id) {
        User oldUser= findById(id);
        if (oldUser!=null){
            userRepository.delete(oldUser);
        }
    }

    @Override
    public void makeUserAdmin(String email) {
        User user = findByEmail(email);
        user.setRole(RoleEnum.ADMIN_ROLE);
        update(user, user.getId());
    }

}
