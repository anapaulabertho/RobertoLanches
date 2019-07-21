package com.bertho.roberto.roberto_lanches;

import com.bertho.roberto.roberto_lanches.entity.Hamburguer;
import com.bertho.roberto.roberto_lanches.entity.Ingredient;
import com.bertho.roberto.roberto_lanches.repository.HamburguerRepository;
import com.bertho.roberto.roberto_lanches.repository.IngredientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RobertoLanchesApplication implements CommandLineRunner {

	@Autowired
	HamburguerRepository hamburguerRepository;

	@Autowired
	IngredientsRepository ingredientsRepository;

	public static void main(String[] args) {
		SpringApplication.run(RobertoLanchesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		hamburguerRepository.save(new Hamburguer("X-BACON", 6.50));
		hamburguerRepository.save(new Hamburguer("X-BURGUER", 4.50));
		hamburguerRepository.save(new Hamburguer("X-EGG", 5.30));
		hamburguerRepository.save(new Hamburguer("X-EGG BACON", 7.30));

		ingredientsRepository.save(new Ingredient("ALFACE", 0.40));
		ingredientsRepository.save(new Ingredient("BACON", 2.00));
		ingredientsRepository.save(new Ingredient("HAMBURGUER DE CARNE", 3.00));
		ingredientsRepository.save(new Ingredient("OVO", 0.80));
		ingredientsRepository.save(new Ingredient("QUEIJO", 1.50));

	}
}
