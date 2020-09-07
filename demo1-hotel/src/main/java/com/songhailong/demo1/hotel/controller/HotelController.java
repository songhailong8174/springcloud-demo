package com.songhailong.demo1.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Controller
public class HotelController {

    @GetMapping("/test")
    public Object test() {
        return "hotel-test";
    }
}
