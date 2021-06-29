package ru.vtb.rama.swagerrest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.vtb.rama.generated.dto.ShortUserDto;
import ru.vtb.rama.generated.dto.UserDto;
import ru.vtb.rama.swagerrest.exception.NoSuchUserException;

public interface IUserService {
    Page<ShortUserDto> list(Pageable pageable, String search);

    UserDto findById(Long id) throws NoSuchUserException;

    UserDto create(UserDto userDto);

    UserDto update(Long id, UserDto userDto) throws NoSuchUserException;

    void delete(Long id) throws NoSuchUserException;
}
