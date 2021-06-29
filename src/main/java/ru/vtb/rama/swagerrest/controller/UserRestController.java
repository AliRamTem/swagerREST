package ru.vtb.rama.swagerrest.controller;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vtb.rama.generated.dto.ShortUserDto;
import ru.vtb.rama.generated.dto.UserDto;
import ru.vtb.rama.swagerrest.exception.NoSuchUserException;
import ru.vtb.rama.swagerrest.service.impl.UserService;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@Slf4j
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Page<ShortUserDto> list(Pageable pageable,
                                   @RequestParam(name = "search", required = false) String search) {
        return userService.list(pageable, search);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity getById(@PathVariable(name = "id") Long id) {
        try {
            return new ResponseEntity(userService.findById(id), HttpStatus.OK);
        } catch (NoSuchUserException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PostMapping("/users")
    public ResponseEntity create(@Valid @RequestBody UserDto user) {
        return new ResponseEntity(userService.create(user), HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity update(@PathVariable(name = "id") Long id, @Valid @RequestBody UserDto user) {
        try {
            return new ResponseEntity(userService.update(id, user), HttpStatus.OK);
        } catch (NoSuchUserException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") Long id) {
        try {
            userService.delete(id);
        } catch (NoSuchUserException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
