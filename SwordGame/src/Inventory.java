import java.util.ArrayList;

public class Inventory {

	Player player;
	ArrayList<Sword> swords = new ArrayList<>();
	ArrayList<Shield> shields = new ArrayList<>();
	ArrayList<Armour> armoury = new ArrayList<>();
	Sword activeSword, inactiveSword;
	Shield activeShield;
	Armour activeArmour;
	
	public Inventory(Player player) {
		this.player = player;
		
	}
	
	public void updatePlayerInventory() {
		UseMongoDB mongo = new UseMongoDB();
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
	}


	public Sword getInactiveSword() {
		return inactiveSword;
	}


	public void setInactiveSword(Sword inactiveSword) {
		this.inactiveSword = inactiveSword;
	}


	public Shield getActiveShield() {
		return activeShield;
	}


	public void setActiveShield(Shield activeShield) {
		this.activeShield = activeShield;
	}


	public Armour getActiveArmour() {
		return activeArmour;
	}


	public void setActiveArmour(Armour activeArmour) {
		this.activeArmour = activeArmour;
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
