package com.onlinekaufen.springframework.service.mySql;

import com.onlinekaufen.springframework.dao.UserDAO;
import com.onlinekaufen.springframework.dto.UserDTO;
import com.onlinekaufen.springframework.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    private DbService dbService;

    @Autowired
    public UserDetailServiceImpl(DbService dbService) {
        this.dbService = dbService;
    }

    @Override
    public UserDTO loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDAO userDAO = dbService.getDao(UserDAO.class);
        return userDAO.getUserByUsername(username);
    }

    @Override
    public UserDTO changePassword(UserDTO userDTO) {
        UserDAO userDAO = dbService.getDao(UserDAO.class);
        userDAO.changePassword(userDTO.getPassword(), userDTO.getUsername());
        return loadUserByUsername(userDTO.getUsername());
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        UserDAO userDAO = dbService.getDao(UserDAO.class);
        userDAO.updateUser(userDTO);

    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        UserDAO adminDAO = dbService.getDao(UserDAO.class);
        int userId = adminDAO.addUser(userDTO);
        userDTO.setId(userId);
        return userDTO;
    }

    @Override
    public void deleteUser(Long userId) {
        UserDAO userDAO = dbService.getDao(UserDAO.class);
        userDAO.deleteUser(userId);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        UserDAO userDAO = dbService.getDao(UserDAO.class);
        return userDAO.getAllUsers();
    }

    @Override
    public void banUser(String id) {
        UserDAO userDAO = dbService.getDao(UserDAO.class);
        userDAO.banUser(id);
    }

    @Override
    public void unbanUser(String id) {
        UserDAO userDAO = dbService.getDao(UserDAO.class);
        userDAO.unbanUser(id);
    }
}
