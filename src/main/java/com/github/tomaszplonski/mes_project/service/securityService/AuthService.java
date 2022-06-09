package com.github.tomaszplonski.mes_project.service.securityService;

import com.github.tomaszplonski.mes_project.model.UserEntity;
import com.github.tomaszplonski.mes_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AuthService implements UserDetailsService {

    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userService.findByUserName(username);

        if(user==null){
            throw new UsernameNotFoundException("No user with username " + username);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));

        return new User(user.getUsername(),user.getPassword(),grantedAuthorities);

    }

    @Autowired
    public void setUserRepository(UserService userService) {
        this.userService = userService;
    }

}
