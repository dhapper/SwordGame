import java.util.Random;

public class TurnLogic {
	
	public void swing(PlayerBattleState attacker, PlayerBattleState defender) {
		
		
		System.out.println("------");
		System.out.println(defender.getName()+" before: "+defender.getCurrHealth());
		
		// if !block 
		int strength = attacker.getCurrStrength();
		int damage = attacker.getPlayer().getSword().getDamage();
		int remainingHealth = defender.getCurrHealth();
		double armour = defender.getPlayer().getArmour().getProtection();
		
		double baseAttack = (strength*damage/100)*(5-armour);
		Random random = new Random();
        double randomMultiplier = 0.9 + (random.nextDouble() * 0.2);
        int totalAttack = (int) (baseAttack * randomMultiplier);
		
        System.out.println(attacker.getName()+" damage: "+totalAttack);
        
		defender.setCurrHealth(remainingHealth-totalAttack);
		
		System.out.println(defender.getName()+" after: "+defender.getCurrHealth());
		// if block
	}
	
	public PlayerBattleState speedComparison(PlayerBattleState A, PlayerBattleState B) {	//can optimize alottttttttt
		Random random = new Random();
		double multiplierA = 0.9 + (random.nextDouble() * 0.2);
		double multiplierB = 0.9 + (random.nextDouble() * 0.2);
		int speedA = speedCheck(A, multiplierA);
		int speedB = speedCheck(B, multiplierB);
		System.out.println("speed A: "+speedA);
		System.out.println("speed B: "+speedB);
		if(speedA>speedB)
			return A;
		else if(speedB>speedA)
			return B;
		else {
			if(random.nextInt(2) == 1)
				return A;
		}
		return B;
	}
	
	public int speedCheck(PlayerBattleState player, double randomMultiplier) {
		
        if(player.getCurrMove().equals("BLOCK")) {
        	double blockSpeed = player.getCurrSpeed()
    				* player.getPlayer().getArmour().getWeight()
    				* player.getPlayer().getShield().getWeight();
    		return (int) (blockSpeed * randomMultiplier);
        }
        
        double jabSpeed = player.getCurrSpeed()
				* player.getPlayer().getArmour().getWeight()
				* player.getPlayer().getSword().getWeight();
        if(player.getCurrMove().equals("JAB")) {
    		return (int) (jabSpeed * randomMultiplier);
        }
        
        if(player.getCurrMove().equals("SWING")) {
        	double swingSpeed = (player.getCurrSpeed()
        			- player.getPlayer().getSword().getWeight())
    				* player.getPlayer().getArmour().getWeight();
    		return (int) (swingSpeed * randomMultiplier);
        }
		
		return 0;		
	}
	
}
