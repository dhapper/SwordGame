
public class PlayerBattleState {

	private String name;
	private int currHealth, currStrength, currSpeed, currStamina, currKnowledge;
	private Player player;
	private String currMove;
	
	public PlayerBattleState(Player player) {
		this.player = player;
		this.name = player.getNickName();
		this.currHealth = player.getHealth();
		this.currStrength = player.getStrength();
		this.currSpeed = player.getSpeed();
		this.currStamina = player.getStamina();
		this.currKnowledge = player.getKnowledge();
		this.currMove = null;
	
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

		
	
}
