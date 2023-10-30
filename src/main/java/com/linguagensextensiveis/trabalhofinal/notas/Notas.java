package com.linguagensextensiveis.trabalhofinal.notas;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import org.hamcrest.Matcher;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Set;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Notas {

	public static String parse(Integer index) {
		try {
			// Get XML from file and transform to JSON
			XML xml = new XMLDocument(new File("notas/nota" + index + ".xml"));
			String string = xml.toString();
			JSONObject json = org.json.XML.toJSONObject(string);
			System.out.println("Writing to json file...");
			PrintWriter out = new PrintWriter(new FileWriter("notas/tmp.json"));
			out.write(json.toString(4));
			out.close();
			System.out.println("Done!");

			// Validate JSON
			JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder()
					.setValidationConfiguration(
							ValidationConfiguration.newBuilder()
									.setDefaultVersion(SchemaVersion.DRAFTV4).freeze())
					.freeze();
			Matcher<?> using = matchesJsonSchemaInClasspath("schema.json").using(jsonSchemaFactory);

			return json.toString(4);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			return e.getMessage();
//			throw new Exception("Error: " + e.getMessage());
		}
	}

//
//	public static void validate(String index) throws Exception {
//		try {
//			ObjectMapper objectMapper = new ObjectMapper();
//			JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V201909);
//
//			System.out.println("Validating nota" + index + ".json...");
//			InputStream jsonStream = inputStreamFromClasspath("notas/nota" + index + ".json");
//			InputStream schemaStream = inputStreamFromClasspath("notas/schema.json");
//
//			System.out.println("jsonStream: " + jsonStream);
//			System.out.println("schemaStream: " + schemaStream);
//
//			JsonNode json = objectMapper.readTree(jsonStream);
//			JsonSchema schema = schemaFactory.getSchema(schemaStream);
//
//			Set<ValidationMessage> validationResult = schema.validate(json);
//			if (validationResult.isEmpty()) {
//				System.out.println("There is no validation errors");
//			}
//		} catch (Exception e) {
//			throw new Exception("Error: " + e.getMessage());
//		}
//	}
//
//	private static InputStream inputStreamFromClasspath(String path) {
//		return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
//	}
}
