package battle;

import java.util.Random;

public class SpeedCheck {
	
	public PlayerBattleState speedComparison(PlayerBattleState A, PlayerBattleState B) {
		Random random = new Random();
		double multiplierA = 0.7 + (random.nextDouble() * 0.6);
		double multiplierB = 0.7 + (random.nextDouble() * 0.6);
		int speedA = speedCheck(A, multiplierA);
		int speedB = speedCheck(B, multiplierB);
		//System.out.println(A.getName()+"'s speed: "+speedA);
		//System.out.println(B.getName()+"'s speed: "+speedB);
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
		return null;
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
        } else if(player.getCurrMove().equals("CHARGE")) {
        	double chargeSpeed = baseArmourSpeed - player.getPlayer().getInventory().getActiveSword().getWeight();
    		return (int) (chargeSpeed * randomMultiplier / 4);
        } else if(player.getCurrMove().equals("SWAP_SWORDS")) {
        	double swapSpeed = baseArmourSpeed - player.getPlayer().getInventory().getActiveSword().getWeight();
    		return (int) (swapSpeed * randomMultiplier / 4);
        } 
        
		return 0;		
	}
}
