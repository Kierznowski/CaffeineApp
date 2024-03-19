package com.Caffeine.app;

import com.Caffeine.app.model.Coffee;
import com.Caffeine.app.model.Ingredient;
import com.Caffeine.app.model.Ingredient.Type;
import com.Caffeine.app.repositories.CoffeeRepository;
import com.Caffeine.app.repositories.IngredientRepository;
import com.Caffeine.app.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;


@SpringBootApplication
@ComponentScan("com.Caffeine.app")
@EnableJpaRepositories("com.Caffeine.app/repositories")
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}


	//initializing fake data to database for testing
	@Bean
	public CommandLineRunner dataLoader(IngredientRepository repo,
										UserRepository userRepository,
										PasswordEncoder passwordEncoder,
										CoffeeRepository coffeeRepository) {

		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {

				Ingredient robust = new Ingredient("ROBUST", "Robust", Type.BEAN);
				repo.save(robust);
				Ingredient arabic = new Ingredient("ARAB", "Arabic", Type.BEAN);
				repo.save(arabic);
				Ingredient blend = new Ingredient("MIX","Arabic/Robust (60%/40%)", Type.BEAN);
				repo.save(blend);
				Ingredient sugar = new Ingredient("SUGAR", "Sugar", Type.SWEETENER);
				repo.save(sugar);
				Ingredient maple = new Ingredient("MAPLE", "Maple Syrup", Type.SWEETENER);
				repo.save(maple);
				Ingredient aspartame = new Ingredient("ASP", "Aspartame", Type.SWEETENER);
				repo.save(aspartame);
				Ingredient brownSugar = new Ingredient("BSUG", "Brown sugar", Type.SWEETENER);
				repo.save(brownSugar);
				Ingredient honey = new Ingredient("HONEY", "Honey", Type.SWEETENER);
				repo.save(honey);
				Ingredient milk = new Ingredient("MILK", "Milk", Type.MILK);
				repo.save(milk);
				Ingredient oatMilk = new Ingredient("OAT", "Oat Milk", Type.MILK);
				repo.save(oatMilk);
				Ingredient almondMilk = new Ingredient("ALMOND", "Almond Milk", Type.MILK);
				repo.save(almondMilk);
				Ingredient cream = new Ingredient("CREAM", "Cream", Type.MILK);
				repo.save(cream);
				Ingredient cocoa = new Ingredient("COCOA", "Chocolate", Type.EXTRA);
				repo.save(cocoa);
				Ingredient whippedCream = new Ingredient("WHIP", "Whipped cream", Type.EXTRA);
				repo.save(whippedCream);
				Ingredient cinnamon = new Ingredient("CIN", "Cinnamon", Type.EXTRA);
				repo.save(cinnamon);
				Ingredient pumpkin = new Ingredient("PUMP", "Pumpkin", Type.EXTRA);
				repo.save(pumpkin);

				Coffee coffee1 = new Coffee();
				coffee1.setName("Dark robust");
				coffee1.setIngredients(Arrays.asList(robust, sugar));
				coffeeRepository.save(coffee1);
				Coffee coffee2 = new Coffee();
				coffee2.setName("Pumpkin latte");
				coffee2.setIngredients(Arrays.asList(arabic, milk, cinnamon, sugar, pumpkin));
				coffeeRepository.save(coffee2);
				Coffee coffee3 = new Coffee();
				coffee3.setName("Cream dream");
				coffee3.setIngredients(Arrays.asList(arabic, cream, whippedCream, sugar, cocoa));
				coffeeRepository.save(coffee3);


			}
		};

	}

}
