package org.example.moquette.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 罗涛
 * @title IndexController
 * @date 2020/10/10 17:12
 */
@RestController
@RequestMapping(value = "/demo")
public class IndexController {

    @RequestMapping(value = "/index",  method = RequestMethod.GET)
    public String index(){
        return "Hello, Moquette!";
    }
}
