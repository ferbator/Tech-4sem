package com.ferbator.services;

import com.ferbator.dao.dto.OwnerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service("webSecurityService")
public class WebSecurityService implements UserDetailsService {

    @Autowired
    private ShelterService service;

    public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<String> roles){
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public User mapUserBDtoUserDetails(OwnerDto owner){
        return new User(owner.getLogin(), owner.getPassword(), mapRolesToAuthorities(List.of(owner.getRole())));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        OwnerDto owner = service.findOwnerByLogin(login);
        if(owner == null){
            throw new UsernameNotFoundException("Owner doesn't exist");
        }
        return mapUserBDtoUserDetails(owner);
    }
}
