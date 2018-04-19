package net.kyuzi.bedwars.storage;

import net.kyuzi.bedwars.Bedwars;
import net.kyuzi.bedwars.arena.BArena;
import net.kyuzi.bedwars.entity.BPlayer;
import net.kyuzi.bedwars.entity.BPlayerStats;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class FileStorage extends AbstractStorage {

    private FileConfiguration arenasConfig;
    private String arenasConfigPath;
    private FileConfiguration playersConfig;
    private String playersConfigPath;

    public FileStorage(String arenasConfigPath, String playersConfigPath) throws IOException {
        File arenasConfigFile = new File(Bedwars.getInstance().getDataFolder(), arenasConfigPath);
        File playersConfigFile = new File(Bedwars.getInstance().getDataFolder(), playersConfigPath);

        if (!arenasConfigFile.exists()) {
            arenasConfigFile.mkdirs();
            arenasConfigFile.createNewFile();
        }

        if (!playersConfigFile.exists()) {
            playersConfigFile.mkdirs();
            playersConfigFile.createNewFile();
        }

        this.arenasConfig = YamlConfiguration.loadConfiguration(arenasConfigFile);
        this.arenasConfigPath = arenasConfigPath;
        this.playersConfig = YamlConfiguration.loadConfiguration(playersConfigFile);
        this.playersConfigPath = playersConfigPath;
    }

    @Override
    public boolean delete() {
        File arenasConfigFile = new File(Bedwars.getInstance().getDataFolder(), arenasConfigPath);
        File playersConfigFile = new File(Bedwars.getInstance().getDataFolder(), playersConfigPath);

        return arenasConfigFile.delete() && playersConfigFile.delete();
    }

    @Override
    public boolean save() {
        try {
            arenasConfig.save(new File(Bedwars.getInstance().getDataFolder(), arenasConfigPath));
            playersConfig.save(new File(Bedwars.getInstance().getDataFolder(), playersConfigPath));
            return true;
        } catch (IOException e) {
            Bukkit.getLogger().severe("Failed to save storage files: " + e.getMessage());
            return false;
        }
    }

    @Override
    public BArena loadArena(String name) {
        return null;
    }

    @Override
    public List<BArena> loadArenas() {
        return null;
    }

    @Override
    public List<BArena> loadArenas(List<String> names) {
        return null;
    }

    @Override
    public BPlayer loadPlayer(UUID uuid, String name) {
        if (uuid == null || name == null) {
            return null;
        }

        if (!playersConfig.getString(uuid.toString() + ".name").equals(name)) {
            playersConfig.set(uuid.toString() + ".name", name);
        }

        int deaths = playersConfig.contains(uuid.toString() + ".statistics.deaths") ? playersConfig.getInt(uuid.toString() + ".statistics.deaths") : 0;
        int kills = playersConfig.contains(uuid.toString() + ".statistics.kills") ? playersConfig.getInt(uuid.toString() + ".statistics.kills") : 0;
        int played = playersConfig.contains(uuid.toString() + ".statistics.played") ? playersConfig.getInt(uuid.toString() + ".statistics.played") : 0;
        int wins = playersConfig.contains(uuid.toString() + ".statistics.wins") ? playersConfig.getInt(uuid.toString() + "statistics.wins") : 0;
        BPlayer player = new BPlayer(uuid, name, new BPlayerStats(deaths, kills, played, wins));

        players.add(player);
        return player;
    }

    @Override
    public List<BPlayer> loadPlayers() {
        List<BPlayer> players = new ArrayList<>();

        for (Player player : Bukkit.getOnlinePlayers()) {
            players.add(loadPlayer(player.getUniqueId(), player.getName()));
        }

        return players;
    }

    @Override
    public List<BPlayer> loadPlayers(HashMap<UUID, String> information) {
        List<BPlayer> players = new ArrayList<>();

        for (Map.Entry<UUID, String> entry : information.entrySet()) {
            players.add(loadPlayer(entry.getKey(), entry.getValue()));
        }

        return players;
    }

    @Override
    public boolean unloadPlayer(BPlayer player) {
        if (player == null) {
            return false;
        }

        UUID uuid = player.getUUID();
        BPlayerStats statistics = player.getStatistics();

        playersConfig.set(uuid.toString() + ".statistics.deaths", statistics.getDeaths());
        playersConfig.set(uuid.toString() + ".statistics.kills", statistics.getKills());
        playersConfig.set(uuid.toString() + ".statistics.played", statistics.getPlayed());
        playersConfig.set(uuid.toString() + ".statistics.wins", statistics.getWins());

        players.remove(player);
        return true;
    }

}
