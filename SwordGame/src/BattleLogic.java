import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public class BattleLogic implements Runnable{

	PlayerBattleState pbsA;
	PlayerBattleState pbsB;
	boolean turnA;
	boolean battleOver;
	String deadPlayer;
	DrawBattle drawBattle;
	
	JFrame frame;
	
	private volatile boolean running = true;
	
	public BattleLogic(JFrame frame, Player A, Player B) {
		this.battleOver = false;
		this.pbsA = new PlayerBattleState(A);
		this.pbsB = new PlayerBattleState(B);
		this.frame = frame;
		
		//frame.getContentPane().removeAll();
		//frame.revalidate();
		//frame.repaint();
		
		new Thread(this).start();
		
		/*this.drawBattle = new DrawBattle(frame, this);
		this.drawBattle.repaint();
		System.out.println("init'd DrawBattle");
		
		String result = battleLoop(pbsA, pbsB);
		drawBattle.repaint();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Result: "+result);	// result dies
		
		DrawMenu drawMenu = new DrawMenu(frame);*/
	}
	

	public String battleLoop(PlayerBattleState A, PlayerBattleState B) {
		int turnNum = 1;
		
		while(!battleOver) {
			
			this.drawBattle = new DrawBattle(frame, this);
			
			System.out.println("**********************************************");
			System.out.println("**********************************************");
			System.out.println("Turn "+turnNum+":");
			turnNum++;
			
			drawBattle.setMoveChosen(false);
			this.turnA = true;
			drawBattle.repaint();
			chooseMove(A);
			drawBattle.setMoveChosen(false);
			
			System.out.println(A.getName()+"'s current move: "+A.getCurrMove());
			
			this.turnA = false;
			drawBattle.repaint();
			chooseMove(B);
			drawBattle.setMoveChosen(false);
			System.out.println(B.getName()+"'s current Move: "+B.getCurrMove());
			
			System.out.println("----------------------------------------------");

			TurnLogic tl = new TurnLogic();
			
			if(tl.speedComparison(A, B) == A) {
				tl.attack(A, B);
				if(checkDeath(A, B)!=null)
					return checkDeath(A, B);
				drawBattle.repaint();
				tl.attack(B, A);
			} else {
				tl.attack(B, A);
				if(checkDeath(A, B)!=null)
					return checkDeath(A, B);
				drawBattle.repaint();
				tl.attack(A, B);
			}
			drawBattle.repaint();
			if(checkDeath(A, B)!=null)
				return checkDeath(A, B);
			
		}
		return null;
	}
	
	public String checkDeath(PlayerBattleState A, PlayerBattleState B) {
		if(A.getCurrHealth()<=0 && B.getCurrHealth()<=0) {
			this.battleOver = true;
			this.deadPlayer = "TIE";
			drawBattle.repaint();
			return "TIE";
		} else if(A.getCurrHealth()<=0) {
			this.battleOver = true;
			this.deadPlayer = "A";
			drawBattle.repaint();
			return "A";
		} else if(B.getCurrHealth()<=0) {

			this.battleOver = true;
			this.deadPlayer = "B";
			drawBattle.repaint();
			return "B";
		}
		System.out.println("NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
		return null;
	}
	
	public void chooseMove(PlayerBattleState player) {
		while(true) {
			String nextMove = this.drawBattle.getNextMove();
			 if (drawBattle.isMoveChosen() && checkMoveValidity(player, nextMove)) {
			     player.setCurrMove(nextMove);
			     return;
			 }
			 try {
					TimeUnit.MILLISECONDS.sleep(10);
			 } catch (InterruptedException e) {
					e.printStackTrace();
			 }
		}
	}
	
	public boolean checkMoveValidity(PlayerBattleState player, String nextMove) {
		boolean isValid = true;
		
		
		if (nextMove == null)	// Add null check and handle "NONE" case
	        return false;
		
		if(nextMove.equals("SWING") && player.getPlayer().getInventory().getActiveSword().getStaminaUsage() > player.getCurrStamina()) {
        	isValid = false;
        	System.out.println("Not enough stamina!");
        } else if(nextMove.equals("JAB") && player.getPlayer().getInventory().getActiveSword().getStaminaUsage()/2 > player.getCurrStamina()) {
        	isValid = false;
        	System.out.println("Not enough stamina!");
        } else if(nextMove.equals("BLOCK") && player.getBlockCounter()>2) {
        	isValid = false;
        	System.out.println("Can't block 3 times in a row!");
        } else if(nextMove.equals("CHARGE") && player.getCurrCharge() == 1.75) {
        	System.out.println("Already at max charge, attempts to charge anyways...");
        }
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

	public PlayerBattleState getPbsA() {
		return this.pbsA;
	}

	public PlayerBattleState getPbsB() {
		return this.pbsB;
	}

	public boolean isTurnA() {
		return this.turnA;
	}

	public void setTurnA(boolean turnA) {
		this.turnA = turnA;
	}
	
	public boolean isBattleOver() {
		return this.battleOver;
	}
	
	public String getDeadPlayer() {
		return this.deadPlayer;
		
	}


	@Override
	public void run() {
		String result = battleLoop(pbsA, pbsB);
        drawBattle.repaint();

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Result: " + result); // result dies

        // Assuming DrawMenu is part of your GUI flow
        DrawMenu drawMenu = new DrawMenu(this.frame);
		
	}
}
