import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTest {


    private static Stream<Arguments> ingredientProvider() {
        return Stream.of(
                Arguments.of(IngredientType.SAUCE, "hot sauce", 100f),
                Arguments.of(IngredientType.FILLING, "cutlet", 200f)
        );
    }

    @ParameterizedTest
    @MethodSource("ingredientProvider")
    @DisplayName("Проверка, что getPrice возвращает корректную цену ингредиента")
    public void getPriceReturnsCorrectPrice(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(price, ingredient.getPrice(), 0.001, "Проверка, что цена ингредиента совпадает с переданной");
    }

    @ParameterizedTest
    @MethodSource("ingredientProvider")
    @DisplayName("Проверка, что getName возвращает корректное название ингредиента")
    public void getNameReturnsCorrectName(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(name, ingredient.getName(), "Проверка, что название ингредиента совпадает с переданным");
    }

    @ParameterizedTest
    @MethodSource("ingredientProvider")
    @DisplayName("Проверка, что getType возвращает корректный тип ингредиента")
    public void getTypeReturnsCorrectType(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(type, ingredient.getType(), "Проверка, что тип ингредиента совпадает с переданным");
    }
}
