package com.hytc.o2o.controller.superAdmin;

import com.hytc.o2o.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class RestMain {

    @Autowired
    private UserService userService;

}
