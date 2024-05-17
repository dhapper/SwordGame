import java.util.ArrayList;

public class Inventory {

	Player player;
	ArrayList<Sword> swords = new ArrayList<>();
	ArrayList<Shield> shields = new ArrayList<>();
	ArrayList<Armour> armour = new ArrayList<>();
	Sword activeSword, inactiveSword;
	Shield activeShield;
	Armour activeArmour;
	
	public Inventory(Player player) {
		this.player = player;
		
		UseMongoDB mongo = new UseMongoDB();
		//add all swords connected to username in DB
		this.swords.add(mongo.getSword("twig"));
        this.swords.add(mongo.getSword("big stick"));
        this.shields.add(mongo.getShield("cardboard"));
        this.armour.add(mongo.getArmour("shirt"));
        
        
        //set active and inActive from DB
        this.activeSword = swords.get(0);
        this.inactiveSword = swords.get(1);
        
        this.activeShield = shields.get(0);
        this.activeArmour = armour.get(0);
        
		//this.shield = player.getActiveSheild();
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
	
	
	
}
