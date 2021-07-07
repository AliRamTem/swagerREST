package ru.vtb.rama.swagerrest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vtb.rama.swagerrest.exception.NoSuchUserException;
import ru.vtb.rama.swagerrest.model.User;
import ru.vtb.rama.swagerrest.repository.UserRepository;
import ru.vtb.rama.swagerrest.service.impl.UserServiceImpl;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserServiceImpl service;

    @Test
    public void whenFindById_thenReturnUser() {
        // given
        User user = new User();
        entityManager.persist(user);
        entityManager.flush();
        Long id = user.getId();

        // when
        User found = userRepository.findById(id).get();

        // then
        Assert.assertEquals(user.getId(), found.getId());
    }

    @Test(expected = NoSuchUserException.class)
    public void findUserByIdThatNotExist_Should_Exception() {
        service.findById(100_000L);
    }
}
