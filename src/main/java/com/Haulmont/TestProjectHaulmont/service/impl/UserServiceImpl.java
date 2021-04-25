package com.Haulmont.TestProjectHaulmont.service.impl;

import com.Haulmont.TestProjectHaulmont.DTO.UserDTO;
import com.Haulmont.TestProjectHaulmont.mapper.AllMapper;
import com.Haulmont.TestProjectHaulmont.model.User;
import com.Haulmont.TestProjectHaulmont.repository.UserRepository;
import com.Haulmont.TestProjectHaulmont.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AllMapper allMapper;
    private final UserRepository userRepository;

    @Override
    public UserDTO getById(String id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Ошибка, нет такого пользователя!");
        }
        return allMapper.map(userRepository.findById(id).get(), UserDTO.class);
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        if (userDTO != null) {
            userDTO.setId(UUID.randomUUID().toString());
            User user = userRepository.save(allMapper.map(userDTO, User.class));
            return allMapper.map(user, UserDTO.class);
        }
        return null;
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        if (userRepository.findById(userDTO.getId()).isEmpty()) {
            throw new RuntimeException("Ошибка, нет такого пользователя!");
        }
        User user = userRepository.save(allMapper.map(userDTO, User.class));
        return allMapper.map(user, UserDTO.class);
    }

    @Override
    public void delete(String id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Ошибка, нет такого пользователя!");
        }
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDTO> findAll() {
        return allMapper.mapAsList(userRepository.findAll(), UserDTO.class);
    }

    @Override
    public List<UserDTO> findAllByBankId(String id) {
        return allMapper.mapAsList(userRepository.findAllByBankId(id), UserDTO.class);
    }
}
