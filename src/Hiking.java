
public class Hiking extends Shoe {

	int traction; // Value from 1 to 10
	
	public Hiking(String brand, String type, double price, int traction) {
		super(brand, type, price);
		this.traction = traction;
		
		if (traction < 1) {
			this.traction = 1;
		}
		
		if (traction > 10) {
			this.traction = 10;
		}
	}
	
	public double getTraction() {
		return traction;
	}

	public String getSpecial() {
		return "Traction: " + traction;
	}
}
