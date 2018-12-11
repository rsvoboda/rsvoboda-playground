package micronaut.hello.world;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.reactivex.Single;

import javax.validation.constraints.NotBlank;

@Controller("/hello") 
public class HelloController {

    @Get(produces = MediaType.TEXT_PLAIN) 
    public String index() {
        return "Hello World";
    }

    @Get(uri = "/{name}", produces = MediaType.TEXT_PLAIN)
    public Single<String> hello(@NotBlank String name) {
        return Single.just("Hello " + name + "!");
    }
}