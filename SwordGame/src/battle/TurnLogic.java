package battle;
import java.util.Random;

public class TurnLogic {
	
	public void attack(BattleManager battleManager, PlayerBattleState attacker, PlayerBattleState defender) {
		
		String attackerMove = attacker.getCurrMove();
		String defenderMove = attacker.getCurrMove();
		Random random = new Random();
		
		
		if(attackerMove.equals("SWING")) {
			
			// attack calculations
			double totalAttack = (attacker.getCurrStrength() + attacker.getActiveSword().getDamage() - defender.getActiveArmour().getProtection())
					* attacker.getCurrCharge();
			if(defenderMove.equals("JAB") && defender.isFaster()) {
				if(random.nextInt(5) == 0) {
					totalAttack = 0;
					//System.out.println("Quick jab fluster's "+attacker.getName()+". Swing missed...");
				}else {
					totalAttack *= 2;
					//System.out.println("Jab attack leave's "+defender.getName()+" vulnerable... Attack damage doubled!");
				}
			}else if(defenderMove.equals("BLOCK") && defender.isFaster()) {
				totalAttack = totalAttack / attacker.getCurrCharge() - (defender.getActiveShield().getResistance() - attacker.getActiveSword().getPiercing());
				totalAttack = Math.max(0, totalAttack);
				//System.out.println(defender.getName()+" quickly blocks, damage minimized.");
			}
			defender.setCurrHealth(defender.getCurrHealth() - (int) totalAttack);
			
			// remove charge
			attacker.setCurrCharge(1);
			
			// chance to remove defender charge
			if(!(defenderMove.equals("BLOCK") && defender.isFaster() && defender.getCurrCharge() == 0))
				if(random.nextBoolean()) {
					defender.setCurrCharge(1);
					//System.out.println("Blunt force throw's off "+defender.getName()+"'s concentration... reseting "+defender.getName()+"'s charge.");
				}
			
			// stamina and durability
			attacker.setCurrStamina(attacker.getCurrStamina() - attacker.getActiveSword().getStaminaUsage());
			attacker.setActiveSwordDurability(attacker.getActiveSwordDurability()-1);
			
		}else if(attackerMove.equals("JAB")) {
			
			// attack calculations
			double totalAttack = attacker.getCurrStrength() + attacker.getActiveSword().getDamage() - defender.getActiveArmour().getProtection();
			if(defenderMove.equals("BLOCK") && defender.isFaster()) {
				totalAttack = 0;
				//System.out.println(defender.getName()+" quickly blocks, damage nullified.");
			}
			defender.setCurrHealth(defender.getCurrHealth() - (int) totalAttack);
			
			// stamina and durability
			attacker.setCurrStamina(attacker.getCurrStamina() - attacker.getActiveSword().getStaminaUsage());
			attacker.setActiveSwordDurability(attacker.getActiveSwordDurability()-1);
			
		}else if(attackerMove.equals("BLOCK")) {
			
			attacker.setBlockCounter(attacker.getBlockCounter()+1);
			attacker.setCurrStamina(attacker.getCurrStamina() + attacker.getActiveShield().getStaminaRegain());
			attacker.setActiveShieldDurability(attacker.getActiveShieldDurability()-1);
			
		}else if(attackerMove.equals("CHARGE")) {
			
			attacker.increaseCurrCharge();
			attacker.setCurrStamina(attacker.getCurrStamina() + attacker.getActiveSword().getStaminaUsage());
			
		}else if(attackerMove.equals("SWAP_SWORDS")) {
			
			attacker.swapSwords();
			attacker.setCurrStamina(attacker.getCurrStamina() + attacker.getActiveSword().getStaminaUsage() + attacker.getInactiveSword().getStaminaUsage());
			
		}else if(attackerMove.equals("FORFEIT")) {
			// not set for multiplayer
			battleManager.deadPlayer = "A";
		}
		attacker.setCurrStamina(Math.min(attacker.getMaxStamina(), attacker.getCurrStamina()));
		
		if(attacker.getActiveSwordDurability() <= 0 && attacker.getInactiveSwordDurability() <= 0)
			battleManager.deadPlayer ="A";
	}
	
}
