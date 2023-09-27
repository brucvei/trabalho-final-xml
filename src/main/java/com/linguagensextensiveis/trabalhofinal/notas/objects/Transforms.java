package com.linguagensextensiveis.trabalhofinal.notas.objects;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.Collection;

public class Transforms {
	@JacksonXmlProperty(localName = "Algorithm", isAttribute = true)
	public Collection<String> transform;

	public Collection<String> getTransform() {
		return transform;
	}

	public void setTransform(Collection<String> transform) {
		this.transform = transform;
	}
}