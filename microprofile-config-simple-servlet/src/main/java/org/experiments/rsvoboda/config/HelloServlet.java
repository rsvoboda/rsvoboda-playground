package org.experiments.rsvoboda.config;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class HelloServlet extends HttpServlet {

    @Inject
    @ConfigProperty(name = "port_number")
    Integer port;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //int port = ConfigProvider.getConfig().getValue("port_number", Integer.class);

        resp.getWriter().println("Value of port: " + port);
    }
}
