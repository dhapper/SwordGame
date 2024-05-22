
public class Main {

	public static void main(String[] args) {
		
		//login, return username
		String username = "dhapper";
		
		
		UseMongoDB mongo = new UseMongoDB();
		Player player = mongo.getPlayer(username);
		Player enemy = mongo.getPlayer("test");
		
		/*System.out.println(player.getInventory().getSwords().get(0).getName());
		System.out.println(player.getInventory().getSwords().get(1).getName());
	
		System.out.println(player.getInventory().getArmoury().get(0).getName());
		System.out.println(player.getInventory().getArmoury().get(1).getName());
		
		System.out.println(player.getInventory().getShields().get(0).getName());
		System.out.println(player.getInventory().getShields().get(1).getName());
		
		System.out.println();
		System.out.println(player.getInventory().getActiveSword().getName());
		System.out.println(player.getInventory().getInactiveSword().getName());
		System.out.println(player.getInventory().getActiveShield().getName());
		System.out.println(player.getInventory().getActiveArmour().getName());*/
		
		BattleLogic bl = new BattleLogic(player, enemy);

		
	}

}
