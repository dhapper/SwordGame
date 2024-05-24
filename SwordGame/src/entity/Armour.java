package entity;

public class Armour {

	private String name;
	private double protection, speedModifier;
	
	public Armour(String name) {
		this.name = name;
	}
	
	public void displayStats() {
		System.out.println("Displaying Armour's Stats:");
		System.out.println(name+"\n"+protection+"\n"+speedModifier);
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

	public double getSpeedModifier() {
		return speedModifier;
	}

	public void setSpeedModifier(double speedModifier) {
		this.speedModifier = speedModifier;
	}

	
	
}
