
public class Main {

	public static void main(String[] args) {
		
		//login, return username
		String username = "dhapper";
		
		
		//access mongodb, get attributes
		UseMongoDB mongo = new UseMongoDB();
		Player player = mongo.getPlayer(username);
		Player enemy = mongo.getPlayer("test");
		
		player.getInventory().swapActiveSword();
		
		//player.getInventory().getActiveSword().displayStats();
		//player.getInventory().getActiveShield().displayStats();
		//player.getInventory().getActiveArmour().displayStats();
		
		
		BattleLogic bl = new BattleLogic();
		
		
		bl.battleManager(player, enemy);
		
	}

}
