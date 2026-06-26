package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class IngredientTypeTest {


    private static Stream<Arguments> ingredientTypeProvider() {
        return Stream.of(
                Arguments.of("SAUCE"),
                Arguments.of("FILLING")
        );
    }

    @ParameterizedTest
    @MethodSource("ingredientTypeProvider")
    @DisplayName("Проверка, что enum содержит ожидаемое значение")
    public void enumContainsExpectedValue(String typeName) {
        assertNotNull(IngredientType.valueOf(typeName), "Значение " + typeName + " должно присутствовать в enum IngredientType");
    }

    @ParameterizedTest
    @MethodSource("ingredientTypeProvider")
    @DisplayName("Проверка, что valueOf возвращает корректный тип ингредиента")
    public void valueOfReturnsCorrectType(String typeName) {
        IngredientType result = IngredientType.valueOf(typeName);
        assertEquals(typeName, result.name(), "Проверка, что name() значения enum совпадает с переданным именем " + typeName);
    }
}
