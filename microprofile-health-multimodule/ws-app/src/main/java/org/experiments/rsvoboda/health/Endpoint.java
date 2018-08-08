package org.experiments.rsvoboda.health;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService (name="Endpoint")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Endpoint
{
   @WebMethod(operationName = "echoString", action = "urn:EchoString")
   String echo(String input);
}
