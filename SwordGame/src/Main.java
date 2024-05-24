
public class Main {

	public static void main(String[] args) {
		
		//login, return username
		String username = "dhapper";
		
		
		UseMongoDB mongo = new UseMongoDB();
		Player player = mongo.getPlayer("dhapper");
		Player enemy = mongo.getPlayer("test");
		
		
		
		MainFrame mf = new MainFrame();
		//DrawMenu dm = new DrawMenu();
		BattleLogic bl = new BattleLogic(mf, player, enemy);
		
	}

}
