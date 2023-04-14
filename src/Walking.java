
public class Walking extends Shoe {

	private boolean goreTex;
	
	public Walking(String brand, String type, double price, boolean goreTex) {
		super(brand, type, price);
		this.goreTex = goreTex;
	}
	
	public boolean getGoreTex() {
		return goreTex;
	}
	
	public boolean isOnSale(int size) {
		// may have to change from super to this??
		if (getQuantity(size) > 2 && !getBrand().toLowerCase().equals("puma")) {
			return true;
		}
		
		return false;
	}
	
	public String getSpecial() {
		return "Goretex?: " + goreTex;
	}
}
