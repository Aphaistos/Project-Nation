package fr.aphaistos.projectnation;

import java.util.UUID;

public class GameProfile {
	
	private UUID uuid;
	
	public GameProfile(UUID uuid) {
		this.uuid = uuid;
	}
	
	public UUID getUuid() {
		return uuid;
	}
	
}