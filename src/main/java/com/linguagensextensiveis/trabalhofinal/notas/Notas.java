package com.linguagensextensiveis.trabalhofinal.notas;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import org.everit.json.schema.ValidationException;
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
	public JSONObject getProdutos() {
		Collection<JSONObject> notas = parseAll();
		JSONObject produtos = new JSONObject();
		for (JSONObject nota : notas) {
			System.out.println(nota);
			JSONObject produtosNota = nota.getJSONObject("nfeProc").getJSONObject("NFe").getJSONObject("infNFe")
					.getJSONObject("det").getJSONObject("prod");
//			System.out.println(produtosNota);
			Set<String> keys = produtosNota.keySet();
			for (String key : keys) {
				JSONObject produto = produtosNota.getJSONObject(key);
				String codigo = produto.getString("cProd");
				String descricao = produto.getString("xProd");
				String preco = produto.getString("vProd");
				produtos.put(codigo, descricao + " - R$" + preco);
			}
		}
		return produtos;
	}

	// número de produtos em todas as notas
	public Integer getNumeroProdutos() {
		JSONObject produtos = getProdutos();
		return produtos.length();
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
