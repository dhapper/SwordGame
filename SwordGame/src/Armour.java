
public class Armour {

	private String name;
	private double protection, weight;
	
	public Armour(String name) {
		//mongodb get sword and stats
		this.name = name;
		this.protection = 1;
		this.weight = 1;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getProtection() {
		return protection;
	}

	public void setProtection(double protection) {
		this.protection = protection;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	
}
