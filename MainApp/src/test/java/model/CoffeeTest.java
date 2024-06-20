package model;

import com.Caffeine.app.model.Coffee;
import com.Caffeine.app.model.Ingredient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes=Coffee.class)
public class CoffeeTest {

    @Autowired
    Coffee coffee;

    @Test
    void shouldIncrementIngredientsByOne() {
        coffee = new Coffee();
        Ingredient ingredient = new Ingredient("Test", "Test", Ingredient.Type.BEAN, 10.0);
        int amount = coffee.getIngredients().size();
        coffee.addIngredient(ingredient);
        Assertions.assertEquals(1, coffee.getIngredients().size() - amount);
    }
}
