package com.Caffeine.app;

import com.Caffeine.app.model.Coffee;
import com.Caffeine.app.model.Ingredient;
import com.Caffeine.app.model.Ingredient.Type;
import com.Caffeine.app.model.User;
import com.Caffeine.app.repositories.CoffeeRepository;
import com.Caffeine.app.repositories.IngredientRepository;
import com.Caffeine.app.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

				Ingredient robust = new Ingredient("ROBUST", "Robust", Type.BEAN, 0.00);
				repo.save(robust);
				Ingredient arabic = new Ingredient("ARAB", "Arabic", Type.BEAN, 0.00);
				repo.save(arabic);
				Ingredient blend = new Ingredient("MIX","Arabic/Robust (60%/40%)", Type.BEAN, 0.00);
				repo.save(blend);
				Ingredient sugar = new Ingredient("SUGAR", "Sugar", Type.SWEETENER, 0.00);
				repo.save(sugar);
				Ingredient maple = new Ingredient("MAPLE", "Maple Syrup", Type.SWEETENER, 0.25);
				repo.save(maple);
				Ingredient aspartame = new Ingredient("ASP", "Aspartame", Type.SWEETENER, 0.10);
				repo.save(aspartame);
				Ingredient brownSugar = new Ingredient("BSUG", "Brown sugar", Type.SWEETENER, 0.10);
				repo.save(brownSugar);
				Ingredient honey = new Ingredient("HONEY", "Honey", Type.SWEETENER, 0.25);
				repo.save(honey);
				Ingredient milk = new Ingredient("MILK", "Milk", Type.MILK, 0.1);
				repo.save(milk);
				Ingredient oatMilk = new Ingredient("OAT", "Oat Milk", Type.MILK, 0.1);
				repo.save(oatMilk);
				Ingredient almondMilk = new Ingredient("ALMOND", "Almond Milk", Type.MILK, 0.1);
				repo.save(almondMilk);
				Ingredient cream = new Ingredient("CREAM", "Cream", Type.MILK, 0.15);
				repo.save(cream);
				Ingredient cocoa = new Ingredient("COCOA", "Chocolate", Type.EXTRA, 0.25);
				repo.save(cocoa);
				Ingredient whippedCream = new Ingredient("WHIP", "Whipped cream", Type.EXTRA, 0.25);
				repo.save(whippedCream);
				Ingredient cinnamon = new Ingredient("CIN", "Cinnamon", Type.EXTRA, 0.1);
				repo.save(cinnamon);
				Ingredient pumpkin = new Ingredient("PUMP", "Pumpkin", Type.EXTRA, 0.5);
				repo.save(pumpkin);
				Ingredient smallCoffee = new Ingredient("SMALL", "Small (200 ml)", Type.VOLUME, 3.00);
				repo.save(smallCoffee);
				Ingredient largeCoffee = new Ingredient("LARGE", "Large (350 ml)", Type.VOLUME, 3.50);
				repo.save(largeCoffee);

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

				//Test User - just for testing purpose
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				User testUser = new User("test", bc.encode("test"), "TestUser", "testStreet",
						"testCity", "00-000", "000000000");
				userRepository.save(testUser);
			}
		};

	}

}
