package com.github.tomaszplonski.mes_project.service.securityService;

import com.github.tomaszplonski.mes_project.model.UserEntity;
import com.github.tomaszplonski.mes_project.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity findByUserName(String name) {
        return userRepository.findByUsername(name).get();
    }

    @Override
    public void saveUser(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        user.setRole("ROLE_USER");
        userRepository.save(user);

    }
}
