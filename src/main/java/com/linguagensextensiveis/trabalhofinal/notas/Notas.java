package com.linguagensextensiveis.trabalhofinal.notas;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Notas {

	public static String parse(Integer index) throws Exception {
		XML xml = new XMLDocument(new File("notas/nota" + index + ".xml"));
		String string = xml.toString();
		JSONObject json = org.json.XML.toJSONObject(string);
		try {
			System.out.println("Writing to tmp file...");
//			ObjectMapper mapper = new ObjectMapper();
//			mapper.writeValue(new File("notas/tmp.json"), string);
			PrintWriter out = new PrintWriter(new FileWriter("notas/tmp.json"));
			out.write(json.toString(4));
			out.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
//		System.out.println(json.toString(4));
		System.out.println("Done!");

		return json.toString(4);
	}

}
