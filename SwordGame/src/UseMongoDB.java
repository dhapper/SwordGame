import org.bson.Document; 
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.push;

import java.util.ArrayList;
import java.util.List;

public class UseMongoDB {
	
	MongoClient mongoClient;
	MongoDatabase database;
	MongoCollection<Document> collection;
	
	public void initConnection(String collectionName) {
		mongoClient = new MongoClient("localhost", 27017);
		database = mongoClient.getDatabase("SWORD_GAME");
        collection = database.getCollection(collectionName);
	}
	
	public Player getPlayer(String username) {
		initConnection("players");
		Document query = new Document("username", username);
		MongoCursor<Document> cursor = collection.find(query).iterator();
		if(cursor.hasNext()) {
			Document Doc = cursor.next();
			Player player = new Player(username);
			player.setNickname(Doc.getString("nickname"));
	        player.setHealth(Doc.getInteger("health"));
	        player.setStrength(Doc.getInteger("strength"));
	        player.setSpeed(Doc.getInteger("speed"));
	        player.setStamina(Doc.getInteger("stamina"));
	        player.setKnowledge(Doc.getInteger("knowledge"));
            return player;
		}
		return null;
	}
	
	public Sword getSword(String name) {
		initConnection("swords");
		Document query = new Document("name", name);
		MongoCursor<Document> cursor = collection.find(query).iterator();
		if(cursor.hasNext()) {
			Document Doc = cursor.next();
			Sword sword = new Sword(name);
	        sword.setDamage(Doc.getInteger("damage"));
	        sword.setWeight(Doc.getInteger("weight"));
	        sword.setPiercing(Doc.getInteger("piercing"));
	        sword.setDurability(Doc.getInteger("durability"));
	        sword.setStaminaUsage(Doc.getInteger("stamina usage"));
            return sword;
		}
		return null;
	}
	
	public Shield getShield(String name) {
		initConnection("shields");
		Document query = new Document("name", name);
		MongoCursor<Document> cursor = collection.find(query).iterator();
		if(cursor.hasNext()) {
			Document Doc = cursor.next();
			Shield shield = new Shield(name);
	        shield.setWeight(Doc.getInteger("weight"));
	        shield.setResistance(Doc.getInteger("resistance"));
	        shield.setDurability(Doc.getInteger("durability"));
	        shield.setStaminaRegain(Doc.getInteger("stamina regain"));
            return shield;
		}
		return null;
	}
	
	public Armour getArmour(String name) {
		initConnection("armour");
		Document query = new Document("name", name);
		MongoCursor<Document> cursor = collection.find(query).iterator();
		if(cursor.hasNext()) {
			Document Doc = cursor.next();
			Armour armour = new Armour(name);
	        armour.setProtection(Doc.getInteger("protection"));
	        armour.setSpeedModifier(Doc.getInteger("speed modifier"));
            return armour;
		}
		return null;
	}
	
	public void addSwordToInventory(Player player, Sword sword) {
		initConnection("inventory");
		Document query = new Document("username", player.getUsername());
		MongoCursor<Document> cursor = collection.find(query).iterator();
		if(cursor.hasNext()) {
			Document doc = cursor.next();
			List<String> swords = (List<String>) doc.get("swords");
			if(!swords.contains(sword.getName())) {
				Bson updateOperation = push("swords", sword.getName());
				collection.updateOne(eq("username", player.getUsername()), updateOperation);
			}
		}
	}
	
	public void getSwords(Player player) {
		initConnection("inventory");
		Document query = new Document("username", player.getUsername());
		MongoCursor<Document> cursor = collection.find(query).iterator();
		if(cursor.hasNext()) {
			Document doc = cursor.next();
			List<String> swords = (List<String>) doc.get("swords");
			for(String swordName : swords) {
				player.getInventory().getSwords().add(getSword(swordName));
			}
		}
	}
	
	public void addShieldToInventory(Player player, Shield shield) {
		initConnection("inventory");
		Document query = new Document("username", player.getUsername());
		MongoCursor<Document> cursor = collection.find(query).iterator();
		if(cursor.hasNext()) {
			Document doc = cursor.next();
			List<String> shields = (List<String>) doc.get("shields");
			if(!shields.contains(shield.getName())) {
				Bson updateOperation = push("shields", shield.getName());
				collection.updateOne(eq("username", player.getUsername()), updateOperation);
			}
		}
	}
	
	public void getShields(Player player) {
		initConnection("inventory");
		Document query = new Document("username", player.getUsername());
		MongoCursor<Document> cursor = collection.find(query).iterator();
		if(cursor.hasNext()) {
			Document doc = cursor.next();
			List<String> shields = (List<String>) doc.get("shields");
			for(String shieldName : shields) {
				player.getInventory().getShields().add(getShield(shieldName));
			}
		}
	}
	
	public void addArmourToInventory(Player player, Armour armour) {
		initConnection("inventory");
		Document query = new Document("username", player.getUsername());
		MongoCursor<Document> cursor = collection.find(query).iterator();
		if(cursor.hasNext()) {
			Document doc = cursor.next();
			List<String> armourString = (List<String>) doc.get("armour");
			if(!armourString.contains(armour.getName())) {
				Bson updateOperation = push("armour", armour.getName());
				collection.updateOne(eq("username", player.getUsername()), updateOperation);
			}
		}
	}
	
	public void getArmoury(Player player) {
		initConnection("inventory");
		Document query = new Document("username", player.getUsername());
		MongoCursor<Document> cursor = collection.find(query).iterator();
		if(cursor.hasNext()) {
			Document doc = cursor.next();
			List<String> armourString = (List<String>) doc.get("armour");
			for(String armourName : armourString) {
				player.getInventory().getArmoury().add(getArmour(armourName));
			}
		}
	}
	
	public void getActiveItem(Player player, String itemType) {
		initConnection("inventory");
		Document query = new Document("username", player.getUsername());
		MongoCursor<Document> cursor = collection.find(query).iterator();
		if(cursor.hasNext()) {
			Document Doc = cursor.next();
			if(itemType == "ACTIVE_SWORD") {
				player.getInventory().setActiveSword(getSword(Doc.getString("active sword")));
				
			} else if(itemType == "INACTIVE_SWORD") {
				player.getInventory().setInactiveSword(getSword(Doc.getString("inactive sword")));	
			} else if(itemType == "ACTIVE_SHIELD") {
				player.getInventory().setActiveShield(getShield(Doc.getString("active shield")));	
			} else if(itemType == "ACTIVE_ARMOUR") {
				player.getInventory().setActiveArmour(getArmour(Doc.getString("active armour")));		
			}
		}
	}
	
	//get and set activeSword
	
}
