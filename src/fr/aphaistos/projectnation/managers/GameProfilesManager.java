package fr.aphaistos.projectnation.managers;

import java.util.Map;
import java.util.UUID;

import com.google.common.collect.Maps;

import fr.aphaistos.projectnation.GameProfile;
import fr.aphaistos.projectnation.PNPlugin;
import fr.aphaistos.projectnation.configs.GameProfilesConfig;

public class GameProfilesManager {
	
	private Map<UUID, GameProfile> gameProfiles;
	private PNPlugin plugin;
	private GameProfilesConfig profilesConfig;
	
	public GameProfilesManager(PNPlugin plugin) {
		this.gameProfiles = Maps.newHashMap();
		this.plugin = plugin;
		this.profilesConfig = new GameProfilesConfig(plugin);
	}
	
	public void add(GameProfile profile) {
		if(!gameProfiles.containsKey(profile.getUuid())) {
			gameProfiles.put(profile.getUuid(), profile);
			profilesConfig.writeProfile(profile);
		}
	}
	
	public void remove(UUID uuid) {
		if(gameProfiles.containsKey(uuid)) {
			gameProfiles.remove(uuid);
		}
	}
	
	public void saveGameProfiles() {
		for(GameProfile profile : gameProfiles.values()) {
			profilesConfig.writeProfile(profile);
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
	
	public GameProfilesConfig getProfilesConfig() {
		return profilesConfig;
	}
	
	public PNPlugin getPlugin() {
		return plugin;
	}

}