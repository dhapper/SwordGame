package battle;

public class DeathChecker {
	
	BattleManager battleManager;
	
	public DeathChecker(BattleManager battleManager) {
		this.battleManager = battleManager;
	}
	
	public String checkDeath(PlayerBattleState A, PlayerBattleState B) {
		if(A.getCurrHealth()<=0 && B.getCurrHealth()<=0) {
			battleManager.battleOver = true;
			battleManager.deadPlayer = "TIE";
			//battleManager.drawBattle.repaint();
			return "TIE";
		} else if(A.getCurrHealth()<=0) {
			battleManager.battleOver = true;
			battleManager.deadPlayer = "A";
			//battleManager.drawBattle.repaint();
			return "A";
		} else if(B.getCurrHealth()<=0) {

			battleManager.battleOver = true;
			battleManager.deadPlayer = "B";
			//battleManager.drawBattle.repaint();
			return "B";
		}
		return null;
	}
}
