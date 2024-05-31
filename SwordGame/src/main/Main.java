package main;
import battle.BattleManager;
import entity.Player;
import playercustomization.DrawPlayerCustomizer;
import utilz.UseMongoDB;

public class Main {

	public static void main(String[] args) {
		
		//login, return username
		String username = "dhapper";
		
		
		UseMongoDB mongo = new UseMongoDB();
		Player player = mongo.getPlayer("dhapper");
		Player enemy = mongo.getPlayer("test");
		
		System.out.println(enemy.getModel()[2]);
		
		MainFrame mf = new MainFrame();
		//DrawMenu dm = new DrawMenu(mf);
		//DrawPlayerCustomizer dpc = new DrawPlayerCustomizer(player, mf);

		BattleManager bl = new BattleManager(mf, player, enemy);
		
		
		
	}

}
