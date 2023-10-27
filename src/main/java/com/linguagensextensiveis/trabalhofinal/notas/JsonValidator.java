package com.linguagensextensiveis.trabalhofinal.notas;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

import java.io.InputStream;
import java.util.Set;

public class JsonValidator {

	public static void main(String[] args) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V201909);

		try (
				InputStream jsonStream = inputStreamFromClasspath("notas/tmp.json");
				InputStream schemaStream = inputStreamFromClasspath("notas/schema.json")
		) {
			JsonNode json = objectMapper.readTree(jsonStream);
			JsonSchema schema = schemaFactory.getSchema(schemaStream);

			Set<ValidationMessage> validationResult = schema.validate(json);
			if (validationResult.isEmpty()) {
				System.out.println("There is no validation errors");
			} else {
				validationResult.forEach(vm -> System.out.println(vm.getMessage()));
			}
		}
	}

	private static InputStream inputStreamFromClasspath(String path) {
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
	}
}