package spring.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @RequestMapping(method = RequestMethod.GET, path = "/hola")
    public String bla(@RequestParam String param1) {
        return "Hola Mundo! - Spring. Param: " + param1;
    }
}
