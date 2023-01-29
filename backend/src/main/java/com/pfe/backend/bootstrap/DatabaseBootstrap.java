package com.pfe.backend.bootstrap;

import com.pfe.backend.constants.ApplicationConstants;
import com.pfe.backend.dao.UserDao;
import com.pfe.backend.dao.entity.User;
import com.pfe.backend.dao.enums.RoleEnum;
import com.pfe.backend.services.UserService;
import com.pfe.backend.services.model.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DatabaseBootstrap implements CommandLineRunner {
    public static final String APPLICATION_ADMIN_EMAIL = "application.admin.email";
    private final UserService userService;
    private final Environment environment;
    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;
    @Override
    public void run(String... args) throws Exception {
        String email=ApplicationConstants.DEFAULT_ADMIN_EMAIL;
        if(environment.containsProperty(APPLICATION_ADMIN_EMAIL)){
            email=environment.getProperty(APPLICATION_ADMIN_EMAIL);
        }
        UserDTO admin=userService.findByEmail(email);
        if (admin == null){
            User user=new User();
            user.setEmail(email);
            user.setRole(RoleEnum.ADMIN_ROLE);
            user.setPassword(passwordEncoder.encode("12345678"));
            userDao.add(user);
        }
    }
}
