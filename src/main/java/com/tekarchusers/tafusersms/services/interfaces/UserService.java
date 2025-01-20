package com.tekarchusers.tafusersms.services.interfaces;

import com.tekarchusers.tafusersms.models.Users;
import org.springframework.http.ResponseEntity;

public interface UserService {
    Users registerUser(Users user);
    Users getUserById(Long userId);
    void updateUser(Long userId, Users user);

}
