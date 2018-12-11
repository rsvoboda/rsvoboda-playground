package micronaut.hello.world;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Single;

import javax.validation.constraints.NotBlank;

@Client("/")
public interface HelloClient {

    @Get("/hello")
    Single<String> hello();


    @Get("/hello/{name}")
    Single<String> helloWithName(@NotBlank String name);
}
