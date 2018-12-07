package micronaut.hello.world;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Single;

@Client("/")
public interface HelloClient {

    @Get("/hello")
    Single<String> hello();
}
