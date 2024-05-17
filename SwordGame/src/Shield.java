
public class Shield {
	private String name;
	private int weight, resistance, durability, staminaUsage;
	
	public Shield(String name) {
		// mongodb get sword and stats
		/*this.name = name;
		this.resistance = 80;
		this.durability = 10;
		this.weight = 10;
		this.staminaUsage = 1;*/
		this.name = name;
	}
	
	public void displayStats() {
		System.out.println("Displaying Shield's Stats:");
		System.out.println(name+"\n"+resistance+"\n"+weight+"\n"+durability+"\n"+durability+"\n"+staminaUsage);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getResistance() {
		return resistance;
	}

	public void setResistance(int resistance) {
		this.resistance = resistance;
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}

	public int getStaminaUsage() {
		return staminaUsage;
	}

	public void setStaminaUsage(int staminaUsage) {
		this.staminaUsage = staminaUsage;
	}

	
	
}
