package org.consume.com.swwd;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/swwd")
public class SwwdController {

    @RequestMapping("/init")
    public ModelAndView init(){
        return new ModelAndView("/swwd/index");
    }
}
