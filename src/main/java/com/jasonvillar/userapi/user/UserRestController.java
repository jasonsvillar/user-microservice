package com.jasonvillar.userapi.user;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        Map<String, Object> map = new LinkedHashMap<String, Object>();

        List<User> userList = this.userService.getAll();

        int status;
        HttpStatus httpStatus;

        if (userList.isEmpty()) {
            status = 0;
            httpStatus = HttpStatus.NOT_FOUND;
        } else {
            status = 1;
            map.put("data", userList);
            httpStatus = HttpStatus.OK;
        }

        map.put("status", status);
        return new ResponseEntity<>(map, httpStatus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();

        Optional<User> userOptional = this.userService.getById(id);

        int status;
        HttpStatus httpStatus;

        if (userOptional.isPresent()) {
            status = 1;
            map.put("data", userOptional.get());
            httpStatus = HttpStatus.OK;
        } else {
            status = 0;
            httpStatus = HttpStatus.NOT_FOUND;
        }

        map.put("status", status);
        return new ResponseEntity<>(map, httpStatus);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody User user) {

        Map<String, Object> map = new LinkedHashMap<String, Object>();

        User userCreated = this.userService.insert(user);

        HttpStatus httpStatus;
        
        if (userCreated != null) {
            map.put("status", 1);
            httpStatus = HttpStatus.CREATED;
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(map, httpStatus);
    }

    @PutMapping
    public ResponseEntity<?> save(@RequestBody User user) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();

        if (user.getId() == null) {
            map.put("status", 0);
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }

        Optional<User> userOptional = this.userService.getById(user.getId());

        if (userOptional.isEmpty()) {
            map.put("status", 0);
            return new ResponseEntity<>(map, HttpStatus.NO_CONTENT);
        }

        User userUpdated = this.userService.save(user);

        HttpStatus httpStatus;
        
        if (userUpdated != null) {
            map.put("status", 1);
            httpStatus = HttpStatus.OK;
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(map, httpStatus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();

        Optional<User> userOptional = this.userService.getById(id);

        if (userOptional.isEmpty()) {
            map.put("status", 0);
            return new ResponseEntity<>(map, HttpStatus.NO_CONTENT);
        }

        this.userService.deleteById(id);

        map.put("status", 1);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
