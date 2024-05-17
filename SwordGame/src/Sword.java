
public class Sword {

	private String name;
	private int damage, weight, piercing, durability, staminaUsage;
	
	public Sword(String name) {
		//mongodb get sword and stats
		this.name = name;
		this.damage = 20;
		this.weight = 10;
		this.piercing = 10;
		this.durability = 10;
		this.staminaUsage = 1;
		
	}
	
	public void displayStats() {
		System.out.println("Displaying Swords's Stats:");
		System.out.println(name+"\n"+damage+"\n"+weight+"\n"+piercing+"\n"+durability+"\n"+staminaUsage);
	}
	
	protected int getStaminaUsage() {
		return staminaUsage;
	}

	protected void setStaminaUsage(int staminaUsage) {
		this.staminaUsage = staminaUsage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getPiercing() {
		return piercing;
	}

	public void setPiercing(int piercing) {
		this.piercing = piercing;
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}
	
	
	
}
