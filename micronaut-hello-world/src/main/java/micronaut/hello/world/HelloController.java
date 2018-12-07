package micronaut.hello.world;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

@Controller("/hello") 
public class HelloController {
    @Get(produces = MediaType.TEXT_PLAIN) 
    public String index() {
        return "Hello World";
    }
}