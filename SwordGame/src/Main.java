
public class Main {

	public static void main(String[] args) {
		
		//login, return username
		
		//access mongodb, get attributes
		// (String nickName, int health, int strength, int speed, int stamina, int knowledge)
		Player a = new Player("a", 50, 20, 100, 100, 100);
		Player b = new Player("b", 50, 20, 100, 100, 100);
		
		BattleLogic bl = new BattleLogic();
		
		PlayerBattleState aa = new PlayerBattleState(a);
		PlayerBattleState bb = new PlayerBattleState(b);
		
		bl.battleManager(aa, bb);
		
	}

}
