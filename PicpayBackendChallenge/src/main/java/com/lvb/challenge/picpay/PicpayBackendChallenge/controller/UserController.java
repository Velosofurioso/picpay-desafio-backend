package com.lvb.challenge.picpay.PicpayBackendChallenge.controller;

import com.lvb.challenge.picpay.PicpayBackendChallenge.dto.user.CreateUserDto;
import com.lvb.challenge.picpay.PicpayBackendChallenge.dto.user.UserDto;
import com.lvb.challenge.picpay.PicpayBackendChallenge.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseBody
    ResponseEntity<UserDto> createUser(@Validated @RequestBody CreateUserDto createUserDto) {

        // Data Validation
        //TODO add validation for data received

        // Password Validation

        //Service
        var createdUserId = userService.createUser(createUserDto);

        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUserId).toUri();
        return ResponseEntity.created(uri).body(createUserDto);
    }

    @PutMapping("/{id}")
    @ResponseBody
    ResponseEntity<UserDto> updateUser(@PathVariable(value = "id") long id, @Validated @RequestBody final UserDto userDto) {

        // Data Validation
        //TODO add validation for data received

        //Service
        userService.updateUser(userDto, id);

        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/{id}")
    ResponseEntity<UserDto> getUser(@PathVariable(value = "id") long id) {
        var user = userService.getUserById(id);

        if (user == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> removeUser(@PathVariable(value = "id") long id) {

        // Data Validation
        //TODO add validation for data received

        //Service
        userService.deleteUser(id);

        return ResponseEntity.ok().build();
    }
}
