package ObjectOriented;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpaceOrderTest {

    @Test
    public void testDefaultConstructor () {
        SpaceOrder order = new SpaceOrder();

        assertAll("Variables and constants from default constructor",
                () -> assertEquals(false, order.isAccept()),
                () -> assertEquals(false, order.isSpecial()),
                () -> assertEquals(1024, order.getMAX_SIZE()),
                () -> assertEquals(16, order.getNORMAL_MIN_SIZE()),
                () -> assertEquals(0, order.getABSOLUTE_MIN_SIZE())
        );

    }

   // @ParameterizedTest
    public void testParameterizedConstructor (boolean special,  boolean specialResult,
                                              boolean acceptResult, int maxSizeResult) {
        SpaceOrder order = new SpaceOrder(special);

        assertAll("Variables and constants from default constructor",
                () -> assertEquals(acceptResult, order.isAccept()),
                () -> assertEquals(specialResult, order.isSpecial()),
                () -> assertEquals(maxSizeResult, order.getMAX_SIZE()),
                () -> assertEquals(16, order.getNORMAL_MIN_SIZE()),
                () -> assertEquals(0, order.getABSOLUTE_MIN_SIZE())
        );

    }


}
