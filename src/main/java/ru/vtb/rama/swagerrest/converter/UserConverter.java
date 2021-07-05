package ru.vtb.rama.swagerrest.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.vtb.rama.generated.dto.ShortUserDto;
import ru.vtb.rama.generated.dto.UserDto;
import ru.vtb.rama.swagerrest.exception.EmptyVacationsListException;
import ru.vtb.rama.swagerrest.model.User;
import ru.vtb.rama.swagerrest.model.Vacation;

import java.util.List;
import java.util.Objects;


@Component
@RequiredArgsConstructor
public class UserConverter {

    @Qualifier("standardModelMapper")
    private final ModelMapper modelMapper;

    public ShortUserDto toShortDto(User user) {
        return modelMapper.map(user, ShortUserDto.class);
    }

    public UserDto toDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public User from(UserDto userdto) {
        User user = modelMapper.map(userdto, User.class);
        user.getVacationsList().forEach(list -> list.setUser(user));
        /*List<Vacation> vacationsList = user.getVacationsList();
        if(Objects.isNull(vacationsList)){
            throw new EmptyVacationsListException();
        }
        vacationsList.forEach(vacation -> vacation.setUser(user));*/

        return user;
    }
}
