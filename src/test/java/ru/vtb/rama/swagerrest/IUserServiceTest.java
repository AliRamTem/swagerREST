package ru.vtb.rama.swagerrest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vtb.rama.swagerrest.exception.NoSuchUserException;
import ru.vtb.rama.swagerrest.model.User;
import ru.vtb.rama.swagerrest.service.impl.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IUserServiceTest {

    @Autowired
    UserServiceImpl service;

    @Test
    public void createAndFindUserById_Should_Ok() {
        User create = service.create(new User("userName","firstName","lastName",
                "email", "description", null));
        User foundUser = service.findById(create.getId());
        Assert.assertEquals(create.getId(), foundUser.getId());
    }

    @Test(expected = NoSuchUserException.class)
    public void findUserByIdThatNotExist_Should_Exception() {
        service.findById(100_000L);
    }

    @Test(expected = NoSuchUserException.class)
    public void deleteUser_Should_Exception() {
        Long id = service.create(new User()).getId();
        service.delete(id);
        service.findById(id);
    }

    @Test(expected = NoSuchUserException.class)
    public void deleteUserNotExist_Should_Exception() {
        Long id = 100_000L;
        service.delete(id);
    }

    @Test
    public void updateUser_should_Ok() {
        User oldUser = service.findById(16L);
        User updateUser = oldUser;
        updateUser.setUserName("Updated");
        service.update(16L, updateUser);
        Assert.assertEquals(oldUser.getUserName(), service.findById(16L).getUserName());
    }
}
