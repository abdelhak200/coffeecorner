package ch.coffee.corner.order;

import java.util.List;

import ch.coffee.corner.extra.Extra;
import ch.coffee.corner.product.Product;

public class Order {
	
	private List<Product> product;
	private List<Extra> extras;

	public Order() {}

	public Order(List<Product> product, List<Extra> extras) {
		this.product = product;
		this.extras = extras;
	}

	public List<Product> getProduct() {
		return product;
	}

	public List<Extra> getExtras() {
		return extras;
	}

	@Override
	public String toString() {
		return "Order [product=" + product + ", extras=" + extras + "]";
	}

}
