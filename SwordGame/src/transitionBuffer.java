import javax.swing.JFrame;

public class transitionBuffer {
	public transitionBuffer(JFrame frame){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UseMongoDB mongo = new UseMongoDB();
		Player player = mongo.getPlayer("dhapper");
		Player enemy = mongo.getPlayer("test");
		BattleLogic bl = new BattleLogic(frame, player, enemy);
	}
}
