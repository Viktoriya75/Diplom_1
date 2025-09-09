package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static praktikum.TestConstants.*;

public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient ingredientMock1;

    @Mock
    private Ingredient ingredientMock2;

    @Mock
    private Ingredient ingredientMock3;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    // ====== ADD INGREDIENT TESTS ======

    @Test
    public void testAddIngredient() {
        burger.addIngredient(ingredientMock1);
        assertEquals(SIZE_ONE, burger.ingredients.size());
        assertEquals(ingredientMock1, burger.ingredients.get(INDEX_ZERO));
    }

    @Test
    public void testAddMultipleIngredients() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.addIngredient(ingredientMock3);

        assertEquals(SIZE_THREE, burger.ingredients.size());
        assertEquals(ingredientMock1, burger.ingredients.get(INDEX_ZERO));
        assertEquals(ingredientMock2, burger.ingredients.get(INDEX_ONE));
        assertEquals(ingredientMock3, burger.ingredients.get(INDEX_TWO));
    }

    @Test
    public void testAddIngredientsInSequence() {
        assertEquals(SIZE_ZERO, burger.ingredients.size());

        burger.addIngredient(ingredientMock1);
        assertEquals(SIZE_ONE, burger.ingredients.size());

        burger.addIngredient(ingredientMock2);
        assertEquals(SIZE_TWO, burger.ingredients.size());

        burger.addIngredient(ingredientMock3);
        assertEquals(SIZE_THREE, burger.ingredients.size());
    }

    @Test
    public void testInitialIngredientsState() {
        assertNotNull(burger.ingredients);
        assertEquals(SIZE_ZERO, burger.ingredients.size());
    }

    // ====== REMOVE INGREDIENT TESTS ======

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        burger.removeIngredient(INDEX_ZERO);

        assertEquals(SIZE_ONE, burger.ingredients.size());
        assertEquals(ingredientMock2, burger.ingredients.get(INDEX_ZERO));
    }

    @Test
    public void testRemoveIngredientFromMiddle() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.addIngredient(ingredientMock3);

        burger.removeIngredient(INDEX_ONE);

        assertEquals(SIZE_TWO, burger.ingredients.size());
        assertEquals(ingredientMock1, burger.ingredients.get(INDEX_ZERO));
        assertEquals(ingredientMock3, burger.ingredients.get(INDEX_ONE));
    }

    @Test
    public void testRemoveLastIngredient() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        burger.removeIngredient(INDEX_ONE);

        assertEquals(SIZE_ONE, burger.ingredients.size());
        assertEquals(ingredientMock1, burger.ingredients.get(INDEX_ZERO));
    }

    @Test
    public void testRemoveSingleIngredient() {
        burger.addIngredient(ingredientMock1);

        burger.removeIngredient(INDEX_ZERO);

        assertEquals(SIZE_ZERO, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredientsSequentially() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.addIngredient(ingredientMock3);
        assertEquals(SIZE_THREE, burger.ingredients.size());

        burger.removeIngredient(INDEX_ZERO);
        assertEquals(SIZE_TWO, burger.ingredients.size());
        assertEquals(ingredientMock2, burger.ingredients.get(INDEX_ZERO));

        burger.removeIngredient(INDEX_ZERO);
        assertEquals(SIZE_ONE, burger.ingredients.size());
        assertEquals(ingredientMock3, burger.ingredients.get(INDEX_ZERO));

        burger.removeIngredient(INDEX_ZERO);
        assertEquals(SIZE_ZERO, burger.ingredients.size());
    }

    // ====== MOVE INGREDIENT TESTS ======

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.addIngredient(ingredientMock3);

        burger.moveIngredient(INDEX_ZERO, INDEX_TWO);

        assertEquals(SIZE_THREE, burger.ingredients.size());
        assertEquals(ingredientMock2, burger.ingredients.get(INDEX_ZERO));
        assertEquals(ingredientMock3, burger.ingredients.get(INDEX_ONE));
        assertEquals(ingredientMock1, burger.ingredients.get(INDEX_TWO));
    }

    @Test
    public void testMoveIngredientToBeginning() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.addIngredient(ingredientMock3);

        burger.moveIngredient(INDEX_TWO, INDEX_ZERO);

        assertEquals(ingredientMock3, burger.ingredients.get(INDEX_ZERO));
        assertEquals(ingredientMock1, burger.ingredients.get(INDEX_ONE));
        assertEquals(ingredientMock2, burger.ingredients.get(INDEX_TWO));
    }

    @Test
    public void testMoveIngredientFromMiddle() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.addIngredient(ingredientMock3);

        burger.moveIngredient(INDEX_ONE, INDEX_TWO);

        assertEquals(ingredientMock1, burger.ingredients.get(INDEX_ZERO));
        assertEquals(ingredientMock3, burger.ingredients.get(INDEX_ONE));
        assertEquals(ingredientMock2, burger.ingredients.get(INDEX_TWO));
    }

    @Test
    public void testMoveIngredientToMiddle() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.addIngredient(ingredientMock3);

        burger.moveIngredient(INDEX_ZERO, INDEX_ONE);

        assertEquals(ingredientMock2, burger.ingredients.get(INDEX_ZERO));
        assertEquals(ingredientMock1, burger.ingredients.get(INDEX_ONE));
        assertEquals(ingredientMock3, burger.ingredients.get(INDEX_TWO));
    }

    @Test
    public void testMoveIngredientInTwoElementList() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        burger.moveIngredient(INDEX_ZERO, INDEX_ONE);

        assertEquals(SIZE_TWO, burger.ingredients.size());
        assertEquals(ingredientMock2, burger.ingredients.get(INDEX_ZERO));
        assertEquals(ingredientMock1, burger.ingredients.get(INDEX_ONE));
    }

    // ====== GET PRICE TESTS ======

    @Test
    public void testGetPriceOnlyBun() {
        when(bunMock.getPrice()).thenReturn(BUN_PRICE_100);
        burger.setBuns(bunMock);

        assertEquals(EXPECTED_PRICE_200, burger.getPrice(), DELTA);
    }

    @Test
    public void testGetPriceWithOneIngredient() {
        when(bunMock.getPrice()).thenReturn(BUN_PRICE_50);
        when(ingredientMock1.getPrice()).thenReturn(INGREDIENT_PRICE_25);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);

        assertEquals(EXPECTED_PRICE_125, burger.getPrice(), DELTA);
    }

    @Test
    public void testGetPriceWithMultipleIngredients() {
        when(bunMock.getPrice()).thenReturn(BUN_PRICE_100);
        when(ingredientMock1.getPrice()).thenReturn(INGREDIENT_PRICE_50);
        when(ingredientMock2.getPrice()).thenReturn(INGREDIENT_PRICE_30);
        when(ingredientMock3.getPrice()).thenReturn(INGREDIENT_PRICE_20);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.addIngredient(ingredientMock3);

        assertEquals(EXPECTED_PRICE_300, burger.getPrice(), DELTA);
    }

    @Test
    public void testGetPriceWithZeroPrices() {
        when(bunMock.getPrice()).thenReturn(BUN_PRICE_ZERO);
        when(ingredientMock1.getPrice()).thenReturn(INGREDIENT_PRICE_ZERO);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);

        assertEquals(EXPECTED_PRICE_ZERO, burger.getPrice(), DELTA);
    }

    @Test
    public void testGetPriceWithFloatPrices() {
        when(bunMock.getPrice()).thenReturn(BUN_PRICE_100_5);
        when(ingredientMock1.getPrice()).thenReturn(INGREDIENT_PRICE_1_5);
        when(ingredientMock2.getPrice()).thenReturn(INGREDIENT_PRICE_2);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        assertEquals(EXPECTED_PRICE_204_5, burger.getPrice(), DELTA);
    }

    @Test
    public void testGetPriceCalculatesCorrectBunPrice() {
        when(bunMock.getPrice()).thenReturn(BUN_PRICE_75);
        burger.setBuns(bunMock);

        // Bun price should be multiplied by 2 (top and bottom)
        assertEquals(150.0f, burger.getPrice(), DELTA);
    }

    @Test
    public void testGetPriceMockVerification() {
        when(bunMock.getPrice()).thenReturn(BUN_PRICE_80);
        when(ingredientMock1.getPrice()).thenReturn(INGREDIENT_PRICE_15);
        when(ingredientMock2.getPrice()).thenReturn(INGREDIENT_PRICE_20);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        burger.getPrice();

        verify(bunMock, times(1)).getPrice();
        verify(ingredientMock1, times(1)).getPrice();
        verify(ingredientMock2, times(1)).getPrice();
    }

    // ====== GET RECEIPT TESTS ======

    @Test
    public void testGetReceiptEmptyBurger() {
        when(bunMock.getName()).thenReturn(BUN_NAME_TEST);
        when(bunMock.getPrice()).thenReturn(BUN_PRICE_100);
        burger.setBuns(bunMock);

        String receipt = burger.getReceipt();
        assertNotNull(receipt);
        assertTrue(receipt.contains(RECEIPT_BUN_PREFIX + BUN_NAME_TEST + RECEIPT_BUN_SUFFIX));
        assertTrue(receipt.contains(RECEIPT_PRICE_PREFIX + EXPECTED_PRICE_200_STR));
    }

    @Test
    public void testGetReceiptWithSauceIngredient() {
        when(bunMock.getName()).thenReturn(BUN_NAME_FLUOR);
        when(bunMock.getPrice()).thenReturn(BUN_PRICE_150);
        when(ingredientMock1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock1.getName()).thenReturn(SAUCE_SPICY);
        when(ingredientMock1.getPrice()).thenReturn(INGREDIENT_PRICE_25);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);

        String receipt = burger.getReceipt();
        assertNotNull(receipt);
        assertTrue(receipt.contains(RECEIPT_BUN_PREFIX + BUN_NAME_FLUOR + RECEIPT_BUN_SUFFIX));
        assertTrue(receipt.contains(RECEIPT_SAUCE_PREFIX + SAUCE_SPICY + RECEIPT_INGREDIENT_SUFFIX));
        assertTrue(receipt.contains(RECEIPT_PRICE_PREFIX + EXPECTED_PRICE_325_STR));
    }

    @Test
    public void testGetReceiptWithFillingIngredient() {
        when(bunMock.getName()).thenReturn(BUN_NAME_CRATER);
        when(bunMock.getPrice()).thenReturn(BUN_PRICE_100);
        when(ingredientMock1.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientMock1.getName()).thenReturn(FILLING_PROTOSTOMIA);
        when(ingredientMock1.getPrice()).thenReturn(INGREDIENT_PRICE_75);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);

        String receipt = burger.getReceipt();
        assertNotNull(receipt);
        assertTrue(receipt.contains(RECEIPT_BUN_PREFIX + BUN_NAME_CRATER + RECEIPT_BUN_SUFFIX));
        assertTrue(receipt.contains(RECEIPT_FILLING_PREFIX + FILLING_PROTOSTOMIA + RECEIPT_INGREDIENT_SUFFIX));
        assertTrue(receipt.contains(RECEIPT_PRICE_PREFIX + EXPECTED_PRICE_275_STR));
    }

    @Test
    public void testGetReceiptWithMixedIngredients() {
        when(bunMock.getName()).thenReturn(BUN_NAME_SUPER);
        when(bunMock.getPrice()).thenReturn(BUN_PRICE_150);
        when(ingredientMock1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock1.getName()).thenReturn(SAUCE_HOT);
        when(ingredientMock1.getPrice()).thenReturn(INGREDIENT_PRICE_25);
        when(ingredientMock2.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientMock2.getName()).thenReturn(FILLING_CUTLET);
        when(ingredientMock2.getPrice()).thenReturn(INGREDIENT_PRICE_75);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        String receipt = burger.getReceipt();
        assertNotNull(receipt);
        assertTrue(receipt.contains(RECEIPT_BUN_PREFIX + BUN_NAME_SUPER + RECEIPT_BUN_SUFFIX));
        assertTrue(receipt.contains(RECEIPT_SAUCE_PREFIX + SAUCE_HOT + RECEIPT_INGREDIENT_SUFFIX));
        assertTrue(receipt.contains(RECEIPT_FILLING_PREFIX + FILLING_CUTLET + RECEIPT_INGREDIENT_SUFFIX));
        assertTrue(receipt.contains(RECEIPT_PRICE_PREFIX + EXPECTED_PRICE_400_STR));
    }

    @Test
    public void testGetReceiptMultipleSauces() {
        when(bunMock.getName()).thenReturn(BUN_NAME_REGULAR);
        when(bunMock.getPrice()).thenReturn(BUN_PRICE_50);
        when(ingredientMock1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock1.getName()).thenReturn(SAUCE_KETCHUP);
        when(ingredientMock1.getPrice()).thenReturn(INGREDIENT_PRICE_10);
        when(ingredientMock2.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock2.getName()).thenReturn(SAUCE_MAYO);
        when(ingredientMock2.getPrice()).thenReturn(INGREDIENT_PRICE_10);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        String receipt = burger.getReceipt();
        assertTrue(receipt.contains(RECEIPT_SAUCE_PREFIX + SAUCE_KETCHUP + RECEIPT_INGREDIENT_SUFFIX));
        assertTrue(receipt.contains(RECEIPT_SAUCE_PREFIX + SAUCE_MAYO + RECEIPT_INGREDIENT_SUFFIX));
        assertTrue(receipt.contains(RECEIPT_PRICE_PREFIX + EXPECTED_PRICE_120_STR));
    }

    @Test
    public void testGetReceiptMultipleFillings() {
        when(bunMock.getName()).thenReturn(BUN_NAME_SPECIAL);
        when(bunMock.getPrice()).thenReturn(BUN_PRICE_80);
        when(ingredientMock1.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientMock1.getName()).thenReturn(FILLING_SALAD);
        when(ingredientMock1.getPrice()).thenReturn(INGREDIENT_PRICE_15);
        when(ingredientMock2.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientMock2.getName()).thenReturn(FILLING_CHEESE);
        when(ingredientMock2.getPrice()).thenReturn(INGREDIENT_PRICE_20);
        when(ingredientMock3.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientMock3.getName()).thenReturn(FILLING_BACON);
        when(ingredientMock3.getPrice()).thenReturn(INGREDIENT_PRICE_30);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.addIngredient(ingredientMock3);

        String receipt = burger.getReceipt();
        assertTrue(receipt.contains(RECEIPT_FILLING_PREFIX + FILLING_SALAD + RECEIPT_INGREDIENT_SUFFIX));
        assertTrue(receipt.contains(RECEIPT_FILLING_PREFIX + FILLING_CHEESE + RECEIPT_INGREDIENT_SUFFIX));
        assertTrue(receipt.contains(RECEIPT_FILLING_PREFIX + FILLING_BACON + RECEIPT_INGREDIENT_SUFFIX));
        assertTrue(receipt.contains(RECEIPT_PRICE_PREFIX + EXPECTED_PRICE_225_STR));
    }

    @Test
    public void testGetReceiptFormatting() {
        when(bunMock.getName()).thenReturn(BUN_NAME_GENERIC);
        when(bunMock.getPrice()).thenReturn(BUN_PRICE_100);
        when(ingredientMock1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock1.getName()).thenReturn(SAUCE_TEST);
        when(ingredientMock1.getPrice()).thenReturn(INGREDIENT_PRICE_50);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);

        String receipt = burger.getReceipt();
        String[] lines = receipt.split("\n");

        assertEquals("(==== " + BUN_NAME_GENERIC + " ====)", lines[INDEX_ZERO].trim());
        assertEquals("= sauce " + SAUCE_TEST + " =", lines[INDEX_ONE].trim());
        assertEquals("(==== " + BUN_NAME_GENERIC + " ====)", lines[INDEX_TWO].trim());
        assertTrue(lines[4].trim().startsWith(RECEIPT_PRICE_PREFIX));
    }

    @Test
    public void testGetReceiptMockVerifications() {
        when(bunMock.getName()).thenReturn(BUN_NAME_TEST);
        when(bunMock.getPrice()).thenReturn(BUN_PRICE_100);
        when(ingredientMock1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock1.getName()).thenReturn(SAUCE_TEST);
        when(ingredientMock1.getPrice()).thenReturn(INGREDIENT_PRICE_25);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);

        burger.getReceipt();

        // Verify getName called twice for bun (beginning and end of receipt)
        verify(bunMock, times(2)).getName();
        verify(ingredientMock1, times(1)).getType();
        verify(ingredientMock1, times(1)).getName();
        // getPrice called once during price calculation
        verify(bunMock, times(1)).getPrice();
        verify(ingredientMock1, times(1)).getPrice();
    }

    // ====== SET BUNS TESTS ======

    @Test
    public void testSetBuns() {
        burger.setBuns(bunMock);
        assertEquals(bunMock, burger.bun);
    }

    @Test
    public void testSetBunsReplacesPreviousBun() {
        Bun firstBunMock = bunMock;
        Bun secondBunMock = mock(Bun.class);

        burger.setBuns(firstBunMock);
        burger.setBuns(secondBunMock);

        assertEquals(secondBunMock, burger.bun);
        assertNotEquals(firstBunMock, burger.bun);
    }

    @Test
    public void testInitialBunState() {
        assertNull(burger.bun);
    }
}