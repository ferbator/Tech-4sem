package com.ferbator.shelterapi.services;

import com.ferbator.shelterapi.dao.dto.OwnerDto;
import com.ferbator.shelterapi.dao.entities.Owner;
import com.ferbator.shelterapi.dao.daoImpl.OwnerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Сервис для управления пользователями (владельцами).
 * Обеспечивает безопасную обработку паролей перед сохранением в базу.
 */
@Service
public class RegistrationService {

    private final OwnerRepository ownerRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(OwnerRepository ownerRepository, PasswordEncoder passwordEncoder) {
        this.ownerRepository = ownerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Регистрирует нового владельца:
     * - Проверяет наличие владельца с таким же логином.
     * - Хэширует пароль с помощью BCrypt.
     * - Сохраняет данные в базу.
     *
     * @param ownerDto данные о новом владельце.
     * @throws IllegalArgumentException если логин уже занят.
     */
    @Transactional
    public void registerNewOwner(OwnerDto ownerDto) {
        if (ownerRepository.findByLogin(ownerDto.getLogin()) != null) {
            throw new IllegalArgumentException("Логин уже используется");
        }
        String rawPassword = ownerDto.getPassword();
        // Хэшируем пароль перед сохранением
        String encodedPassword = passwordEncoder.encode(rawPassword);
        ownerDto.setPassword(encodedPassword);

        Owner owner = new Owner(ownerDto);
        ownerRepository.save(owner);
    }
}
