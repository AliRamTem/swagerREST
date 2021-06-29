package ru.vtb.rama.swagerrest.service.impl;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.vtb.rama.generated.dto.ShortUserDto;
import ru.vtb.rama.generated.dto.UserDto;
import ru.vtb.rama.swagerrest.converter.UserConverter;
import ru.vtb.rama.swagerrest.exception.NoSuchUserException;
import ru.vtb.rama.swagerrest.model.entity.User;
import ru.vtb.rama.swagerrest.repository.UserRepository;
import ru.vtb.rama.swagerrest.service.IUserService;

import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Getter
@Slf4j
public class UserService implements IUserService {

    @Autowired
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Override
    public Page<ShortUserDto> list(Pageable pageable, String search) {
        Page<ShortUserDto> page = new PageImpl<ShortUserDto>(
                userRepository
                        .findAll(pageable)
                        .stream().map(userConverter::toShortDto)
                        .collect(Collectors.toList()));
        return page;
    }

    @Override
    public UserDto findById(Long id) throws NoSuchUserException {
        return userConverter.toDto(userRepository.findById(id).orElseThrow(() -> new NoSuchUserException("Нет пользователя с таким id")));
    }

    @Override
    public UserDto create(UserDto userDto) {
        return userConverter.toDto(userRepository.save(userConverter.from(userDto)));
    }

    @Override
    public UserDto update(Long id, UserDto userDto) throws NoSuchUserException {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchUserException("Нет пользователя с таким id"));
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setDescription(userDto.getDescription());
        return userConverter.toDto(userRepository.save(user));
    }

    @Override
    public void delete(Long id) throws NoSuchUserException {
        userRepository.findById(id).orElseThrow(() -> new NoSuchUserException("Нет пользователя с таким id"));
        userRepository.deleteById(id);
    }
}
