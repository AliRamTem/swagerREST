package ru.vtb.rama.swagerrest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.vtb.rama.swagerrest.model.User;

public interface IUserService {
    Page<User> list(Pageable pageable, String search);

    User findById(Long id);

    User create(User user);

    User update(Long id, User user);

    void delete(Long id);
}
