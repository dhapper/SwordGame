import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class BattleLogic {

	public void battleManager(Player A, Player B) {
		PlayerBattleState pbsA = new PlayerBattleState(A);
		PlayerBattleState pbsB = new PlayerBattleState(B);
		
		
		String result = battleLoop(pbsA, pbsB);
		
		System.out.println("Result: "+result);	// result dies
	}
	
	public String battleLoop(PlayerBattleState A, PlayerBattleState B) {
		boolean battleOver = false;
		int turnNum = 1;
		
		DrawBattle drawBattle = new DrawBattle(A, B);
		
		while(!battleOver) {
			
			
			System.out.println("**********************************************");
			System.out.println("**********************************************");
			System.out.println("Turn "+turnNum+":");
			turnNum++;
			
			
			
			promptUser(A);
			System.out.println(A.getName()+"'s current move: "+A.getCurrMove());
			promptUser(B);
			System.out.println(B.getName()+"'s current Move: "+B.getCurrMove());
			
			System.out.println("----------------------------------------------");

			TurnLogic tl = new TurnLogic();
			
			if(tl.speedComparison(A, B) == A) {
				tl.attack(A, B);
				if(checkDeath(A, B)!=null)
					return checkDeath(A, B);
				tl.attack(B, A);
			} else {
				tl.attack(B, A);
				if(checkDeath(A, B)!=null)
					return checkDeath(A, B);
				tl.attack(A, B);
			}
			
			drawBattle.repaint();
			
		}
		return checkDeath(A, B);
	}
	
	public String checkDeath(PlayerBattleState A, PlayerBattleState B) {
		if(A.getCurrHealth()<=0 && B.getCurrHealth()<=0) {
			return "TIE";
		} else if(A.getCurrHealth()<=0) {
			return "A";
		} else if(B.getCurrHealth()<=0) {
			return "B";
		}
		return null;
	}
	
	public void promptUser(PlayerBattleState player) {
		Scanner scanner = new Scanner(System.in);
        String nextMove;
        boolean notMeetingCriteria;
        do {
        	notMeetingCriteria = true;
            System.out.println("Enter your next move "+ player.getName() +":");
            nextMove = scanner.nextLine().toUpperCase();
            if (!isValidMove(nextMove)) {
                System.out.println("Invalid move!");
            } else if(nextMove.equals("SWING") && player.getPlayer().getInventory().getActiveSword().getStaminaUsage() > player.getCurrStamina()) {
            	notMeetingCriteria = false;
            	System.out.println("Not enough stamina!");
            } else if(nextMove.equals("JAB") && player.getPlayer().getInventory().getActiveSword().getStaminaUsage()/2 > player.getCurrStamina()) {
            	notMeetingCriteria = false;
            	System.out.println("Not enough stamina!");
            } else if(nextMove.equals("BLOCK") && player.getBlockCounter()>2) {
            	notMeetingCriteria = false;
            	System.out.println("Can't block 3 times in a row!");
            } else if (nextMove.equals("CHARGE") && player.getCurrCharge() == 1.75) {
            	System.out.println("Already at max charge, attempts to charge anyways...");
            }
        } while (!isValidMove(nextMove) || !notMeetingCriteria );
        player.setCurrMove(nextMove);
	}
	
	public boolean isValidMove(String move) {
        return move.equals("SWING") || move.equals("JAB") || move.equals("BLOCK") || move.equals("CHARGE");
    }
	
}
