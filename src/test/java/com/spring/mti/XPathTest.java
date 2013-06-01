package com.spring.mti;

import static org.junit.Assert.*;

import java.io.StringReader;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.NodeList;

import org.junit.Test;
import org.xml.sax.InputSource;

public class XPathTest {

	private static String fixture="<?xml version=\"1.0\" encoding=\"UTF-8\"?><root>" +
			"<hash>dcd95bcb84b09897b2b66d4684c040da</hash><answer q=\"883\" c=\"true\" a=\"884\" />\"" +
			"<answer q=\"883\" c=\"false\" a=\"885\" />\"<answer q=\"883\" c=\"true\" a=\"886\" />\"" +
			"<answer q=\"883\" c=\"true\" a=\"887\" />\"<answer q=\"883\" c=\"false\" a=\"888\" />\"" +
			"<answer q=\"877\" c=\"true\" a=\"878\" />\"<answer q=\"877\" c=\"true\" a=\"879\" />\"" +
			"<answer q=\"877\" c=\"false\" a=\"880\" />\"<answer q=\"877\" c=\"false\" a=\"881\" />\"" +
			"<answer q=\"877\" c=\"false\" a=\"882\" />\"<answer q=\"872\" c=\"true\" a=\"915\" />\"</root>";
	
	@Test
	public void testXPath() {
		InputSource source = new InputSource(new StringReader(fixture));
        XPath xPath = XPathFactory.newInstance().newXPath();
        try {
			NodeList nodes = (NodeList) xPath.evaluate("//root/answer/@q", source, XPathConstants.NODESET); 
        	assertTrue(nodes.getLength() > -1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
