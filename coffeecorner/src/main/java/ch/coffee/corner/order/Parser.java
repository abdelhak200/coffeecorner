package ch.coffee.corner.order;

import java.util.ArrayList;
import java.util.List;

import ch.coffee.corner.extra.Extra;
import ch.coffee.corner.extra.exception.InvalidOrderException;
import ch.coffee.corner.product.Product;
import ch.coffee.corner.product.ProductType;
import ch.coffee.corner.product.Stock;

public class Parser {

	public List<Product> parseProduct(String products)
			throws InvalidOrderException {

		List<Product> productList = new ArrayList<>();
		String[] order = products.split("");

		if (!products.matches("[1-5]+")) {
			throw new InvalidOrderException("Invalid order: " + products);
		}
	
		for (String ord : order) {
			Product product = Stock.getProductsMap().get(Integer.parseInt(ord));
			productList.add(product);
		}

		return productList;
	}

	public List<Extra> parseExtras(String extras, int nbExtraBonus)
			throws InvalidOrderException {

		List<Extra> extrasList = new ArrayList<>();

		String[] order = extras.split("");
		
		if (extras.trim().length()>nbExtraBonus) {
			throw new InvalidOrderException("You have to choice a number of extra bonus less or equal to: "+nbExtraBonus);
		}

		if (!extras.matches("[1-3]+")) {
			throw new InvalidOrderException("Invalid order: " + extras);
		}
		
		for (String ord : order) {
			Extra extra = Stock.getExtrasMap().get(Integer.parseInt(ord));
			extrasList.add(extra);

		}

		return extrasList;
	}

	public int checkIfHaveExtras(List<Product> productList) {
		int extraBonus = 0;
		long nbOfBeverage = productList.stream().filter(p -> p.getType().equals(ProductType.BEVERAGE)).count();
		long nbOfSnack = productList.stream().filter(p -> p.getType().equals(ProductType.SNACK)).count();

		while(nbOfBeverage-- >= 1 && nbOfSnack-- >= 1) {
			extraBonus++;
		}
		return extraBonus;
	}

}
