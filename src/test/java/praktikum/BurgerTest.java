package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredientFirst;

    @Mock
    private Ingredient ingredientSecond;

    @BeforeEach
    public void setUp() {
        burger = new Burger();
    }

    @Test
    @DisplayName("Проверка, что setBuns корректно устанавливает булочку")
    public void setBunsSetsBunCorrectly() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun, "Проверка, что при выполнении setBuns поле bun содержит переданную булочку");
    }

    @Test
    @DisplayName("Проверка, что addIngredient добавляет ингредиент в список")
    public void addIngredientAddsToList() {
        burger.addIngredient(ingredientFirst);
        assertTrue(burger.ingredients.contains(ingredientFirst), "Ингредиент должен присутствовать в списке после добавления");
        assertEquals(1, burger.ingredients.size(), "Размер списка ингредиентов должен быть равен 1 после добавления одного ингредиента");
    }

    @Test
    @DisplayName("Проверка, что removeIngredient удаляет ингредиент из списка")
    public void removeIngredientRemovesFromList() {
        burger.addIngredient(ingredientFirst);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty(), "Список ингредиентов должен быть пуст после удаления единственного ингредиента");
    }

    @Test
    @DisplayName("Проверка, что moveIngredient перемещает ингредиент на новую позицию")
    public void moveIngredientChangesPosition() {
        burger.addIngredient(ingredientFirst);
        burger.addIngredient(ingredientSecond);
        burger.moveIngredient(0, 1);
        assertEquals(ingredientSecond, burger.ingredients.get(0), "После перемещения на позиции 0 должен быть второй ингредиент");
        assertEquals(ingredientFirst, burger.ingredients.get(1), "После перемещения на позиции 1 должен быть первый ингредиент");
    }

    @Test
    @DisplayName("Проверка, что getPrice корректно рассчитывает стоимость бургера")
    public void getPriceCalculatesCorrectly() {
        burger.setBuns(bun);
        burger.addIngredient(ingredientFirst);
        Mockito.when(bun.getPrice()).thenReturn(100f);
        Mockito.when(ingredientFirst.getPrice()).thenReturn(200f);
        float expected = 100f * 2 + 200f;
        assertEquals(expected, burger.getPrice(), 0.001, "Цена бургера должна быть равна удвоенной цене булочки плюс цена всех ингредиентов");
    }

    @Test
    @DisplayName("Проверка, что getReceipt возвращает чек с корректным форматом")
    public void getReceiptReturnsCorrectFormat() {
        burger.setBuns(bun);
        burger.addIngredient(ingredientFirst);
        Mockito.when(bun.getName()).thenReturn("test bun");
        Mockito.when(bun.getPrice()).thenReturn(100f);
        Mockito.when(ingredientFirst.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredientFirst.getName()).thenReturn("hot sauce");
        Mockito.when(ingredientFirst.getPrice()).thenReturn(100f);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("(==== test bun ====)"), "Чек должен содержать строку с названием булочки");
        assertTrue(receipt.contains("= sauce hot sauce ="), "Чек должен содержать строку с типом и названием ингредиента");
        assertTrue(receipt.contains("Price:"), "Чек должен содержать строку со стоимостью");

        verify(bun, Mockito.times(2)).getName();
        verify(ingredientFirst).getType();
        verify(ingredientFirst).getName();
    }
}
