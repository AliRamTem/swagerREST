package ru.vtb.rama.swagerrest.service.impl;

import com.querydsl.core.BooleanBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.vtb.rama.swagerrest.exception.NoSuchUserException;
import ru.vtb.rama.swagerrest.model.QUser;
import ru.vtb.rama.swagerrest.model.User;
import ru.vtb.rama.swagerrest.repository.UserRepository;
import ru.vtb.rama.swagerrest.service.AbstractBaseService;
import ru.vtb.rama.swagerrest.service.IUserService;


@Service
@AllArgsConstructor
@Getter
@Slf4j
public class UserServiceImpl extends AbstractBaseService<User, Long, QUser, UserRepository>
        implements IUserService {

    private final UserRepository repository;

    @Override
    public Page<User> list(Pageable pageable, String search) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(QUser.user.isDeleted.isFalse());
        return findAll(booleanBuilder, pageable);
    }

    @Override
    public User findById(Long id){
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(QUser.user.isDeleted.isFalse());
        booleanBuilder.and(QUser.user.id.eq(id));
        return get(booleanBuilder).orElseThrow(() -> new NoSuchUserException(id));
    }

    @Override
    public User create(User user) {
        return save(user);
    }

    @Override
    public User update(Long id, User user) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(QUser.user.isDeleted.isFalse());
        booleanBuilder.and(QUser.user.id.eq(id));
        User oldUser = get(booleanBuilder).orElseThrow(() -> new NoSuchUserException(id));
        user.setId(id);
        user.setCreatedAt(oldUser.getCreatedAt());
        return save(user);
    }

    @Override
    public void delete(Long id) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(QUser.user.isDeleted.isFalse());
        booleanBuilder.and(QUser.user.id.eq(id));
        User user = get(booleanBuilder).orElseThrow(() -> new NoSuchUserException(id));
        user.setDeleted(true);
        save(user);
    }
}
