package com.linguagensextensiveis.trabalhofinal.notas;

import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import org.json.JSONObject;

import java.io.File;

public class Notas {

	public static String parse(Integer index) throws Exception {
		System.out.println("----------- Parsing nota" + index + ".xml");
		XML xml = new XMLDocument(new File("notas/nota" + index + ".xml"));
		String string = xml.toString();
		JSONObject json = org.json.XML.toJSONObject(string);
		return json.toString(4);
	}

}
