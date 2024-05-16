import java.util.Scanner;

public class BattleLogic {

	
	
	public void battleManager(PlayerBattleState A, PlayerBattleState B) {
		String result = battleLoop(A, B);
		System.out.println("Result: "+result);	// result dies
	}
	
	public String battleLoop(PlayerBattleState A, PlayerBattleState B) {
		boolean battleOver = false;
		int turnNum = 1;
		
		
		while(!battleOver) {
			
			System.out.println("***********");
			System.out.println("Turn "+turnNum+":");
			turnNum++;
			
			A.setCurrMove(promptUser(A));
			//System.out.println("fff: "+A.getCurrMove());
			B.setCurrMove(promptUser(B));
			//System.out.println("fff: "+B.getCurrMove());

			TurnLogic tl = new TurnLogic();
			
			if(tl.speedComparison(A, B) == A) {
				tl.swing(A, B);
				if(checkDeath(A, B)!=null)
					return checkDeath(A, B);
				checkDeath(A, B);
				tl.swing(B, A);
			} else {
				tl.swing(B, A);
				if(checkDeath(A, B)!=null)
					return checkDeath(A, B);
				tl.swing(A, B);
			}
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
	
	public String promptUser(PlayerBattleState player) {
		Scanner scanner = new Scanner(System.in);
        String nextMove;
        do {
            System.out.println("Enter your next move "+ player.getName() +":");
            nextMove = scanner.nextLine().toUpperCase();
            if (!isValidMove(nextMove)) {
                System.out.println("Invalid move!");
            }
        } while (!isValidMove(nextMove));
        //scanner.close();
        return nextMove;
	}
	
	public static boolean isValidMove(String move) {
        return move.equals("SWING") || move.equals("JAB") || move.equals("BLOCK");
    }
	
}
