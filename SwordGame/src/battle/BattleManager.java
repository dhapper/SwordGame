package battle;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import battle.graphics.DrawAnimation;
import battle.graphics.DrawBattle;
import entity.Player;
import main.DrawMenu;


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
	
	ArrayList<String> turnMessages;
	
	int turnNum;
	
	int blockMax = 3;
	double chargeLevel1 = 1.5;
	double chargeLevel2 = 3.5;
	
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
		
		GetMove getMove = new GetMove(this);
		DeathChecker deathChecker = new DeathChecker(this);
		turnNum = 1;
		
		while(!battleOver) {
			
			this.turnMessages = new ArrayList<String>();
			
			//System.out.println("**********************************************");
			turnMessages.add("Turn "+turnNum+":");
			turnNum++;
			
			drawBattle.setMoveChosen(false);
			this.turnA = true;
			drawBattle.repaint();
			getMove.chooseMove(A);
			drawBattle.setMoveChosen(false);
			
			//System.out.println(A.getName()+"'s current move: "+A.getCurrMove());
			
			//////////////////////////////////////////////
			//this.turnA = false;
			//drawBattle.repaint();
			getMove.botChooseMove(B);
			//drawBattle.setMoveChosen(false);
			//System.out.println(B.getName()+"'s current Move: "+B.getCurrMove());
			

			SpeedCheck sc = new SpeedCheck();
			sc.speedComparison(A, B);
			TurnLogic tl = new TurnLogic();
			
			if(sc.speedComparison(A, B) == A) {
				
				tl.attack(this, A, B);
				
				drawAnimation(1);
				
				if(deathChecker.checkDeath(A, B)!=null)
					return deathChecker.checkDeath(A, B);
				
				drawBattle.repaint();
				tl.attack(this, B, A);
				
				drawAnimation(2);
				
			} else {
				
				tl.attack(this, B, A);
				
				drawAnimation(1);
				
				if(deathChecker.checkDeath(A, B)!=null)
					return deathChecker.checkDeath(A, B);
				
				drawBattle.repaint();
				tl.attack(this, A, B);
				
				drawAnimation(2);
				
			}
			
			drawBattle.repaint();
			if(deathChecker.checkDeath(A, B)!=null)
				return deathChecker.checkDeath(A, B);
			
			/*for(int i = 0; i < turnMessages.size(); i++) {
				System.out.println(turnMessages.get(i));	
			}
			System.out.println();*/
			
		}
		
		return null;
	}
	
	
	public void drawAnimation(int moveNum) {
		Thread drawAnimation = new Thread(new DrawAnimation(drawBattle, moveNum));
		drawAnimation.start();
		
		try {
			drawAnimation.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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

	public ArrayList<String> getTurnMessages() {
		return this.turnMessages;
	}
	
	public int getTurnNum() {
		return this.turnNum;
	}

	public int getBlockMax() {
		return blockMax;
	}

	public double getChargeLevel1() {
		return chargeLevel1;
	}

	public double getChargeLevel2() {
		return chargeLevel2;
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
