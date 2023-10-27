package com.linguagensextensiveis.trabalhofinal;

import com.linguagensextensiveis.trabalhofinal.notas.Notas;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TrabalhoFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrabalhoFinalApplication.class, args);
	}

	@GetMapping("/nota/{index}")
	public String hello(@PathVariable Integer index) throws Exception {
		return Notas.parse(index);
	}
}
