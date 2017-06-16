package org.experiments.rsvoboda;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class SimpleFilter implements ContainerRequestFilter, ContainerResponseFilter {
    @Override public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        System.out.println("Request: " + containerRequestContext);
        String xxx = String.valueOf(System.nanoTime());
        System.out.println( "Set: " + xxx );
        containerRequestContext.getHeaders().putSingle("rs", xxx);
    }

    @Override public void filter(ContainerRequestContext containerRequestContext,
            ContainerResponseContext containerResponseContext) throws IOException {
        System.out.println("Response: " + containerRequestContext + " " + containerResponseContext);
        System.out.println(" ...... " + containerRequestContext.getHeaderString("rs"));
    }
}
