package model;

import com.Caffeine.app.model.Coffee;
import com.Caffeine.app.model.CoffeeOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes=CoffeeOrder.class)
public class CoffeeOrderTest {

    @Autowired
    CoffeeOrder order;

    @Test
    void addExactlyOneCoffee() {
        Coffee coffee = new Coffee();
        int amount = order.getCoffees().size();
        order.addCoffee(coffee);
        Assertions.assertEquals(1, order.getCoffees().size() - amount);
    }

    @Test
    void deleteExactlyOneCoffee() {
        Coffee coffee = new Coffee();
        Coffee coffee1 = new Coffee();
        order.addCoffee(coffee);
        order.addCoffee(coffee1);
        int amount = order.getCoffees().size();
        order.deleteCoffee(coffee);
        Assertions.assertEquals(1, amount - order.getCoffees().size());
    }

}
