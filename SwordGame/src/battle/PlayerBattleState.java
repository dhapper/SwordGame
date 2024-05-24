package battle;
import entity.Player;
import entity.Sword;

public class PlayerBattleState {

	private String name;
	private int currHealth, currStrength, currSpeed, currStamina, currKnowledge;
	private double currCharge;
	private Player player;
	private String currMove;
	private boolean faster;
	private int maxHealth, maxStamina;
	private int blockCounter;
	private int activeSwordDurability, maxActiveSwordDurability, inactiveSwordDurability, maxInactiveSwordDurability;
	private int activeShieldDurability, maxActiveShieldDurability;
	private Sword activeSword, inactiveSword;
	
	private int swordChoice;
	
	public PlayerBattleState(Player player) {
		this.player = player;
		this.name = player.getNickname();
		this.currHealth = player.getHealth();
		this.currStrength = player.getStrength();
		this.currSpeed = player.getSpeed();
		this.currStamina = player.getStamina();
		this.currKnowledge = player.getKnowledge();
		this.currCharge = 1;
		this.blockCounter = 0;
		this.activeSwordDurability = player.getInventory().getActiveSword().getDurability();
		this.maxActiveSwordDurability = this.activeSwordDurability;
		this.inactiveSwordDurability = player.getInventory().getInactiveSword().getDurability();
		this.maxInactiveSwordDurability = this.inactiveSwordDurability;
		this.activeShieldDurability = player.getInventory().getActiveShield().getDurability();
		this.maxActiveShieldDurability = this.activeShieldDurability;
		this.activeSword = player.getInventory().getActiveSword();
		this.inactiveSword = player.getInventory().getInactiveSword();
		
		this.currMove = "NONE";
		
		this.currMove = null;
		
		this.maxHealth = player.getHealth();
		this.maxStamina = player.getStamina();
		
		this.faster = false;
		//this.swordChoice = 0;
	
	}
	
	public void swapSwords() {
		Sword tempSword = activeSword;
		activeSword = inactiveSword;
		inactiveSword = tempSword;
		
		int tempDur = activeSwordDurability;
		activeSwordDurability = inactiveSwordDurability;
		inactiveSwordDurability = tempDur;
		
		int tempMaxDur = activeSwordDurability;
		maxActiveSwordDurability = maxInactiveSwordDurability;
		maxInactiveSwordDurability = tempMaxDur;
	}

	

	public void addToCurrStamina(int stamina) {
		this.currStamina += stamina;
	}
	
	
	public void increaseCurrCharge() {
		if(this.currCharge == 1)
			this.currCharge += 0.5;
		else if (this.currCharge == 1.5)
			this.currCharge += 2;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCurrHealth() {
		return currHealth;
	}

	public void setCurrHealth(int currHealth) {
		this.currHealth = currHealth;
	}

	public int getCurrStrength() {
		return currStrength;
	}

	public void setCurrStrength(int currStrength) {
		this.currStrength = currStrength;
	}

	public int getCurrSpeed() {
		return currSpeed;
	}

	public void setCurrSpeed(int currSpeed) {
		this.currSpeed = currSpeed;
	}

	public int getCurrStamina() {
		return currStamina;
	}

	public void setCurrStamina(int currStamina) {
		this.currStamina = currStamina;
	}

	public int getCurrKnowledge() {
		return currKnowledge;
	}

	public void setCurrKnowledge(int currKnowledge) {
		this.currKnowledge = currKnowledge;
	}

	public double getCurrCharge() {
		return currCharge;
	}

	public void setCurrCharge(double currCharge) {
		this.currCharge = currCharge;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public String getCurrMove() {
		return currMove;
	}

	public void setCurrMove(String currMove) {
		this.currMove = currMove;
	}

	public boolean isFaster() {
		return faster;
	}

	public void setFaster(boolean faster) {
		this.faster = faster;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getMaxStamina() {
		return maxStamina;
	}

	public void setMaxStamina(int maxStamina) {
		this.maxStamina = maxStamina;
	}

	public int getBlockCounter() {
		return blockCounter;
	}

	public void setBlockCounter(int blockCounter) {
		this.blockCounter = blockCounter;
	}

	public int getActiveSwordDurability() {
		return activeSwordDurability;
	}

	public void setActiveSwordDurability(int activeSwordDurability) {
		this.activeSwordDurability = activeSwordDurability;
	}

	public int getMaxActiveSwordDurability() {
		return maxActiveSwordDurability;
	}

	public void setMaxActiveSwordDurability(int maxActiveSwordDurability) {
		this.maxActiveSwordDurability = maxActiveSwordDurability;
	}

	public int getInactiveSwordDurability() {
		return inactiveSwordDurability;
	}

	public void setInactiveSwordDurability(int inactiveSwordDurability) {
		this.inactiveSwordDurability = inactiveSwordDurability;
	}

	public int getMaxInactiveSwordDurability() {
		return maxInactiveSwordDurability;
	}

	public void setMaxInactiveSwordDurability(int maxInactiveSwordDurability) {
		this.maxInactiveSwordDurability = maxInactiveSwordDurability;
	}

	public int getActiveShieldDurability() {
		return activeShieldDurability;
	}

	public void setActiveShieldDurability(int activeShieldDurability) {
		this.activeShieldDurability = activeShieldDurability;
	}

	public int getMaxActiveShieldDurability() {
		return maxActiveShieldDurability;
	}

	public void setMaxActiveShieldDurability(int maxActiveShieldDurability) {
		this.maxActiveShieldDurability = maxActiveShieldDurability;
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

	public int getSwordChoice() {
		return swordChoice;
	}

	public void setSwordChoice(int swordChoice) {
		this.swordChoice = swordChoice;
	}

	
	
}
