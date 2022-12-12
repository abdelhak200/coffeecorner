package ch.coffee.corner.main;

import java.util.List;
import java.util.Scanner;

import ch.coffee.corner.extra.Extra;
import ch.coffee.corner.extra.exception.InvalidOrderException;
import ch.coffee.corner.order.Order;
import ch.coffee.corner.order.Parser;
import ch.coffee.corner.order.Receipt;
import ch.coffee.corner.product.Product;

public class Main {

	public static void main(String[] args) {
		try {

			Scanner in = new Scanner(System.in);
			
			// the actual value of customer stamp card can be what ever an initial value from 0 to 5
			int stampCard = 2;
			System.out.println("Can I take your order?\nChoose the number of corresponding product!");
			System.out.println("""
						1- small coffee  2.50 chf
						2- medium coffee 3.00 chf
						3- large coffee  3.50 chf
						4- orange juice  3.95 chf
						5- bacon roll    4.50 chf
					""");

			String product = in.nextLine();

			Parser parser = new Parser();
			List<Product> productList = null;

			if (!product.isBlank()) {
				productList = parser.parseProduct(product);

				System.out.println("Do you desire an extra.\nChoose the number of corresponding product!");
				System.out.println("""
						1-extra milk           0.30 chf
						2-foamed milk          0.50 chf
						3-special roast coffee 0.90 chf
						""");
				String extra = in.nextLine();

				List<Extra> extraList = null;

				if (!extra.isBlank()) {
					extraList = parser.parseExtras(extra, Integer.MAX_VALUE);
				}
				List<Extra> extraBonusList = null;
				int nbExtraBonus = parser.checkIfHaveExtras(productList);
				if (nbExtraBonus > 0) {
					System.out.println("You have an extra :'" + nbExtraBonus
							+ "' bonus.\nChoose the number of corresponding bonus!");
					System.out.println("""
							1-extra milk
							2-foamed milk
							3-special roast coffee
							""");
					String extraBonus = in.nextLine();

					extraBonusList = parser.parseExtras(extraBonus, nbExtraBonus);
				}
				Receipt receipt = new Receipt(stampCard);

				System.out.println(receipt.getReceipt(new Order(productList, extraList), extraBonusList));
			}
			System.out.println("Good day!");

		} catch (InvalidOrderException e) {
			System.out.println(e.getMessage());
		}
	}

}
