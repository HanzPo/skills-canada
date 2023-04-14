import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class ShoeStore {

	private String name;
	private ArrayList<Shoe> inventory = new ArrayList<>();
	private StringBuilder builder = new StringBuilder();

	public static void main(String[] args) {

		// Initialize ShoeStore object
		ShoeStore store = new ShoeStore("Feet Locker");
		store.loadInventory("src/ShoeInventory1.csv");
		
		// Output
		store.welcome();
		System.out.println("Most Expensive Shoe: " + store.mostExpensiveShoe());
		System.out.println("Most Stocked Shoe: " + store.highestStockedShoe());
		System.out.println("Most Shoe Sizes: " + store.mostSizes());
		System.out.println("Most On Sale: " + store.mostOnSale());
		System.out.println("\n\n");
		System.out.println("Pricing Report\n----------------------------------------");
		store.printPricing();
		System.out.println("\n\n");
		System.out.println("Inventory Report\n----------------------------------------");
		store.printInventory();
		
		System.exit(0);
	}

	// ShoeStore constructor
	public ShoeStore (String name) {
		this.name = name;
	}
	
	public void loadInventory(String fileNa) {

		String line = "";
		String values[];

		// Load csv file into ArrayList
		try {
			Scanner input = new Scanner(new File(fileNa));
			boolean isFirstLine = true;

			// Iterate through csv file until no lines left
			while(input.hasNext()) {

				line = input.nextLine();

				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}

				// Comma separated values file gets separated by commas
				values = line.split(",");


				boolean exists = false;
				int existsIndex = 0;
				
				// Create object and put into ArrayList based on shoe type
				switch(values[0]) {
				case "Walking":

					for (int i = 0; i < inventory.size(); i++) {
						Shoe currentShoe = inventory.get(i);


						if (currentShoe.getType().equals("Walking") && currentShoe.getBrand().equals(values[1])) {
							exists = true;
							existsIndex = i;
						}
					}
					
					if (!exists) {
						inventory.add(new Walking(values[1], values[0], Double.parseDouble(values[3]), values[5].equals("true")));
						inventory.get(inventory.size() - 1).setQuantity(Integer.parseInt(values[2]), Integer.parseInt(values[4]));
					} else {
						inventory.get(existsIndex).setQuantity(Integer.parseInt(values[2]), Integer.parseInt(values[4]));
					}

					break;
				case "Hiking":
					
					for (int i = 0; i < inventory.size(); i++) {
						Shoe currentShoe = inventory.get(i);


						if (currentShoe.getType().equals("Hiking") && currentShoe.getBrand().equals(values[1])) {
							exists = true;
							existsIndex = i;
						}
					}

					if (!exists) {
						inventory.add(new Hiking(values[1], values[0], Double.parseDouble(values[3]), Integer.parseInt(values[5])));
						inventory.get(inventory.size() - 1).setQuantity(Integer.parseInt(values[2]), Integer.parseInt(values[4]));
					} else {
						inventory.get(existsIndex).setQuantity(Integer.parseInt(values[2]), Integer.parseInt(values[4]));
					}

					break;
				case "Running":
					
					for (int i = 0; i < inventory.size(); i++) {
						Shoe currentShoe = inventory.get(i);


						if (currentShoe.getType().equals("Running") && currentShoe.getBrand().equals(values[1])) {
							exists = true;
							existsIndex = i;
						}
					}

					if (!exists) {
						inventory.add(new Running(values[1], values[0], Double.parseDouble(values[3]), Double.parseDouble(values[5])));
						inventory.get(inventory.size() - 1).setQuantity(Integer.parseInt(values[2]), Integer.parseInt(values[4]));
					} else {
						inventory.get(existsIndex).setQuantity(Integer.parseInt(values[2]), Integer.parseInt(values[4]));
					}
					
					break;
				default:
					for (int i = 0; i < inventory.size(); i++) {
						Shoe currentShoe = inventory.get(i);


						if (currentShoe.getType().equals(values[0]) && currentShoe.getBrand().equals(values[1])) {
							exists = true;
							existsIndex = i;
						}
					}

					if (!exists) {
						inventory.add(new Shoe(values[1], values[0], Double.parseDouble(values[3])));
						inventory.get(inventory.size() - 1).setQuantity(Integer.parseInt(values[2]), Integer.parseInt(values[4]));
					} else {
						inventory.get(existsIndex).setQuantity(Integer.parseInt(values[2]), Integer.parseInt(values[4]));
					}
					break;
				}
			}
		}

		catch(IOException e) {
			JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public String mostExpensiveShoe() {
		double highestCost = 0.0;
		String mostExpensiveShoe = "";
		
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i).getBasePrice() > highestCost) {
				highestCost = inventory.get(i).getBasePrice();
				mostExpensiveShoe = inventory.get(i).getBrand();
			}
		}
		
		return mostExpensiveShoe + " at $" + highestCost;
	}

	public String highestStockedShoe() {
		int maxStock = 0;
		String maxBrand = "";
		String maxType = "";
		
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i).getTotalQuantity() > maxStock) {
				maxStock = inventory.get(i).getTotalQuantity();
				maxBrand = inventory.get(i).getBrand();
				maxType = inventory.get(i).getType();
			}
		}
		
		return maxBrand + " " + maxType;
	}

	public String mostSizes() {
		int maxSizes = 0;
		String maxBrand = "";
		String maxType = "";
		
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i).getTotalSizes() > maxSizes) {
				maxSizes = inventory.get(i).getTotalSizes();
				maxBrand = inventory.get(i).getBrand();
				maxType = inventory.get(i).getType();
			}
		}
		
		return maxBrand + " " + maxType;
	}

	public String mostOnSale() {
		int maxSales = 0;
		String maxBrand = "";
		String maxType = "";
		
		for (int i = 0; i < inventory.size(); i++) {
			int sales = 0;
			for (int j = 7; j <= 12; j++) {
				if (inventory.get(i).isOnSale(j)) {
					sales++;
				}
			}
			
			if (sales > maxSales) {
				maxSales = sales;
				maxBrand = inventory.get(i).getBrand();
				maxType = inventory.get(i).getType();
			}
		}
		
		return maxBrand + " " + maxType;
	}

	public void printPricing() {
		builder.setLength(0);
		
		builder.append(String.format("%-15s%-15s%-12s%-12s%-12s%-12s%-12s%-12s%-20s", "Type", "Shoe", "7", "8", "9", "10", "11", "12", "Special"));
		builder.append("\n-------\n");
		
		for (int i = 0; i < inventory.size(); i++) {
			Shoe currentShoe = inventory.get(i);
			
			
			
			builder.append(String.format("%-15s%-15s%-12s%-12s%-12s%-12s%-12s%-12s%-20s", currentShoe.getType(), currentShoe.getBrand(), priceInfo(currentShoe, 7), priceInfo(currentShoe, 8), priceInfo(currentShoe, 9), priceInfo(currentShoe, 10), priceInfo(currentShoe, 11), priceInfo(currentShoe, 12), currentShoe.getSpecial()));
			builder.append("\n");
		}
		
		builder.append("\nShoes with a * are on sale");
		
		System.out.println(builder.toString());
	}

	public void printInventory() {
		builder.setLength(0);
		
		builder.append(String.format("%-15s%-15s%-12s%-12s%-12s%-12s%-12s%-12s%-20s", "Type", "Shoe", "7", "8", "9", "10", "11", "12", "Special"));
		builder.append("\n-------\n");
		
		for (int i = 0; i < inventory.size(); i++) {
			Shoe currentShoe = inventory.get(i);
			
			
			
			builder.append(String.format("%-15s%-15s%-12s%-12s%-12s%-12s%-12s%-12s%-20s", currentShoe.getType(), currentShoe.getBrand(), currentShoe.getQuantity(7), currentShoe.getQuantity(8), currentShoe.getQuantity(9), currentShoe.getQuantity(10), currentShoe.getQuantity(11), currentShoe.getQuantity(12), currentShoe.getSpecial()));
			builder.append("\n");
		}
		
		System.out.println(builder.toString());
	}

	public String priceInfo(Shoe currentShoe, int size) {
		return ((currentShoe.getQuantity(size) > 0) ? "$" + (currentShoe.getPrice(size) + (currentShoe.isOnSale(size) ? "*" : "")) : "");
	}
	
	public void welcome() {
		System.out.println(name + " Database\n----------------------------------------\n");
	}
}
