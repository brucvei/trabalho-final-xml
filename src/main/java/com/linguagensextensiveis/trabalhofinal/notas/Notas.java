package com.linguagensextensiveis.trabalhofinal.notas;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.linguagensextensiveis.trabalhofinal.notas.objects.Xml;

import java.io.File;

public class Notas {

	public static String parse(Integer index) throws Exception {
		System.out.println("----------- Parsing nota" + index + ".xml");
		File xml = new File("notas/nota" + index + ".xml");
		System.out.println(xml.getAbsolutePath());
		System.out.println(xml.exists());
		System.out.println(xml.isFile());
		XmlMapper xmlMapper = new XmlMapper();
		System.out.println("-----------");
		Xml tmp = xmlMapper.readValue(xml, Xml.class);
		System.out.println(tmp);
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("tudo certo!");
		return mapper.writeValueAsString(tmp);
	}

}
