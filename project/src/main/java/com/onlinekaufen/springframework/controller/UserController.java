package com.onlinekaufen.springframework.controller;

import com.onlinekaufen.springframework.dto.Response;
import com.onlinekaufen.springframework.dto.UserDTO;
import com.onlinekaufen.springframework.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final
    UserService userService;

    private final
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String userProfile(Model model, HttpServletRequest request) {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user instanceof UserDTO) {
            model.addAttribute("user", user);
            return "profile";
        } else {
            return "accessDenied";
        }
    }

    @RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
    public String updateProfile(Model model, HttpServletRequest request) {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user instanceof UserDTO) {
            UserDTO userDTO = (UserDTO) user;
            String firstName = request.getParameter("first_name");
            String lastName = request.getParameter("last_name");
            String phoneNo = request.getParameter("phone_no");
            String address = request.getParameter("address");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String country = request.getParameter("country");
            String postCode = request.getParameter("post_code");
            userDTO.setEmailId(userDTO.getEmailId());
            userDTO.setRole(userDTO.getRole());
            userDTO.setFirstName(firstName);
            userDTO.setLastName(lastName);
            userDTO.setPhoneNo(phoneNo);
            userDTO.setAddress(address);
            userDTO.setCity(city);
            userDTO.setState(state);
            userDTO.setCountry(country);
            userDTO.setPostCode(postCode);

            userService.updateUser(userDTO);
            userDTO = (UserDTO) userService.loadUserByUsername(userDTO.getEmailId());
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDTO, userDTO.getPassword(), userDTO.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            model.addAttribute("user", user);
            return "profile";
        } else {
            return "accessDenied";
        }

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public
    @ResponseBody
    Response<String> registerUser(HttpServletRequest request) {
        String userName = request.getParameter("registerUserName");
        String password = request.getParameter("registerPassword");
        System.out.println("Password is :" + password);
        logger.info("username" + userName);
        logger.info("Password is :" + password);
        //String confirmPassword = request.getParameter("registerConfirmPassword");
        String lastName = request.getParameter("registerLastName");
        String country = request.getParameter("registerCountry");
        String state = request.getParameter("registerState");
        String city = request.getParameter("registerCity");
        String postCode = request.getParameter("registerPostCode");
        String phoneNo = request.getParameter("registerPhoneNo");
        String address = request.getParameter("registerAddress");
        String alias = request.getParameter("registerAlias");
        String firstName = request.getParameter("registerFirstName");
        UserDTO userDTO = new UserDTO();
        userDTO.setEmailId(userName);
        userDTO.setPassword(passwordEncoder.encode(password));
        userDTO.setFirstName(firstName);
        userDTO.setLastName(lastName);
        userDTO.setPhoneNo(phoneNo);
        userDTO.setAddress(address);
        userDTO.setCountry(country);
        userDTO.setPostCode(postCode);
        userDTO.setCity(city);
        userDTO.setState(state);
        userDTO.setAlias(alias);
        userDTO.setRole("ROLE_USER");
        userDTO.setEnabled(true);
        userService.addUser(userDTO);
        return Response.ok("Successfully created user.", HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
    }

}
