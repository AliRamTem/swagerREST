package ru.vtb.rama.swagerrest.repository;

import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;
import ru.vtb.rama.swagerrest.model.QUser;
import ru.vtb.rama.swagerrest.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, Long, QUser> {
    @Override
    @EntityGraph(attributePaths = {"vacationsList"})
    Optional<User> findOne(Predicate predicate);
}
