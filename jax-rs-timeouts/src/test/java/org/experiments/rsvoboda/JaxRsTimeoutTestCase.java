package org.experiments.rsvoboda;
        
import java.util.concurrent.TimeUnit;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JaxRsTimeoutTestCase {

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void connectTimeout() {
        expected.expect(ProcessingException.class);
        Client client = ClientBuilder.
                newBuilder().
                connectTimeout(1, TimeUnit.MILLISECONDS).
                build();
        //hope no one listens here
        client.target("http://127.0.0.1").
                request().
                get();
    }

    @Test
    public void readTimeout() {
        expected.expect(ProcessingException.class);
        Client client = ClientBuilder.
                newBuilder().
                readTimeout(1, TimeUnit.MILLISECONDS).
                build();
        //java.cz is too slow to answer in 1ms
        client.target("http://java.cz").
                request().
                get();
    }
}