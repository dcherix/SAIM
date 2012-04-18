package de.uni_leipzig.simba.saim.widget;

import static org.junit.Assert.*;

import org.junit.Test;

import de.uni_leipzig.simba.saim.core.Configuration;
import de.uni_leipzig.simba.saim.core.DefaultEndpointLoader;
import de.uni_leipzig.simba.saim.gui.widget.ManualMetricForm;

public class TestManualMetricForm {
	@Test
	public void testTestPropertiesAreSet() {
		Configuration c = Configuration.getInstance();
		c.setSourceEndpoint(DefaultEndpointLoader.getDefaultEndpoints().get("lgd.aksw - Drugbank"));
		c.setTargetEndpoint(DefaultEndpointLoader.getDefaultEndpoints().get("lgd.aksw - Sider"));
		c.addPropertiesMatch("rdfs:label", "rdfs:FalseLable");
		ManualMetricForm form = new ManualMetricForm();
		
		assertTrue(form.testPropertiesAreSet("trigrams(src.rdfs:label,dest.rdfs:FalseLable)", 0.5d));
		assertTrue(form.testPropertiesAreSet("ADD(0.1*trigrams(src.rdfs:label,dest.rdfs:FalseLable)|0.5,0.9*trigrams(src.rdfs:label,dest.rdfs:FalseLable)|0.5)", 0.5d));
		assertTrue(form.testPropertiesAreSet("AND(ADD(0.1*trigrams(src.rdfs:label,dest.rdfs:FalseLable)|0.5,0.9*trigrams(src.rdfs:label,dest.rdfs:FalseLable)|0.5)|0.6, trigrams(src.rdfs:label,dest.rdfs:FalseLable)|0.9)", 0.5d));
		
		assertFalse(form.testPropertiesAreSet("trigrams(src.rdfs:someOtherProp,dest.rdfs:FalseLable)", 0.5d));
		assertFalse(form.testPropertiesAreSet("trigrams(src.rdfs:FalseLable,dest.rdfs:FalseLable)", 0.5d));
		c = null;
	}
}