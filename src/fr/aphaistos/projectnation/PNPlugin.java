package fr.aphaistos.projectnation;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.aphaistos.projectnation.listeners.PNPlayerConnectionEvent;
import fr.aphaistos.projectnation.managers.GameProfilesManager;

public class PNPlugin extends JavaPlugin {
	
	private GameProfilesManager gameProfilesManager;
	
	/* Config */
	private double defaultBalance;
	private String prefix;
	private String joinMessage;
	private String quitMessage;
	
	@Override
	public void onEnable() {
		loadConfig();
		this.gameProfilesManager = new GameProfilesManager(this);
		this.gameProfilesManager.getProfilesConfig().saveDefaultConfig();
		
		PluginManager pM = getServer().getPluginManager();
		pM.registerEvents(new PNPlayerConnectionEvent(this), this);
	}
	
	public void loadConfig() {
		saveDefaultConfig();
		this.defaultBalance = getConfig().getDouble("default-balance");
		this.prefix = getConfig().getString("messages.prefix").replace('&', '§');
		this.joinMessage = getConfig().getString("messages.join-message").replace('&', '§');
		this.quitMessage = getConfig().getString("messages.quit-message").replace('&', '§');
	}

	@Override
	public void onDisable() {
		for(GameProfile profile : gameProfilesManager.getGameProfiles().values()) {
			System.out.println(profile.toString());
		}
		gameProfilesManager.saveGameProfiles();
	}
	
	public GameProfilesManager getGameProfilesManager() {
		return gameProfilesManager;
	}
	
	/* Config */
	public double getDefaultBalance() {
		return defaultBalance;
	}

	public String getPrefix() {
		return prefix;
	}
	
	public String getJoinMessage() {
		return joinMessage;
	}
	
	public String getQuitMessage() {
		return quitMessage;
	}
	
}