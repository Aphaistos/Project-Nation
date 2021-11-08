package fr.aphaistos.projectnation.managers;

import java.util.Map;
import java.util.UUID;

import com.google.common.collect.Maps;

import fr.aphaistos.projectnation.GameProfile;

public class GameProfilesManager {
	
	private Map<UUID, GameProfile> gameProfiles;
	
	public GameProfilesManager() {
		this.gameProfiles = Maps.newHashMap();
	}
	
	public void add(GameProfile profile) {
		if(!gameProfiles.containsKey(profile.getUuid())) {
			gameProfiles.put(profile.getUuid(), profile);
		}
	}
	
	public GameProfile getProfile(UUID uuid) {
		if(gameProfiles.containsKey(uuid)) {
			GameProfile profile = gameProfiles.get(uuid);
			if(profile != null)
				return profile;
		}
		return null;
	}
	
	public Map<UUID, GameProfile> getGameProfiles() {
		return gameProfiles;
	}
}