package battle.bots;

import java.util.Random;

import battle.BattleManager;
import battle.PlayerBattleState;
import battle.graphics.*;

public class DefaultBot {

	BattleManager battleManager;
	
	public DefaultBot(BattleManager battleManager) {
		this.battleManager = battleManager;
	}
	
	public String processMoves() {
		
		PlayerBattleState bot = battleManager.getPbsB();
		PlayerBattleState player = battleManager.getPbsA();
		int stam = bot.getCurrStamina();
		
		double swingChanceMult = 1;
		double jabChanceMult = 1;
		double blockChanceMult = 1;
		double chargeChanceMult = 1;
		double swapChanceMult = 1;
		
		swapChanceMult /= 20;
		
		if(battleManager.getTurnNum() == 1) {
			swapChanceMult *= 0;
		}
		
		if(player.getCurrStamina() < player.getActiveSword().getStaminaUsage()) {
			chargeChanceMult *= 3;
		}
		
		if(player.getCurrCharge() == battleManager.getChargeLevel1()) {
			swingChanceMult *= 3;
			blockChanceMult /= 10;
		}
		
		if(player.getCurrCharge() == battleManager.getChargeLevel2()) {
			if(bot.getCurrSpeed() > player.getCurrSpeed())
				swingChanceMult *= 3;
			blockChanceMult *= 10;
		}
		
		if(bot.getBlockCounter() >= 1) {
			blockChanceMult /= 2;
			swingChanceMult *= 2;
		}
		
		if(bot.getBlockCounter() == 3) {
			swingChanceMult *= 3;
		}
		
		if(bot.getActiveSwordDurability() < 4) {
			swapChanceMult *= 3;
		}
		
		if(bot.getCurrStamina() * 3 < bot.getMaxStamina()) {
			swapChanceMult *= 3;
		}
		
		if(player.getCurrHealth() * 6 < player.getMaxHealth()) {
			jabChanceMult *= 10;
		}
		
		return oddsManager(swingChanceMult, jabChanceMult, blockChanceMult, chargeChanceMult, swapChanceMult);
		
	}
	
	public String oddsManager(double swingChanceMult, double jabChanceMult, double blockChanceMult, double chargeChanceMult, double swapChanceMult) {
		
		int base = 10;
		double swingChance = base * swingChanceMult;
		double jabChance = base * jabChanceMult;
		double blockChance = base * blockChanceMult;
		double chargeChance = base * chargeChanceMult;
		double swapChance = base * swapChanceMult;
		
		double totalChance = swingChance + blockChance + chargeChance + swapChance;
		
		Random random = new Random();
		double roll = random.nextDouble() * totalChance;
		
		String move = "NONE";
		if(roll < swingChance)
			move = "SWING";
		else if(roll < base + jabChance)
			move = "JAB";
		else if(roll < swingChance + blockChance)
			move = "BLOCK";
		else if(roll < swingChance + blockChance + chargeChance)
			move = "CHARGE";
		else if(roll < swingChance + blockChance + chargeChance + swapChance)
			move = "SWAP_SWORDS";
		
		
		/*System.out.println("\n"+move);
		System.out.println(totalChance);
		System.out.println(roll);
		System.out.println("s  "+swingChance);
		System.out.println("j  "+jabChance);
		System.out.println("b  "+blockChance);
		System.out.println("c  "+chargeChance);
		System.out.println("ss "+swapChance);*/
		return move;
	}
	
}
