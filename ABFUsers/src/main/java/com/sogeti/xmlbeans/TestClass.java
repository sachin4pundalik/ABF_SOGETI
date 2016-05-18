package com.sogeti.xmlbeans;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class TestClass {

	public static void main(String[] args) {
		Resource r1 = new Resource();
		
		r1.setId(1);
		r1.setMonths(new Months());
		
		try {
	        JAXBContext ctx = JAXBContext.newInstance(Resource.class);
	        Marshaller marshaller = ctx.createMarshaller();
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	        marshaller.marshal(r1, System.out);
	    }
	    catch (Exception e) {

	              //catch exception 
	    }

	}

}
