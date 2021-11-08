package fr.aphaistos.projectnation.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.aphaistos.projectnation.GameProfile;
import fr.aphaistos.projectnation.PNPlugin;

public class PNPlayerConnectionEvent extends PNListener {

	public PNPlayerConnectionEvent(PNPlugin plugin) {
		super(plugin);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		GameProfile profile = new GameProfile(player.getUniqueId(), plugin.getDefaultBalance());
		this.plugin.getGameProfilesManager().add(profile);
		
		event.setJoinMessage(this.plugin.getJoinMessage().replace("%prefix%", this.plugin.getPrefix()).replace("%player%", player.getDisplayName()));
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		
		event.setQuitMessage(this.plugin.getQuitMessage().replace("%prefix%", this.plugin.getPrefix()).replace("%player%", player.getDisplayName()));
	}

}