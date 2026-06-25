import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import praktikum.Bun;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTest {

    private static Stream<Arguments> bunProvider() {
        return Stream.of(
                Arguments.of("Small bun", 100.5f),
                Arguments.of("Average bun", 200f),
                Arguments.of("Big bun", 300.78f)
        );
    }

    @ParameterizedTest
    @MethodSource("bunProvider")
    @DisplayName("getName возвращает корректное название булочки")
    public void getNameReturnsCorrectName(String name, float price) {
        Bun bun = new Bun(name, price);
        assertEquals(name, bun.getName());
    }

    @ParameterizedTest
    @MethodSource("bunProvider")
    @DisplayName("getPrice возвращает корректную цену булочки")
    public void getPriceReturnsCorrectPrice(String name, float price) {
        Bun bun = new Bun(name, price);
        assertEquals(price, bun.getPrice(), 0.001);
    }
}
