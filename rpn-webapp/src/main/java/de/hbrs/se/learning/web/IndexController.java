package de.hbrs.se.learning.web;

import de.hbrs.se.learning.service.RpnExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
class IndexController {

    @Autowired
    private RpnExecutor executor;

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("expression", "20 2 4 2 * + 2 * -");
        modelAndView.addObject("result", null);
        return modelAndView;
    }

    @RequestMapping(value = "/calc", method = RequestMethod.POST)
    public ModelAndView calc(@RequestParam("expression") String expression) {
        ModelAndView modelAndView = new ModelAndView("index");
        try {
            double result = executor.execute(expression);
            modelAndView.addObject("result", result);
            modelAndView.addObject("showResult", true);
            modelAndView.addObject("expression", expression);
        } catch (RuntimeException e) {
            modelAndView.addObject("showError", true);
            modelAndView.addObject("errorMessage", e.getMessage());
        }
        return modelAndView;
    }
}
