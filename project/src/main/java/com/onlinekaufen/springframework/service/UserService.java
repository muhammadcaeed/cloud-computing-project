package com.onlinekaufen.springframework.service;

import com.onlinekaufen.springframework.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
/**
 * Created by Prashant on 12/05/18.
 */
public interface UserService extends UserDetailsService {

    UserDTO changePassword(UserDTO userDTO);

    void updateUser(UserDTO userDTO);

    UserDTO addUser(UserDTO userDTO);

    void deleteUser(Long userId);

    List<UserDTO> getAllUsers();

    void banUser(String id);

    void unbanUser(String id);
}
