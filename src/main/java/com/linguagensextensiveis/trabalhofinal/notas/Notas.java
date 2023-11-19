package com.linguagensextensiveis.trabalhofinal.notas;

import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

public class Notas {

	public XML get(Integer index) throws FileNotFoundException {
		return new XMLDocument(new File("notas/nota" + index + ".xml"));
	}

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
					JSONObject prod = ((JSONObject) det).getJSONObject("prod");
					prod.append("nNF", nota.getJSONObject("nfeProc").getJSONObject("NFe").getJSONObject("infNFe").getJSONObject("ide").getBigInteger("nNF"));
					prod.append("dEmi", nota.getJSONObject("nfeProc").getJSONObject("NFe").getJSONObject("infNFe").getJSONObject("ide").getString("dEmi"));
					prod.append("impostos", ((JSONObject) det).getJSONObject("imposto"));
					produtos.add(prod);
				} else if (det instanceof JSONArray) {
					((JSONArray) det).forEach(obj -> {
						JSONObject prod = ((JSONObject) obj).getJSONObject("prod");
						prod.append("nNF", nota.getJSONObject("nfeProc").getJSONObject("NFe").getJSONObject("infNFe").getJSONObject("ide").getBigInteger("nNF"));
						prod.append("dEmi", nota.getJSONObject("nfeProc").getJSONObject("NFe").getJSONObject("infNFe").getJSONObject("ide").getString("dEmi"));
						prod.append("impostos", ((JSONObject) obj).getJSONObject("imposto"));
						produtos.add(prod);
					});
				}
			});
		}
		return produtos;
	}

//	public Collection<JSONObject> getProdutosNfe(Integer index) {
//		Collection<JSONObject> produtos = new ArrayList<>();
//		JSONObject nota = parse(index);
//		Collection<JSONObject> produtosNota = new ArrayList<>();
//		produtosNota.add(nota.getJSONObject("nfeProc").getJSONObject("NFe").getJSONObject("infNFe"));
//		produtosNota.stream().map(produto -> produto.get("det")).forEach(det -> {
//			if (det instanceof JSONObject) {
//				produtos.add(((JSONObject) det).getJSONObject("prod"));
//			} else if (det instanceof JSONArray) {
//				((JSONArray) det).forEach(obj -> produtos.add(((JSONObject) obj).getJSONObject("prod")));
//			}
//		});
//
//		produtos.add(nota.getJSONObject("nfeProc").getJSONObject("NFe").getJSONObject("infNFe").getJSONObject("ide").getJSONObject("nNF"));
//		return produtos;
//	}

	public BigDecimal getValorTotalProdutos() {
		Collection<JSONObject> produtos = getProdutos();
		BigDecimal valorTotal = BigDecimal.ZERO;
		for (JSONObject produto : produtos) {
			valorTotal = valorTotal.add(produto.getBigDecimal("vProd"));
		}
		return valorTotal;
	}

	// número de produtos em todas as notas
	public Integer getNumeroProdutos() {
		Collection<JSONObject> produtos = getProdutos();
		return produtos.size();
	}

	// total de ISSQN retido
	public BigDecimal getISSQN() {
		Collection<JSONObject> notas = parseAll();
		BigDecimal issqn = BigDecimal.ZERO;
		for (JSONObject nota : notas) {
			issqn = issqn.add(nota.getJSONObject("nfeProc").getJSONObject("NFe").getJSONObject("infNFe").getJSONObject("total").getJSONObject("ICMSTot").getBigDecimal("vCOFINS"));
		}
		return issqn;
	}

	// total do ICMS
	public ICMS getICMS() {
		Collection<JSONObject> notas = parseAll();
		ICMS ICMStotal = new ICMS();
		for (JSONObject nota : notas) {
			ICMS icms = new ICMS();
			JSONObject total = nota.getJSONObject("nfeProc").getJSONObject("NFe").getJSONObject("infNFe").getJSONObject("total").getJSONObject("ICMSTot");
			icms.calculete(total);
			ICMStotal.COFINS = ICMStotal.COFINS.add(icms.COFINS);
			ICMStotal.BCST = ICMStotal.BCST.add(icms.BCST);
			ICMStotal.Prod = ICMStotal.Prod.add(icms.Prod);
			ICMStotal.Seg = ICMStotal.Seg.add(icms.Seg);
			ICMStotal.NF = ICMStotal.NF.add(icms.NF);
			ICMStotal.PIS = ICMStotal.PIS.add(icms.PIS);
			ICMStotal.BC = ICMStotal.BC.add(icms.BC);
			ICMStotal.ST = ICMStotal.ST.add(icms.ST);
			ICMStotal.ICMS = ICMStotal.ICMS.add(icms.ICMS);
			ICMStotal.II = ICMStotal.II.add(icms.II);
			ICMStotal.Desc = ICMStotal.Desc.add(icms.Desc);
			ICMStotal.Outro = ICMStotal.Outro.add(icms.Outro);
			ICMStotal.IPI = ICMStotal.IPI.add(icms.IPI);
			ICMStotal.Frete = ICMStotal.Frete.add(icms.Frete);
		}
		return ICMStotal;
	}

	// valor aproximado de tributos
	public BigDecimal getTributos() {
		BigDecimal tributos = BigDecimal.ZERO;
		ICMS icms = getICMS();
		tributos = tributos.add(icms.all());
		return tributos;
	}

//	total de frete dos produtos das notas
	public BigDecimal getFrete() {
		BigDecimal frete = BigDecimal.ZERO;
		ICMS icms = getICMS();
		frete = frete.add(icms.getFrete());
		return frete;
	}

//	detalhes do produto com menor preço
	public String getProdutoBarato() {
		JSONObject produtoBarato = null;
		Collection<JSONObject> produtos = getProdutos();
		for (JSONObject produto : produtos) {
			if (produtoBarato == null) {
				produtoBarato = produto;
			} else {
				if (produto.getBigDecimal("vProd").compareTo(produtoBarato.getBigDecimal("vProd")) < 0) {
					produtoBarato = produto;
				}
			}
		}
		String desc = produtoBarato.getString("xProd");
		return desc;
	}

//	detalhes da nota com maior imposto
	public String getNotaMaior() {
		JSONObject notaMaior = null;
		BigDecimal maiorTributo = BigDecimal.ZERO;
		Collection<JSONObject> notas = parseAll();
		for (JSONObject nota : notas) {
			if (notaMaior == null) {
				notaMaior = nota;
				ICMS icms = new ICMS();
				JSONObject tmp = nota.getJSONObject("nfeProc").getJSONObject("NFe").getJSONObject("infNFe").getJSONObject("total").getJSONObject("ICMSTot");
				icms.calculete(tmp);
				maiorTributo = icms.all();
			} else {
				JSONObject total = nota.getJSONObject("nfeProc").getJSONObject("NFe").getJSONObject("infNFe").getJSONObject("total").getJSONObject("ICMSTot");
				ICMS icms = new ICMS();
				icms.calculete(total);
				BigDecimal ICMSAtual = icms.all();
				if (ICMSAtual.compareTo(maiorTributo) > 0) {
					notaMaior = nota;
				}
			}
		}
		Integer number = notaMaior.getJSONObject("nfeProc").getJSONObject("NFe").getJSONObject("infNFe").getJSONObject("ide").getInt("nNF");
		return number.toString();
	}

	public Integer getNumeroNotas() {
		return parseAll().size();
	}



}
