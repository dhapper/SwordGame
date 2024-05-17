
public class Armour {

	private String name;
	private double protection, speedModifier;
	private int staminaUsage;
	
	public Armour(String name) {
		//mongodb get sword and stats
		this.name = name;
		this.protection = 1;
		this.speedModifier = 1;
		this.staminaUsage = 1;
	}
	
	public void displayStats() {
		System.out.println("Displaying Armour's Stats:");
		System.out.println(name+"\n"+protection+"\n"+speedModifier+"\n"+staminaUsage);
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

	public double getStaminaUsage() {
		return staminaUsage;
	}

	public void setStaminaUsage(int staminaUsage) {
		this.staminaUsage = staminaUsage;
	}
	
	
	
}
