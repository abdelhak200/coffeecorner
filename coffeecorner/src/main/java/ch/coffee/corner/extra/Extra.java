package ch.coffee.corner.extra;

public class Extra {

	private String name;
	private Double price;
	
	public Extra() {}

	public Extra(String name, Double price) {
		this.name = name;
		this.price = price;
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

	@Override
	public String toString() {
		return "Extra [name=" + name + ", price=" + price + "]";
	}

}
