package com.tekarchusers.tafusersms.contollers;

import com.tekarchusers.tafusersms.models.Users;
import com.tekarchusers.tafusersms.services.UserServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;


@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserServiceImp userService;

    @PostMapping("/register")
    public Users registerUser(@RequestBody Users user) throws Exception {
        return userService.registerUser(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable("userId") Long id) {
        Users user = userService.getUserById(id);
        if (user != null) {

            return ResponseEntity.ok(user);
        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id " + id + "  not found ");
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable("userId") Long id, @RequestBody Users user) {

        Users RetrivedUser = userService.getUserById(id);
        if (RetrivedUser != null) {
            try {
                userService.updateUser(id, user);
                return ResponseEntity.ok("User updated successfully");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to update user");
            }
        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id " + id + "  not found ");
        }
    }


    @ExceptionHandler
    public ResponseEntity<?> respondWithError(Exception e) {
        return new ResponseEntity<>("Exception Occurred. More Info :"
                + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
