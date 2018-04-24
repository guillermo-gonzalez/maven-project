package com.mulesoft.training;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.mule.api.MuleEvent;
import org.mule.tck.junit4.FunctionalTestCase;
import org.mule.tck.junit4.rule.DynamicPort;

public class HelloMavenTest extends FunctionalTestCase {

	@Rule
	public DynamicPort myPort = new DynamicPort("http.port");
	
    @Test
    public void mavenFlowReturnsHelloMaven() throws Exception {
        runFlowAndExpect("mavenFlow", "Hello Maven!\n");
        System.out.println("\n\n***** mavenFlowReturnsHelloMavenhttp.port: " + myPort.getNumber() + " *****\n");
    }
    
    @Override
    protected String getConfigFile() {
        return "maven-project.xml";
    }

    @Test
    public void retrieveFlightsAddsAppropriateHeader() throws Exception {
      MuleEvent event = runFlow("retrieveFlights");
      System.out.println("\n\n***** retrieveFlightsAddsAppropriateHeader.port: " + myPort.getNumber() + " *****\n");
      String contentType = event.getMessage().getOutboundProperty("Content-Type");
      assertEquals("application/json", contentType);
    }  
}
