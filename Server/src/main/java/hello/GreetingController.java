package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(value = "/justrun")
public class GreetingController {

    @ResponseBody
    @RequestMapping(value = "/{other}")
    public String greeting(@PathVariable String other) {
        return "Esta es la cadena: " + other;
    }
}