package battle;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import entity.*;
import main.*;


public class BattleManager implements Runnable{

	PlayerBattleState pbsA;
	PlayerBattleState pbsB;
	boolean turnA;
	boolean battleOver;
	String deadPlayer;
	DrawBattle drawBattle;
	
	JFrame frame;
	
	String invalidMoveString;
	//private volatile boolean running = true;
	
	public BattleManager(JFrame frame, Player A, Player B) {
		this.battleOver = false;
		this.frame = frame;
		this.pbsA = new PlayerBattleState(A);
		this.pbsB = new PlayerBattleState(B);
		
		this.pbsA.setPlayerPos("A");
		this.pbsB.setPlayerPos("B");
		
		this.drawBattle = new DrawBattle(frame, this);
		new Thread(this).start();
	}
	

	public String battleLoop(PlayerBattleState A, PlayerBattleState B) {
		
		GetMove gm = new GetMove(this);
		DeathChecker dc = new DeathChecker(this);
		int turnNum = 1;
		
		while(!battleOver) {
			
			//System.out.println("**********************************************");
			//System.out.println("Turn "+turnNum+":");
			turnNum++;
			
			drawBattle.setMoveChosen(false);
			this.turnA = true;
			drawBattle.repaint();
			gm.chooseMove(A);
			drawBattle.setMoveChosen(false);
			
			//System.out.println(A.getName()+"'s current move: "+A.getCurrMove());
			
			this.turnA = false;
			drawBattle.repaint();
			gm.chooseMove(B);
			drawBattle.setMoveChosen(false);
			//System.out.println(B.getName()+"'s current Move: "+B.getCurrMove());
			

			SpeedCheck sc = new SpeedCheck();
			sc.speedComparison(A, B);
			TurnLogic tl = new TurnLogic();
			
			
			Thread drawAnimation = new Thread(new DrawAnimation(drawBattle));
			drawAnimation.start();
	
			try {
				drawAnimation.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//System.out.println("----------------------------------------------");
			
			if(sc.speedComparison(A, B) == A) {
				tl.attack(this, A, B);
				if(dc.checkDeath(A, B)!=null)
					return dc.checkDeath(A, B);
				drawBattle.repaint();
				tl.attack(this, B, A);
			} else {
				tl.attack(this, B, A);
				if(dc.checkDeath(A, B)!=null)
					return dc.checkDeath(A, B);
				drawBattle.repaint();
				tl.attack(this, A, B);
			}
			drawBattle.repaint();
			if(dc.checkDeath(A, B)!=null)
				return dc.checkDeath(A, B);
			
		}
		return null;
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

	public String getInvalidMoveString() {
		return this.invalidMoveString;
	}
	
	public boolean isBattleOver() {
		return this.battleOver;
	}
	
	public String getDeadPlayer() {
		return this.deadPlayer;
		
	}
	
	public void setDeadPlayer(String deadPlayer) {
		this.deadPlayer = deadPlayer;
		
	}


	@Override
	public void run() {
		
		this.invalidMoveString = "NONE";
		String result = battleLoop(pbsA, pbsB);
        
		drawBattle.repaint();
		
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Result: " + result); // result dies

        DrawMenu drawMenu = new DrawMenu(this.frame);
	}
}
