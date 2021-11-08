package fr.aphaistos.projectnation.configs;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.google.common.base.Charsets;

import fr.aphaistos.projectnation.PNPlugin;

public abstract class PluginConfig {

	protected final PNPlugin plugin;
	private final String configFileName;
	protected FileConfiguration config;

	public PluginConfig(PNPlugin plugin, String configFileName) {
		this.plugin = plugin;
		this.configFileName = configFileName;
		this.config = YamlConfiguration.loadConfiguration(getFile());
	}

	public abstract void load();

	protected Location toBukkitLocation(String sectionName) {
		if (this.config.contains(sectionName + ".world") && this.config.contains(sectionName + ".x") && this.config.contains(sectionName + ".y") && this.config.contains(sectionName + ".z")) {
			String world = this.config.getString(sectionName + ".world");
			double x = this.config.getDouble(sectionName + ".x");
			double y = this.config.getDouble(sectionName + ".y");
			double z = this.config.getDouble(sectionName + ".z");
			if (this.config.contains(sectionName + ".yaw") && this.config.contains(sectionName + ".pitch")) {
				float yaw = (float) this.config.getDouble(sectionName + ".yaw");
				float pitch = (float) this.config.getDouble(sectionName + ".pitch");
				return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
			}
			return new Location(Bukkit.getWorld(world), x, y, z);
		}
		return null;
	}

	public void reloadConfig() {
		if (this.config != null) {
			FileConfiguration newConfig = YamlConfiguration.loadConfiguration(getFile());
			InputStream defConfigStream = this.plugin.getResource(this.configFileName);
			if (defConfigStream == null)
				return;
			newConfig.setDefaults(
					YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream, Charsets.UTF_8)));
			this.config = newConfig;
		}
	}

	public void save() {
		try {
			config.save(getFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.load();
	}

	public void saveDefaultConfig() {
		if (!getFile().exists()) {
			this.plugin.saveResource(configFileName, false);
		}
		this.load();
	}

	public File getFile() {
		return new File(plugin.getDataFolder(), configFileName);
	}

	public FileConfiguration getConfig() {
		return config;
	}
}