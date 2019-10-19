package com.onlinekaufen.springframework.controller;

import com.onlinekaufen.springframework.dto.MessageDTO;
import com.onlinekaufen.springframework.service.MessengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller
public class MessengerController {

    long tempUserId = 1;

    private final MessengerService messengerService;

    @Autowired
    public MessengerController(MessengerService messengerService) {
        this.messengerService = messengerService;
    }

    @RequestMapping(value = "/messenger", method = RequestMethod.GET)
    public String messenger(Model model) {
        List<MessageDTO> dto  = messengerService.getUserMessages(this.tempUserId);
        model.addAttribute("messages", dto);
        return "messenger";
    }




}
