package com.linguagensextensiveis.trabalhofinal.notas.objects;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Reference {

	@JacksonXmlProperty(localName = "URI", isAttribute = true)
	public String URI;

	public Transforms transforms;

	@JacksonXmlProperty(localName = "Algorithm", isAttribute = true)
	public String digestMethod;

	public String digestValue;

}
