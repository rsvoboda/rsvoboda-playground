package org.experiments.rsvoboda.health;

import javax.jws.WebService;

import javax.ejb.Stateless;

@Stateless
@WebService(serviceName="EndpointService", portName="EndpointPort", endpointInterface = "org.experiments.rsvoboda.health.Endpoint")
public class EndpointImpl
{

   public String echo(String input)
   {
      return input;
   }
}
