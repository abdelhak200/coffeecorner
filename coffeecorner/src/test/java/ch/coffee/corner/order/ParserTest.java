package ch.coffee.corner.order;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ch.coffee.corner.extra.Extra;
import ch.coffee.corner.extra.exception.InvalidOrderException;
import ch.coffee.corner.product.Product;

public class ParserTest {

	
	private Parser underTest;
	
	@BeforeEach
	public void setUp() {
		underTest = new Parser();
	}

	@Test
	public void testBeverageWithoutExtras() throws InvalidOrderException  {
		List<Product> prodList = underTest.parseProduct("1234");

		// expect 4 product
		Assertions.assertEquals(4, prodList.size());
		Assertions.assertEquals("small coffee", prodList.get(0).getName());
		Assertions.assertEquals("medium coffee", prodList.get(1).getName());
		Assertions.assertEquals("large coffee", prodList.get(2).getName());
		Assertions.assertEquals("orange juice", prodList.get(3).getName());

		// expect 0 extra
		Assertions.assertEquals(0, underTest.checkIfHaveExtras(prodList));
	}
	
	@Test
	public void testBeverageSnackWithExtras() throws InvalidOrderException  {
		List<Product> prodList = underTest.parseProduct("1235");

		// expect 4 product
		Assertions.assertEquals(4, prodList.size());
		Assertions.assertEquals("small coffee", prodList.get(0).getName());
		Assertions.assertEquals("medium coffee", prodList.get(1).getName());
		Assertions.assertEquals("large coffee", prodList.get(2).getName());
		Assertions.assertEquals("bacon roll", prodList.get(3).getName());

		// expect 1 extra
		Assertions.assertEquals(1, underTest.checkIfHaveExtras(prodList));
	}
	
	@Test
	public void testBeverageAndExtras() throws InvalidOrderException  {
		List<Product> prodList = underTest.parseProduct("1235");
		List<Extra> extraList = underTest.parseExtras("123",Integer.MAX_VALUE);

		// expect 4 product
		Assertions.assertEquals(4, prodList.size());
		Assertions.assertEquals("small coffee", prodList.get(0).getName());
		Assertions.assertEquals("medium coffee", prodList.get(1).getName());
		Assertions.assertEquals("large coffee", prodList.get(2).getName());
		Assertions.assertEquals("bacon roll", prodList.get(3).getName());
		
		// expect 3 extras
		Assertions.assertEquals(3, extraList.size());
		Assertions.assertEquals("extra milk", extraList.get(0).getName());
		Assertions.assertEquals("foamed milk", extraList.get(1).getName());
		Assertions.assertEquals("special roast coffee", extraList.get(2).getName());

		// expect 1 extra
		Assertions.assertEquals(1, underTest.checkIfHaveExtras(prodList));
	}
	
	@Test
	public void testBeverageWitInvalidOrder() throws InvalidOrderException  {
	
		InvalidOrderException exception = Assertions.assertThrows(InvalidOrderException.class,
			    () -> underTest.parseProduct("1238"));
		
		Assertions.assertEquals(exception.getMessage(), "Invalid order: 1238");
	}
	
	@Test
	public void testExtraWitInvalidOrder() throws InvalidOrderException  {
	
		InvalidOrderException exception = Assertions.assertThrows(InvalidOrderException.class,
			    () -> underTest.parseExtras("18",Integer.MAX_VALUE));
		
		Assertions.assertEquals(exception.getMessage(), "Invalid order: 18");
	}
	
	@Test
	public void testBeverageWitUpLimitOfExtrasInvalidOrderException() throws InvalidOrderException  {
		List<Product> prodList = underTest.parseProduct("1235");
		List<Extra> extraList = underTest.parseExtras("123",Integer.MAX_VALUE);

		// expect 4 product
		Assertions.assertEquals(4, prodList.size());
		Assertions.assertEquals("small coffee", prodList.get(0).getName());
		Assertions.assertEquals("medium coffee", prodList.get(1).getName());
		Assertions.assertEquals("large coffee", prodList.get(2).getName());
		Assertions.assertEquals("bacon roll", prodList.get(3).getName());
		
		// expect 3 extras
		Assertions.assertEquals(3, extraList.size());
		Assertions.assertEquals("extra milk", extraList.get(0).getName());
		Assertions.assertEquals("foamed milk", extraList.get(1).getName());
		Assertions.assertEquals("special roast coffee", extraList.get(2).getName());

		// expect 1 extra
		Assertions.assertEquals(1, underTest.checkIfHaveExtras(prodList));
		
		InvalidOrderException exception = Assertions.assertThrows(InvalidOrderException.class,
			    () -> underTest.parseExtras("12",1));
		
		Assertions.assertEquals(exception.getMessage(), "You have to choice a number of extra bonus less or equal to: 1");
		
	}
	
	@Test
	public void testBeverageWithProductNotFound() throws InvalidOrderException  {
		List<Product> prodList = underTest.parseProduct("1234");
		prodList.add(null);

		// expect 5 product
		Assertions.assertEquals(5, prodList.size());

		NullPointerException exception = Assertions.assertThrows(NullPointerException.class,
			    () -> underTest.checkIfHaveExtras(prodList));
		
		Assertions.assertEquals(exception.getMessage(), "Cannot invoke \"ch.coffee.corner.product.Product.getType()\" because \"p\" is null");
	}

}
