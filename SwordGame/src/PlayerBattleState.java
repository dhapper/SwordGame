
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
		
		this.currMove = "NONE";
		
		this.currMove = null;
		
		this.maxHealth = player.getHealth();
		this.maxStamina = player.getStamina();
		
		this.faster = false;
		//this.swordChoice = 0;
	
	}

	protected boolean isFaster() {
		return faster;
	}

	protected void setFaster(boolean faster) {
		this.faster = faster;
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected int getCurrHealth() {
		return currHealth;
	}

	protected void setCurrHealth(int currHealth) {
		this.currHealth = currHealth;
	}

	protected int getCurrStrength() {
		return currStrength;
	}

	protected void setCurrStrength(int currStrength) {
		this.currStrength = currStrength;
	}

	protected int getCurrSpeed() {
		return currSpeed;
	}

	protected void setCurrSpeed(int currSpeed) {
		this.currSpeed = currSpeed;
	}

	protected int getCurrStamina() {
		return currStamina;
	}

	protected void setCurrStamina(int currStamina) {
		this.currStamina = currStamina;
	}

	protected int getCurrKnowledge() {
		return currKnowledge;
	}

	protected void setCurrKnowledge(int currKnowledge) {
		this.currKnowledge = currKnowledge;
	}

	protected Player getPlayer() {
		return player;
	}

	protected void setPlayer(Player player) {
		this.player = player;
	}

	protected String getCurrMove() {
		return currMove;
	}

	protected void setCurrMove(String currMove) {
		this.currMove = currMove;
	}

	public void addToCurrStamina(int stamina) {
		this.currStamina += stamina;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getSwordChoice() {
		return swordChoice;
	}

	public void setSwordChoice(int swordChoice) {
		this.swordChoice = swordChoice;
	}

	public int getMaxStamina() {
		return maxStamina;
	}

	public void setMaxStamina(int maxStamina) {
		this.maxStamina = maxStamina;
	}

	public double getCurrCharge() {
		return currCharge;
	}

	public void setCurrCharge(double currCharge) {
		this.currCharge = currCharge;
	}
		
	public void increaseCurrCharge() {
		if(this.currCharge == 1)
			this.currCharge += 0.5;
		else if (this.currCharge == 1.5)
			this.currCharge += 2;
	}

	public int getBlockCounter() {
		return blockCounter;
	}

	public void setBlockCounter(int blockCounter) {
		this.blockCounter = blockCounter;
	}
	
	public void increaseBlockCounter() {
		this.blockCounter++;
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
	
	
	
}
