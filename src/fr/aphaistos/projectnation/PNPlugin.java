package fr.aphaistos.projectnation;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.aphaistos.projectnation.listeners.PNJoinEvent;
import fr.aphaistos.projectnation.managers.GameProfilesManager;

public class PNPlugin extends JavaPlugin {
	
	private GameProfilesManager gameProfilesManager;
	
	/* Config */
	private String prefix;
	private String joinMessage;
	
	@Override
	public void onEnable() {
		loadConfigs();
		this.gameProfilesManager = new GameProfilesManager();
		
		PluginManager pM = getServer().getPluginManager();
		pM.registerEvents(new PNJoinEvent(this), this);
	}
	
	public void loadConfigs() {
		saveDefaultConfig();
		this.prefix = getConfig().getString("messages.prefix").replace('&', '§');
		this.joinMessage = getConfig().getString("messages.join-message").replace('&', '§');
	}

	@Override
	public void onDisable() {
		
	}
	
	public GameProfilesManager getGameProfilesManager() {
		return gameProfilesManager;
	}
	
	/* Config */
	public String getPrefix() {
		return prefix;
	}
	
	public String getJoinMessage() {
		return joinMessage;
	}
	
}