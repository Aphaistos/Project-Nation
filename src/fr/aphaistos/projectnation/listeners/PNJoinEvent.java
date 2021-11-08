package fr.aphaistos.projectnation.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.aphaistos.projectnation.GameProfile;
import fr.aphaistos.projectnation.PNPlugin;

public class PNJoinEvent extends PNListener {

	public PNJoinEvent(PNPlugin plugin) {
		super(plugin);
	}
	
	@EventHandler
	public void onPlayer(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		GameProfile profile = new GameProfile(player.getUniqueId());
		this.plugin.getGameProfilesManager().add(profile);
		
		event.setJoinMessage(this.plugin.getJoinMessage().replace("%prefix%", this.plugin.getPrefix()).replace("%player%", player.getDisplayName()));
	}

}