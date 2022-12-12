package ch.coffee.corner.product;

public class Product {

	private String name; 
	private Double price;
	private ProductType type;
	
	public Product() {}

	public Product(String name, Double price, ProductType type) {
		this.name = name;
		this.price = price;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", price=" + price + ", type=" + type + "]";
	}

}
