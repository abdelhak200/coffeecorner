package ch.coffee.corner.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ch.coffee.corner.extra.Extra;
import ch.coffee.corner.product.Product;
import ch.coffee.corner.product.ProductType;

public class Receipt {

	private static final int BEVERAGE_NUMBER = 5;

	private int stampCard;

	public Receipt(int stampCard) {
		this.stampCard = stampCard;
	}

	public Double getTotalPrice(Order order) {
		List<Product> products = Optional.ofNullable(order.getProduct()).orElse(new ArrayList<Product>());
		List<Extra> extras = Optional.ofNullable(order.getExtras()).orElse(new ArrayList<Extra>());

		int beverageBonus = getBeverageExtrasBonus(products).get(0);
		int extraBonus = getBeverageExtrasBonus(products).get(1);

		double finalPrice = 0.0;
		for (Product product : products) {

			if (product.getType() == ProductType.BEVERAGE && beverageBonus > 0) {
				beverageBonus--;
			} else {
				finalPrice += product.getPrice();
			}
		}

		for (Extra extra : extras) {
			if (extraBonus > 0) {
				extraBonus--;
			} else {
				finalPrice += extra.getPrice();
			}
		}

		return finalPrice;
	}

	public String getReceipt(Order order, List<Extra> extraBonusList) {
		int beverageBonus = 0;
		
		List<Product> products = Optional.ofNullable(order.getProduct()).orElse(new ArrayList<Product>());
		List<Extra> extras = Optional.ofNullable(order.getExtras()).orElse(new ArrayList<Extra>());
		List<Extra> extraBonusL = Optional.ofNullable(extraBonusList).orElse(new ArrayList<Extra>());

		long nbOfBeverage = products.stream().filter(p -> p.getType().equals(ProductType.BEVERAGE)).count();

		int timesStampCard = stampCard + (int) nbOfBeverage;

		while (timesStampCard >= BEVERAGE_NUMBER) {
			beverageBonus++;
			timesStampCard -= BEVERAGE_NUMBER;
		}


		final String format = "%-20s %20s\n";

		final StringBuilder sb = new StringBuilder();
		sb.append(String.format(format, "Product", "Price"));
		sb.append(String.format(format, "----------------", "-----"));

		for (Product product : products) {
			if (product.getType() == ProductType.BEVERAGE && beverageBonus-- > 0) {
				sb.append(String.format(format, product.getName(), "0.0"));
			} else {
				sb.append(String.format(format, product.getName(), product.getPrice()));
			}

		}
		
		for (Extra extra : extras) {
				sb.append(String.format(format, extra.getName(), extra.getPrice()));
		}

		for (Extra extra : extraBonusL) {
				sb.append(String.format(format, extra.getName(), "0.0"));
		}

		sb.append(String.format(format, "-----", "-----"));
		sb.append(String.format(format, "Total", getTotalPrice(order)));

		sb.append("\n");
		sb.append("Beverage stamp card value: "+(timesStampCard)+"\n");

		return sb.toString();
	}

	private List<Integer> getBeverageExtrasBonus(List<Product> products) {
		int beverageBonus = 0;
		int extraBonus = 0;

		long nbOfBeverage = products.stream().filter(p -> p.getType().equals(ProductType.BEVERAGE)).count();
		long nbOfSnack = products.stream().filter(p -> p.getType().equals(ProductType.SNACK)).count();

		int timesStampCard = stampCard + (int) nbOfBeverage;

		while (timesStampCard >= BEVERAGE_NUMBER) {
			beverageBonus++;
			timesStampCard -= BEVERAGE_NUMBER;
		}

		while(nbOfBeverage-- >= 1 && nbOfSnack-- >= 1) {
			extraBonus++;
		}

		return List.of(beverageBonus, extraBonus);
	}
	
}
