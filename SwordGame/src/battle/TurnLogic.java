package battle;
import java.util.ArrayList;
import java.util.Random;

public class TurnLogic {
	
	
	
	public void attack(BattleManager battleManager, PlayerBattleState attacker, PlayerBattleState defender) {
		
		String attackerMove = attacker.getCurrMove();
		String defenderMove = defender.getCurrMove();
		Random random = new Random();
		
		battleManager.turnMessages.add("");
		
		
		if(attackerMove.equals("SWING")) {
			
			battleManager.turnMessages.add(attacker.getName()+" takes a big swing.");
			
			boolean swingMissed = false;
			
			// attack calculations
			double totalAttack = (attacker.getCurrStrength() + attacker.getActiveSword().getDamage() - defender.getActiveArmour().getProtection())
					* attacker.getCurrCharge();
			if(defenderMove.equals("JAB") && defender.isFaster()) {
				if(random.nextInt(5) == 0) {
					totalAttack = 0;
					swingMissed = true;
					
					battleManager.turnMessages.add("Quick jab fluster's "+attacker.getName()+". Swing missed...");
				}else {
					totalAttack *= 2;
					
					battleManager.turnMessages.add("Jab attack leave's "+defender.getName()+" vulnerable... Attack damage doubled!");
				}
			}else if(defenderMove.equals("BLOCK") && defender.isFaster()) {
				totalAttack = totalAttack / attacker.getCurrCharge() - (defender.getActiveShield().getResistance() - attacker.getActiveSword().getPiercing());
				totalAttack = Math.max(0, totalAttack);

				battleManager.turnMessages.add(defender.getName()+" quickly blocks, damage minimized.");
			}
			defender.setCurrHealth(defender.getCurrHealth() - (int) totalAttack);
			
			// remove charge
			attacker.setCurrCharge(1);
			
			// chance to remove defender charge
			if(!((defenderMove.equals("BLOCK") && defender.isFaster()) || defender.getCurrCharge() == 0) && !swingMissed)
				if(random.nextInt(3) == 0) {
					defender.setCurrCharge(1);
					
					battleManager.turnMessages.add("Blunt force throw's off "+defender.getName()+"'s concentration... reseting "+defender.getName()+"'s charge.");
				}
			
			if(!swingMissed)
				attacker.setBlockCounter(0);
			
			// stamina and durability
			attacker.setCurrStamina(attacker.getCurrStamina() - attacker.getActiveSword().getStaminaUsage());
			attacker.setActiveSwordDurability(attacker.getActiveSwordDurability()-1);
			
			battleManager.turnMessages.add("- "+totalAttack+" HEALTH");
			battleManager.turnMessages.add("- "+attacker.getActiveSword().getStaminaUsage()+" STAMINA");
			
		}else if(attackerMove.equals("JAB")) {
			
			battleManager.turnMessages.add(attacker.getName()+" takes a quick jab.");
			
			// attack calculations
			double totalAttack = attacker.getCurrStrength() + attacker.getActiveSword().getDamage() - defender.getActiveArmour().getProtection();
			if(defenderMove.equals("BLOCK") && defender.isFaster()) {
				totalAttack = 0;
				
				battleManager.turnMessages.add(defender.getName()+" quickly blocks, damage nullified.");
			}
			defender.setCurrHealth(defender.getCurrHealth() - (int) totalAttack);
			
			// stamina and durability
			attacker.setCurrStamina(attacker.getCurrStamina() - attacker.getActiveSword().getStaminaUsage());
			attacker.setActiveSwordDurability(attacker.getActiveSwordDurability()-1);
			
			battleManager.turnMessages.add("- "+totalAttack+" HEALTH");
			battleManager.turnMessages.add("- "+attacker.getActiveSword().getStaminaUsage()+" STAMINA");
			
		}else if(attackerMove.equals("BLOCK")) {
			
			int staminaGained = Math.min(attacker.getMaxStamina() - attacker.getCurrStamina(), attacker.getActiveShield().getStaminaRegain());
			
			attacker.setBlockCounter(attacker.getBlockCounter()+1);
			attacker.setCurrStamina(attacker.getCurrStamina() + staminaGained);
			attacker.setActiveShieldDurability(attacker.getActiveShieldDurability()-1);
			
			battleManager.turnMessages.add(attacker.getName()+" pulls out his shield.");
			battleManager.turnMessages.add("+ "+staminaGained+" STAMINA");
			
		}else if(attackerMove.equals("CHARGE")) {
			
			int staminaGained = Math.min(attacker.getMaxStamina() - attacker.getCurrStamina(), attacker.getActiveSword().getStaminaUsage());
			
			
			if(attacker.getCurrCharge() == 1)
				attacker.setCurrCharge(battleManager.chargeLevel1);
			else
				attacker.setCurrCharge(battleManager.chargeLevel2);
			
			attacker.setCurrStamina(attacker.getCurrStamina() + attacker.getActiveSword().getStaminaUsage());
			
			battleManager.turnMessages.add(attacker.getName()+" charges up an attack...");
			battleManager.turnMessages.add("+ "+attacker.getActiveSword().getStaminaUsage()+" STAMINA");
			
		}else if(attackerMove.equals("SWAP_SWORDS")) {
			
			int staminaGained = Math.min(attacker.getMaxStamina() - attacker.getCurrStamina(), attacker.getActiveSword().getStaminaUsage() + attacker.getActiveSword().getStaminaUsage());
			
			attacker.swapSwords();
			attacker.setCurrStamina(attacker.getCurrStamina() + staminaGained);
			
			battleManager.turnMessages.add(attacker.getName()+" unsheathes "+attacker.getInactiveSword().getName()+".");
			battleManager.turnMessages.add("+ "+staminaGained+" STAMINA");
			
		}else if(attackerMove.equals("FORFEIT")) {
			
			battleManager.turnMessages.add(attacker.getName()+" forfeits.");
			// not set for multiplayer
			battleManager.deadPlayer = "A";
		}
		attacker.setCurrStamina(Math.min(attacker.getMaxStamina(), attacker.getCurrStamina()));
		
		if(attacker.getActiveSwordDurability() <= 0 && attacker.getInactiveSwordDurability() <= 0) {
			battleManager.turnMessages.add(attacker.getName()+" has no remaining swords... forfeits battle.");
			battleManager.deadPlayer ="A";
		}
		
	}
	
}
