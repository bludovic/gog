package com.nd.classes.parameters;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class JAXB {
	public JAXB() {
	}

	public static Parameters importParameters() {
		Parameters param = null;
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(Parameters.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			param = (Parameters) jaxbUnmarshaller.unmarshal(new File("c:/xml/parameters.xml"));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return param;
	}

}