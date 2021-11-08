package fr.aphaistos.projectnation;

import java.util.UUID;

public class GameProfile {
	
	private UUID uuid;
	
	private double balance;
	
	public GameProfile(UUID uuid, double balance) {
		this.uuid = uuid;
		this.balance = balance;
	}
	
	public UUID getUuid() {
		return uuid;
	}
	
	public double getBalance() {
		return balance;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		builder.append("uuid=");
		builder.append(uuid.toString());
		builder.append(",");
		builder.append("balance=");
		builder.append(balance);
		builder.append("]");
		return builder.toString();
	}
	
}