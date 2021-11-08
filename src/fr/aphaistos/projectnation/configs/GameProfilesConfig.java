package fr.aphaistos.projectnation.configs;

import java.util.Map;
import java.util.UUID;

import org.bukkit.configuration.ConfigurationSection;

import com.google.common.collect.Maps;

import fr.aphaistos.projectnation.GameProfile;
import fr.aphaistos.projectnation.PNPlugin;

public class GameProfilesConfig extends PluginConfig {
	
	private Map<UUID, GameProfile> gameProfiles;
	
	public GameProfilesConfig(PNPlugin plugin) {
		super(plugin, "players-data.yml");
		this.gameProfiles = Maps.newHashMap();
	}

	@Override
	public void load() {
		for(String sectionName : this.getConfig().getKeys(false)) {
			this.loadProfile(sectionName);
		}
	}
	
	public void loadProfile(String sectionName) {
		UUID uuid = UUID.fromString(sectionName);
		if(uuid != null) {
			ConfigurationSection section = config.getConfigurationSection(sectionName);
			double balance = 0;
			if(section.contains("balance")) {
				balance = section.getDouble("balance");
			}
			
			GameProfile profile = new GameProfile(uuid, balance);
			
			plugin.getGameProfilesManager().add(profile);
			gameProfiles.put(uuid, profile);
		}
	}
	
	public void writeProfile(GameProfile profile) {
		if(!gameProfiles.containsKey(profile.getUuid()) && !config.contains(profile.getUuid().toString())) {
			ConfigurationSection section = config.createSection(profile.getUuid().toString());
			section.set("balance", profile.getBalance());
			
			save();
			gameProfiles.put(profile.getUuid(), profile);
		}
	}
}