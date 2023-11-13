package com.linguagensextensiveis.trabalhofinal.notas;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import org.everit.json.schema.ValidationException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

public class Notas {

	// get nota by index
	public JSONObject parse(Integer index) {
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
			File jsonFile = new File("notas/schema.json");
			String stringFile = jsonFile.toString();
			System.out.println("Reading schema file...");
			JSONObject jsonSchema = org.json.XML.toJSONObject(stringFile);
			Schema schema = SchemaLoader.load(jsonSchema);
			try {
				schema.validate(json);
			} catch (ValidationException e) {
				System.out.println("schema validation failed!");
				e.printStackTrace();
			}
			System.out.println("schema validated successfully!");
			return json;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
//			throw e;
		}
		return null;
	}

	// get all notas
	public Collection<JSONObject> parseAll() {
		Collection<JSONObject> notas = new ArrayList<>();
		for (int i = 1; i <= 6; i++) {
			notas.add(parse(i));
		}
		return notas;
	}

	// todos os produtos de todas as notas
	public Collection<JSONObject> getProdutos() {
		Collection<JSONObject> notas = parseAll();
		Collection<JSONObject> produtos = new ArrayList<>();
		for (JSONObject nota : notas) {
			Collection<JSONObject> produtosNota = new ArrayList<>();
			produtosNota.add(nota.getJSONObject("nfeProc").getJSONObject("NFe").getJSONObject("infNFe"));
			produtosNota.stream().map(produto -> produto.get("det")).forEach(det -> {
				if (det instanceof JSONObject) {
					produtos.add(((JSONObject) det).getJSONObject("prod"));
				} else if (det instanceof JSONArray) {
					((JSONArray) det).forEach(obj -> produtos.add(((JSONObject) obj).getJSONObject("prod")));
				}
			});
		}
		return produtos;
	}

	// número de produtos em todas as notas
	public Integer getNumeroProdutos() {
		Collection<JSONObject> produtos = getProdutos();
		return produtos.size();
	}

	// total de ISSQN retido
	public BigDecimal getISSQN() {

		return BigDecimal.ZERO;
	}

	// total do ICMS
	public BigDecimal getICMS() {
		return BigDecimal.ZERO;
	}

	// valor aproximado de tributos
	public BigDecimal getTributos() {
		return BigDecimal.ZERO;
	}

//	total de frete dos produtos das notas
	public BigDecimal getFrete() {
		return BigDecimal.ZERO;
	}

//	detalhes do produto com menor preço
	public String getProdutoBarato() {
		return null;
	}

//	detalhes da nota com maior imposto
	public JSONObject getNotaMaior() {
		return null;
	}
}
