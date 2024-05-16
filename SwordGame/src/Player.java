
public class Player {
	
	private String nickName;
    private int health, strength, speed, stamina, knowledge;
    private Sword sword;
    private Shield shield;
    private Armour armour;
    
	public Player(String nickName, int health, int strength, int speed, int stamina, int knowledge) {
		this.nickName = nickName;
        this.health = health;
        this.strength = strength;
        this.speed = speed;
        this.stamina = stamina;
        this.knowledge = knowledge;
        this.sword = new Sword("basic");
        this.shield = new Shield("wood shield");
        this.armour = new Armour("shirt");
        
	}
	
	public void displayStats() {
		System.out.println(nickName+"\n"+health+"\n"+strength+"\n"+speed+"\n"+stamina+"\n"+knowledge);
	}

	
	// getters and setters
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	public int getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(int knowledge) {
		this.knowledge = knowledge;
	}

	public Sword getSword() {
		return sword;
	}

	public void setSword(Sword sword) {
		this.sword = sword;
	}

	public Shield getShield() {
		return shield;
	}

	public void setShield(Shield shield) {
		this.shield = shield;
	}

	public Armour getArmour() {
		return armour;
	}

	public void setArmour(Armour armour) {
		this.armour = armour;
	}
	
	
	
}
