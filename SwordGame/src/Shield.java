
public class Shield {
	private String name;
	private int weight, resistance, durability, staminaRegain;
	
	public Shield(String name) {
		this.name = name;
	}
	
	public void displayStats() {
		System.out.println("Displaying Shield's Stats:");
		System.out.println(name+"\n"+resistance+"\n"+weight+"\n"+durability+"\n"+durability+"\n"+staminaRegain);
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

	public int getStaminaRegain() {
		return staminaRegain;
	}

	public void setStaminaRegain(int staminaRegain) {
		this.staminaRegain = staminaRegain;
	}

	
	
}
