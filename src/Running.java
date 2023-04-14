
public class Running extends Shoe {

	private double weight;
	
	public Running(String brand, String type, double price, double weight) {
		super(brand, type, price);
		this.weight = weight;
	}

	public boolean isOnSale(int size) {
		if (getQuantity(size) > 3 && !(getBrand().toLowerCase().equals("asics") || getBrand().toLowerCase().equals("saucony"))) {
			return true;
		}
		
		return false;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public double getPrice(int size) {
		if (!isOnSale(size)) {
			return getBasePrice();
		}
		
		return Math.round((getBasePrice() * 0.7f) * 100.0) / 100.0;
	}
	
	public String getSpecial() {
		return "Weight: " + weight;
	}
}
