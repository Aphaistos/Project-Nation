package fr.aphaistos.projectnation.listeners;

import org.bukkit.event.Listener;

import fr.aphaistos.projectnation.PNPlugin;

public class PNListener implements Listener {
	
	protected PNPlugin plugin;
	
	public PNListener(PNPlugin plugin) {
		this.plugin = plugin;
	}
	
	public PNPlugin getPlugin() {
		return plugin;
	}
	
}