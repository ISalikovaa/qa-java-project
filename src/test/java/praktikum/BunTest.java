package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTest {

    private static Stream<Arguments> bunProvider() {
        return Stream.of(
                Arguments.of("black bun", 100f),
                Arguments.of("white bun", 200f),
                Arguments.of("red bun", 300f)
        );
    }

    @ParameterizedTest
    @MethodSource("bunProvider")
    @DisplayName("getName возвращает корректное название булочки")
    public void getNameReturnsCorrectName(String name, float price) {
        Bun bun = new Bun(name, price);
        assertEquals(name, bun.getName(), "Проверка, что название булочки совпадает  с переданным");
    }

    @ParameterizedTest
    @MethodSource("bunProvider")
    @DisplayName("getPrice возвращает корректную цену булочки")
    public void getPriceReturnsCorrectPrice(String name, float price) {
        Bun bun = new Bun(name, price);
        assertEquals(price, bun.getPrice(), 0.001, "Проверка, что цена булочки совпадает с переданной");
    }
}