
public class Shield {
	private String name;
	private int weight, resistance, durability;
	
	public Shield(String name) {
		// mongodb get sword and stats
		this.name = name;
		this.resistance = 80;
		this.durability = 10;
		this.weight = 10;
		
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected int getResistance() {
		return resistance;
	}

	protected void setResistance(int resistance) {
		this.resistance = resistance;
	}

	protected int getDurability() {
		return durability;
	}

	protected void setDurability(int durability) {
		this.durability = durability;
	}

	protected int getWeight() {
		return weight;
	}

	protected void setWeight(int weight) {
		this.weight = weight;
	}
	
	
	
}
