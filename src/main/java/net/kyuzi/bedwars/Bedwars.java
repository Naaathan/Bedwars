package net.kyuzi.bedwars;

import net.kyuzi.bedwars.storage.AbstractStorage;
import net.kyuzi.bedwars.storage.FileStorage;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Bedwars extends JavaPlugin {

    private static Bedwars instance;
    private AbstractStorage storage;

    public static Bedwars getInstance() {
        return instance;
    }

    public AbstractStorage getStorage() {
        return storage;
    }

    @Override
    public void onDisable() {
        if (storage != null) {
            storage.save();
        }
    }

    @Override
    public void onEnable() {
        instance = this;

        if (!new File(getDataFolder(), "config.yml").exists()) {
            saveDefaultConfig();
        }

        String storageType = getConfig().getString("storage.type");

        switch (storageType.toLowerCase()) {
            case "file":
                try {
                    this.storage = new FileStorage(getConfig().getString("storage.file.arenas"), getConfig().getString("storage.file.players"));
                } catch (IOException e) {
                    getLogger().severe("Failed to load storage: " + e.getMessage());
                    getServer().getPluginManager().disablePlugin(this);
                }

                break;
            case "mysql":
            default:
                throw new UnsupportedOperationException(storageType + " is not currently supported!");
        }

        storage.loadArenas();
        storage.loadPlayers();
    }

}
