package micronaut.hello.world;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class HelloControllerTest {

	@Inject
	HelloClient helloClient;

    @Test
    void testHello() {
        assertEquals(
                "Hello World",
                helloClient.hello().blockingGet(),
                "Endpoint didn't provide expected response");
    }
}
