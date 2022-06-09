package com.github.tomaszplonski.mes_project.service.securityService;

import com.github.tomaszplonski.mes_project.model.UserEntity;

public interface UserService {

    UserEntity findByUserName(String name);

    void saveUser(UserEntity user);
}
