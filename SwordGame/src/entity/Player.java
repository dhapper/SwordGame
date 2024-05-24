package entity;
import java.util.ArrayList;

public class Player {
	
	private String username, nickname;
	private Inventory inventory;
    private int health, strength, speed, stamina, knowledge;
    
	public Player(String username) {
		this.username = username;
		
        this.inventory = new Inventory(this);
        this.inventory.updatePlayerInventory();
        
	}
	
	public void displayStats() {
		System.out.println("Displaying Player's Stats:");
		System.out.println(username+"\n"+nickname+"\n"+health+"\n"+strength+"\n"+speed+"\n"+stamina+"\n"+knowledge);
	}

	// getters and setters
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public Inventory getInventory() {
		return inventory;
		
	}
	
}
