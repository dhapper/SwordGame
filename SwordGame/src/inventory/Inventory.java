package inventory;
import java.util.ArrayList;

import entity.Armour;
import entity.Player;
import entity.Shield;
import entity.Sword;
import utilz.UseMongoDB;

public class Inventory {

	Player player;
	ArrayList<Sword> swords = new ArrayList<>();
	ArrayList<Shield> shields = new ArrayList<>();
	ArrayList<Armour> armoury = new ArrayList<>();
	Sword activeSword, inactiveSword;
	Shield activeShield;
	Armour activeArmour;
	
	UseMongoDB mongo;
	
	public Inventory(Player player) {
		this.player = player;
		
		this.mongo = new UseMongoDB();
	}
	
	public void updatePlayerInventory() {
        mongo.getSwords(player);
        mongo.getShields(player);
        mongo.getArmoury(player);
        mongo.getActiveItem(player, "ACTIVE_SWORD");
        mongo.getActiveItem(player, "INACTIVE_SWORD");
        mongo.getActiveItem(player, "ACTIVE_SHIELD");
        mongo.getActiveItem(player, "ACTIVE_ARMOUR");
	}
	
	public void swapActiveSword() {
		Sword temp = activeSword;
		activeSword = inactiveSword;
		inactiveSword = temp;
		
		mongo.setActiveItem(this.player, "ACTIVE_SWORD", this.activeSword.getName());
		mongo.setActiveItem(this.player, "INACTIVE_SWORD", this.inactiveSword.getName());
	}
	
	protected Sword getSword(int swordIndex) {
		return swords.get(swordIndex);
	}

	protected void setSwords(ArrayList<Sword> swords) {
		this.swords = swords;
	}

	public ArrayList<Sword> getSwords() {
		return swords;
	}


	public Sword getActiveSword() {
		return activeSword;
	}


	public void setActiveSword(Sword activeSword) {
		
		this.activeSword = activeSword;
		
		mongo.setActiveItem(this.player, "ACTIVE_SWORD", this.activeSword.getName());
	}


	public Sword getInactiveSword() {
		return inactiveSword;
	}


	public void setInactiveSword(Sword inactiveSword) {
		this.inactiveSword = inactiveSword;
		
		mongo.setActiveItem(this.player, "INACTIVE_SWORD", this.inactiveSword.getName());
	}


	public Shield getActiveShield() {
		return activeShield;
	}


	public void setActiveShield(Shield activeShield) {
		this.activeShield = activeShield;
		
		mongo.setActiveItem(this.player, "ACTIVE_SHIELD", this.activeShield.getName());
	}


	public Armour getActiveArmour() {
		return activeArmour;
	}


	public void setActiveArmour(Armour activeArmour) {
		this.activeArmour = activeArmour;
		
		mongo.setActiveItem(this.player, "ACTIVE_ARMOUR", this.activeArmour.getName());
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Shield> getShields() {
		return shields;
	}

	public void setShields(ArrayList<Shield> shields) {
		this.shields = shields;
	}

	public ArrayList<Armour> getArmoury() {
		return armoury;
	}

	public void setArmoury(ArrayList<Armour> armour) {
		this.armoury = armour;
	}
	
	
	
}
