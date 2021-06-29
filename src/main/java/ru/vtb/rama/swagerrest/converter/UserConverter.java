package ru.vtb.rama.swagerrest.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.vtb.rama.generated.dto.ShortUserDto;
import ru.vtb.rama.generated.dto.UserDto;
import ru.vtb.rama.swagerrest.model.entity.User;


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
        //user.getWebhookHeaders().forEach(webhookHeader -> webhookHeader.setWebhook(webhook));
        return user;
    }
}
