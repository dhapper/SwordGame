package battle;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GetMove {
	
	BattleManager battleManager;
	
	public GetMove(BattleManager battleManager) {
		this.battleManager = battleManager;
	}

	public void chooseMove(PlayerBattleState player) {
		
		battleManager.invalidMoveString = "NONE";
		while(true) {
			
			String nextMove = battleManager.drawBattle.getNextMove();
			 if (battleManager.drawBattle.isMoveChosen() && checkMoveValidity(player, nextMove)) {
			     player.setCurrMove(nextMove);
			     return;
			 }
			 //battleManager.drawBattle.repaint();
			 try {
					TimeUnit.MILLISECONDS.sleep(10);
			 } catch (InterruptedException e) {
					e.printStackTrace();
			 }
		}
	}
	
	String prev;
	public boolean checkMoveValidity(PlayerBattleState player, String nextMove) {
		boolean isValid = true;
		//boolean a = true, b = true, c = true, d = true, e = true, f = true;
		
		if (nextMove == null)	// Add null check and handle "NONE" case
	        return false;
		battleManager.invalidMoveString = "NONE";
		if(nextMove.equals("SWING") && player.getPlayer().getInventory().getActiveSword().getStaminaUsage() > player.getCurrStamina()) {
        	isValid = false;
        	battleManager.invalidMoveString = "NOT ENOUGH STAMINA";
        	if(!prev.equals(battleManager.invalidMoveString))
        		battleManager.drawBattle.repaint();
        }else if(nextMove.equals("JAB") && player.getPlayer().getInventory().getActiveSword().getStaminaUsage() > player.getCurrStamina()) {
        	isValid = false;
        	battleManager.invalidMoveString = "NOT ENOUGH STAMINA";
        	if(!prev.equals(battleManager.invalidMoveString))
        		battleManager.drawBattle.repaint();
        }else if(nextMove.equals("BLOCK") && player.getBlockCounter()>2) {
        	isValid = false;
        	battleManager.invalidMoveString = "CAN'T BLOCK 4 TIMES IN A ROW";
        	if(!prev.equals(battleManager.invalidMoveString))
        		battleManager.drawBattle.repaint();
        }else if(nextMove.equals("CHARGE") && player.getCurrCharge() == 3.5) {
        	isValid = false;
        	battleManager.invalidMoveString = "CHARGE MULTIPLIER ALREADY AT MAX";
        	if(!prev.equals(battleManager.invalidMoveString))
        		battleManager.drawBattle.repaint();
        }else if(nextMove.equals("SWAP_SWORDS") && player.getInactiveSwordDurability() <= 0) {
        	isValid = false;
        	battleManager.invalidMoveString = "OTHER SWORD IS BROKEN";
        	if(!prev.equals(battleManager.invalidMoveString))
        		battleManager.drawBattle.repaint();
        }else if((nextMove.equals("SWING") || nextMove.equals("JAB")) && player.getActiveSwordDurability() <= 0) {
        	isValid = false;
        	battleManager.invalidMoveString = "CURRENT SWORD IS BROKEN, SWAP SWORDS";
        	if(!prev.equals(battleManager.invalidMoveString))
        		battleManager.drawBattle.repaint();
        }
		prev = battleManager.invalidMoveString;
		return isValid;
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
