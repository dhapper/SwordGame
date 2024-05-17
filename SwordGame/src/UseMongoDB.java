import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class UseMongoDB {

	// didn't surround connection with try catch for simplicity
	
	MongoClient mongoClient;
	MongoDatabase database;
	MongoCollection<Document> collection;
	
	/*public static void main(String[] args) {
		UseMongoDB a = new UseMongoDB();
		Player b = a.getPlayer("dhapper");
		b.displayStats();
	}*/
	
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
	        shield.setStaminaUsage(Doc.getInteger("stamina usage"));
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
	        armour.setStaminaUsage(Doc.getInteger("stamina usage"));
	        armour.setSpeedModifier(Doc.getDouble("speed modifier"));
            return armour;
		}
		return null;
	}
	
}
