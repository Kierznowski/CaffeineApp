package com.Caffeine.app;

import com.Caffeine.app.model.Ingredient;
import com.Caffeine.app.model.Ingredient.Type;
import com.Caffeine.app.repositories.IngredientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(IngredientRepository repo) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {

				repo.save(new Ingredient("ROBUST", "Robust", Type.BEAN));
				repo.save(new Ingredient("ARAB", "Arabic", Type.BEAN));
				repo.save(new Ingredient("MIX", "Arabic/Robust (60%/40%)", Type.BEAN));
				repo.save(new Ingredient("SUGAR", "Sugar", Type.SWEETENER));
				repo.save(new Ingredient("MAPLE", "Maple Syrup", Type.SWEETENER));
				repo.save(new Ingredient("ASP", "Aspartame", Type.SWEETENER));
				repo.save(new Ingredient("BSUG", "Brown sugar", Type.SWEETENER));
				repo.save(new Ingredient("HONEY", "Honey", Type.SWEETENER));
				repo.save(new Ingredient("MILK", "Milk", Type.MILK));
				repo.save(new Ingredient("OAT", "Oat Milk", Type.MILK));
				repo.save(new Ingredient("ALMOND", "Almond Milk", Type.MILK));
				repo.save(new Ingredient("CREAM", "Cream", Type.MILK));
				repo.save(new Ingredient("COCOA", "Chocolate", Type.EXTRA));
				repo.save(new Ingredient("WHIP", "Whipped cream", Type.EXTRA));
				repo.save(new Ingredient("CIN", "Cinnamon", Type.EXTRA));
				repo.save(new Ingredient("PUMP", "Pumpkin", Type.EXTRA));

			}
		};
	}
}
