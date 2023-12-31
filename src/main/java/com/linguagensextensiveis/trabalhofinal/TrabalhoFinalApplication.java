package com.linguagensextensiveis.trabalhofinal;

import com.linguagensextensiveis.trabalhofinal.notas.ICMS;
import com.linguagensextensiveis.trabalhofinal.notas.Notas;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Collection;

@SpringBootApplication
@RestController
public class TrabalhoFinalApplication {

	private final Notas notas = new Notas();

	public static void main(String[] args) {
		SpringApplication.run(TrabalhoFinalApplication.class, args);
	}

	@GetMapping("/")
	public String index() {
		System.out.println("Hello World!");
		return "Processando JSON com Java!";
	}

	@GetMapping("/nota/all")
	public String getAllNFe() throws Exception {
		Collection<JSONObject> nfe = notas.parseAll();
		if (nfe == null) {
			throw new Exception("Nota não encontrada");
		}
		System.out.println(nfe);
		return nfe.toString();
	}


	@GetMapping("/nota/{index}")
	public String getNFe(@PathVariable Integer index) throws Exception {
		JSONObject nfe = notas.parse(index);
		if (nfe == null) {
			throw new Exception("Nota não encontrada");
		}
		return nfe.toString();
	}

	@GetMapping("/nota/quantidade")
	public Integer getNumeroNotas() throws Exception {
		Integer produtos = notas.getNumeroNotas();
		if (produtos == null) {
			throw new Exception("Produtos não encontrados");
		}
		return produtos;
	}

//	@GetMapping("/nota/{index}/produto/quantidade")
//	public String getProdutosNfe(@PathVariable Integer index) throws Exception {
//		JSONObject produtos = notas.getProdutosNfe(index);
//		if (produtos == null) {
//			throw new Exception("Nota não encontrada");
//		}
//		return produtos.toString();
//	}

	@GetMapping("/produtos")
	public String getProdutos() throws Exception {
		Collection<JSONObject> produtos = notas.getProdutos();
		if (produtos == null) {
			throw new Exception("Produtos não encontrados");
		}
		return produtos.toString();
	}

	@GetMapping("/produtos/valor-total")
	public BigDecimal getValorTotalProdutos() throws Exception {
		BigDecimal produtos = notas.getValorTotalProdutos();
		if (produtos == null) {
			throw new Exception("Produtos não encontrados");
		}
		return produtos;
	}

	@GetMapping("/produtos/quantidade")
	public Integer getNumeroProdutos() throws Exception {
		Integer produtos = notas.getNumeroProdutos();
		if (produtos == null) {
			throw new Exception("Produtos não encontrados");
		}
		return produtos;
	}

	@GetMapping("/issqn")
	public BigDecimal getISSQN() throws Exception {
		BigDecimal issqn = notas.getISSQN();
		if (issqn == null) {
			throw new Exception("ISSQN não encontrado");
		}
		return issqn;
	}

	@GetMapping("/icms")
	public ICMS getICMS() throws Exception {
		ICMS icms = notas.getICMS();
		if (icms == null) {
			throw new Exception("ICMS não encontrado");
		}
		return icms;
	}

	@GetMapping("/tributos")
	public BigDecimal getTributos() throws Exception {
		BigDecimal tributos = notas.getTributos();
		if (tributos == null) {
			throw new Exception("Tributos não encontrados");
		}
		return tributos;
	}

	@GetMapping("/frete")
	public BigDecimal getFrete() throws Exception{
		BigDecimal frete = notas.getFrete();
		if (frete == null) {
			throw new Exception("Frete não encontrado");
		}
		return frete;
	}

	@GetMapping("/produtos/barato")
	public String getProdutoBarato() throws Exception{
		String menorPreco = notas.getProdutoBarato();
		if (menorPreco == null) {
			throw new Exception("Produto de menor preço não encontrado");
		}
		return menorPreco;
	}

	@GetMapping("/nota/maior")
	public String getNotaMaior() throws Exception{
		String maiorNota = notas.getNotaMaior();
		if (maiorNota == null) {
			throw new Exception("Nota com maior tributacao não encontrada");
		}
		return maiorNota;
	}
}
