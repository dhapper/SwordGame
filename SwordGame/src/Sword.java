
public class Sword {

	private String name;
	private int damage, weight, peircing, durability;
	
	public Sword(String name) {
		//mongodb get sword and stats
		this.name = name;
		this.damage = 20;
		this.weight = 1;
		this.peircing = 0;
		this.durability = 10;
		
	}
	
	public void displayStats() {
		System.out.println(name+"\n"+damage);
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

	public int getPeircing() {
		return peircing;
	}

	public void setPeircing(int peircing) {
		this.peircing = peircing;
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}
	
	
	
}
