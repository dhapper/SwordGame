import java.util.Random;

public class TurnLogic {
	
	public void attack(PlayerBattleState attacker, PlayerBattleState defender) {
		
		if(!attacker.getCurrMove().equals("BLOCK")) {
			
			Random random = new Random();
	        double randomMultiplier = 0.9 + (random.nextDouble() * 0.2);
			double baseAttack = (attacker.getCurrStrength() * attacker.getPlayer().getInventory().getActiveSword().getDamage() /10)
					- (defender.getPlayer().getInventory().getActiveArmour().getProtection());
	        int totalAttack = (int) (baseAttack * randomMultiplier);
	        
	        // jab/block calcs
	        if(attacker.getCurrMove().equals("JAB")) {
	        	if(defender.getCurrMove().equals("BLOCK") && defender.isFaster())
	        		totalAttack = 0;
	        	else
	        		totalAttack /= 2;
	        }
	        
	        // swing/block calcs
	        if(defender.getCurrMove().equals("BLOCK")) {
	        	if(defender.isFaster()) {
	        		System.out.println("resistance: "+defender.getPlayer().getInventory().getActiveShield().getResistance());
	        		System.out.println("piercing: "+attacker.getPlayer().getInventory().getActiveSword().getPiercing());
	        		System.out.println("initial damage: "+totalAttack);
	        		int shieldLeak = defender.getPlayer().getInventory().getActiveShield().getResistance()-attacker.getPlayer().getInventory().getActiveSword().getPiercing();
	        		if(shieldLeak >= 100) {
	        			totalAttack = 0;
	        		}else if(shieldLeak > 0) {
	        			totalAttack *= (1-shieldLeak/100.0);
	        		}
	        	}
	        }
			
	        System.out.println("------");
	        System.out.println(attacker.getName()+"'s damage: "+totalAttack);
	        System.out.println(defender.getName()+"'s HP before: "+defender.getCurrHealth());
	        
			defender.setCurrHealth(defender.getCurrHealth()-totalAttack);
			
			System.out.println(defender.getName()+"'s HP after: "+defender.getCurrHealth());
			
		}
		
		// remove stamina
		if(attacker.getCurrMove().equals("SWING")) {
			
		}else if(attacker.getCurrMove().equals("JAB")) {
			
		}else if(attacker.getCurrMove().equals("BLOCK")) {
			
		}
		
	}
	
	public PlayerBattleState speedComparison(PlayerBattleState A, PlayerBattleState B) {
		Random random = new Random();
		double multiplierA = 0.9 + (random.nextDouble() * 0.2);
		double multiplierB = 0.9 + (random.nextDouble() * 0.2);
		int speedA = speedCheck(A, multiplierA);
		int speedB = speedCheck(B, multiplierB);
		System.out.println(A.getName()+"'s speed: "+speedA);
		System.out.println(B.getName()+"'s speed: "+speedB);
		if(speedA>speedB) {
			A.setFaster(true);
			B.setFaster(false);
			return A;
		} else if(speedB>speedA) {
			B.setFaster(true);
			A.setFaster(false);
			return B;
		}else {
			if(random.nextInt(2) == 1) {
				A.setFaster(true);
				B.setFaster(false);
				return A;
			}
		}
		B.setFaster(true);
		A.setFaster(false);
		return B;
	}
	
	public int speedCheck(PlayerBattleState player, double randomMultiplier) {
		
		double baseArmourSpeed = player.getCurrSpeed()/100.0 * player.getPlayer().getInventory().getActiveArmour().getSpeedModifier();
		
        if(player.getCurrMove().equals("BLOCK")) {
        	double blockSpeed = baseArmourSpeed - player.getPlayer().getInventory().getActiveShield().getWeight();
    		return (int) (blockSpeed * randomMultiplier);
        } else if(player.getCurrMove().equals("JAB")) {
        	double jabSpeed = baseArmourSpeed - player.getPlayer().getInventory().getActiveSword().getWeight();
    		return (int) (jabSpeed * randomMultiplier);
        } else if(player.getCurrMove().equals("SWING")) {
        	double swingSpeed = baseArmourSpeed - player.getPlayer().getInventory().getActiveSword().getWeight();
    		return (int) (swingSpeed * randomMultiplier / 2);
        }
        
		return 0;		
	}
	
}
