package components.controllers.streams;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("streams_training")
public class StreamsController {

    @GetMapping("")
    public String getStreamTrainingHome(){
        return "streams";
    }

}
