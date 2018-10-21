package com.hytc.o2o.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("jump")
public class FrontJumpMapperController {

    @RequestMapping(path = "index",method = RequestMethod.GET)
    public String jumpToIndex(){
        return "/frontend/index";
    }
}
