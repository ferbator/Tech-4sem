package com.ferbator.shelterapi.services;

import com.ferbator.shelterapi.dao.dto.OwnerDto;
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

    private final ShelterService shelterService;

    public WebSecurityService(ShelterService shelterService) {
        this.shelterService = shelterService;
    }

    /**
     * Преобразует список ролей в список GrantedAuthority.
     *
     * @param roles коллекция ролей
     * @return коллекция GrantedAuthority
     */
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<String> roles) {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    /**
     * Преобразует объект OwnerDto в UserDetails для Spring Security.
     *
     * @param owner DTO, содержащий логин, пароль и роль
     * @return объект User, реализующий UserDetails
     */
    public UserDetails mapOwnerDtoToUserDetails(OwnerDto owner) {
        return User.withUsername(owner.getLogin())
                .password(owner.getPassword())
                .authorities(mapRolesToAuthorities(List.of(owner.getRole())))
                .build();
    }

    /**
     * Загружает пользователя по логину и возвращает объект UserDetails.
     * Если пользователь не найден, выбрасывает исключение UsernameNotFoundException.
     *
     * @param login Логин пользователя
     * @return UserDetails для переданного логина
     * @throws UsernameNotFoundException если пользователь не найден
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        OwnerDto owner = shelterService.findOwnerByLogin(login);
        if (owner == null) {
            throw new UsernameNotFoundException("Owner with login '" + login + "' doesn't exist");
        }
        return mapOwnerDtoToUserDetails(owner);
    }
}
