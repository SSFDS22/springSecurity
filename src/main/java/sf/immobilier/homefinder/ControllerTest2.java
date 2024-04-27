package sf.immobilier.homefinder;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test2")
public class ControllerTest2 {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String printHello() {
        return "Hello Le monde";
    }
}

