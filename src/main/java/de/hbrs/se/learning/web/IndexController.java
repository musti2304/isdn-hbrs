package de.hbrs.se.learning.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
class IndexController {

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

}
