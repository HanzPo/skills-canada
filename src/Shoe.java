
public class Shoe {

	// Declare variables
	private String brand;
	private String type;
	private double price;
	private int[] quantity;
	
	// Constructor, initialize variables
	public Shoe(String brand, String type, double price) {
		this.brand = brand;
		this.type = type;
		this.price = price;
		quantity = new int[6];
	}
	
	// Getters and setters 
	
	public String getBrand() {
		return brand;
	}
	
	public void setQuantity(int size, int qty) {
		this.quantity[size - 7] = qty;
	}
	
	public int getQuantity(int size) {
		if (size >= 7 && size <= 12) {
			return quantity[size - 7];
		}
		
		return 0;
	}
	
	public int getTotalQuantity() {
		int sum = 0;
		
		for (int i = 0; i < quantity.length; i++) {
			sum += quantity[i];
		}
		
		return sum;
	}
	
	public int getTotalSizes() {
		int sizes = 0;
		
		for (int i = 0; i < quantity.length; i++) {
			if (quantity[i] > 0) {
				sizes++;
			}
		}
		
		return sizes;
	}
	
	public double getPrice (int size) {
		if (!isOnSale(size)) {
			return price;
		}
		else {
			return Math.round((price * 0.8f) * 100.0) / 100.00;
		}
	}
	
	public double getBasePrice() {
		return price;
	}
	
	public boolean isOnSale(int size) {
		return false;
	}

	public String getType() {
		return type;
	}
	
	public String getSpecial() {
		return "";
	}
}
