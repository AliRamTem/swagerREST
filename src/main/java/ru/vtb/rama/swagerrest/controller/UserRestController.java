package ru.vtb.rama.swagerrest.controller;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vtb.rama.generated.dto.ShortUserDto;
import ru.vtb.rama.generated.dto.UserDto;
import ru.vtb.rama.swagerrest.converter.UserConverter;
import ru.vtb.rama.swagerrest.service.impl.UserServiceImpl;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@Slf4j
public class UserRestController {

    private UserServiceImpl service;
    private UserConverter converter;

    @GetMapping("/users")
    public Page<ShortUserDto> list(Pageable pageable,
                                   @RequestParam(name = "search", required = false) String search) {
        return service.list(pageable, search).map(converter::toShortDto);
    }

    @GetMapping("/users/{id}")
    public UserDto getById(@PathVariable(name = "id") Long id) {
        return converter.toDto(service.findById(id));
    }

    @PostMapping("/users")
    public UserDto create(@Valid @RequestBody UserDto user) {
        return converter.toDto(service.create(converter.from(user)));
    }

    @PutMapping("/users/{id}")
    public UserDto update(@PathVariable(name = "id") Long id, @Valid @RequestBody UserDto user) {
        return converter.toDto(service.update(id, converter.from(user)));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
