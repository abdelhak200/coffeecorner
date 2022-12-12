package ch.coffee.corner.order;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ch.coffee.corner.extra.Extra;
import ch.coffee.corner.extra.exception.InvalidOrderException;
import ch.coffee.corner.product.Product;

public class ReceiptTest {
	
	private Receipt underTest;
	private String recpt;
	
	@BeforeEach
	public void setUp() {
		// the actual value of customer stamp card can be what ever an initial value from 0 to 5
		int stampCard = 3;
		underTest = new Receipt(stampCard);
		
		recpt = """
				Product                             Price
				----------------                    -----
				small coffee                          0.0
				medium coffee                         3.0
				large coffee                          3.5
				bacon roll                            4.5
				extra milk                            0.3
				foamed milk                           0.5
				special roast coffee                  0.9
				special roast coffee                  0.0
				-----                               -----
				Total                                12.4
				
				Beverage stamp card value: 1
				""";
	}
	
    @Test
    public void testGetTotalPrice() throws InvalidOrderException {
    	Parser parser = new Parser();
    	List<Product> prodList = parser.parseProduct("1235");
    	List<Extra> extraList = parser.parseExtras("123",Integer.MAX_VALUE);
    	
    	Order order = new Order(prodList, extraList);
        Assertions.assertEquals(12.4, underTest.getTotalPrice(order));
    }

    @Test
    public void testGetReceipt() throws InvalidOrderException {
    	
    	Parser parser = new Parser();
    	List<Product> prodList = parser.parseProduct("1235");
    	List<Extra> extraList = parser.parseExtras("123",Integer.MAX_VALUE);
    	
    	Order order = new Order(prodList, extraList);

		// expect 1 extra
		Assertions.assertEquals(1, parser.checkIfHaveExtras(prodList));

		List<Extra> extraBonusList = parser.parseExtras("3",1);
		
		// total
		Assertions.assertEquals(12.4, underTest.getTotalPrice(order));
		
		Assertions.assertEquals(recpt, underTest.getReceipt(order,extraBonusList));
		
    }

}
