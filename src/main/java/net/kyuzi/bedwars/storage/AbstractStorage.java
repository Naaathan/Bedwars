package net.kyuzi.bedwars.storage;

import net.kyuzi.bedwars.arena.BArena;
import net.kyuzi.bedwars.entity.BPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public abstract class AbstractStorage {

    protected List<BArena> arenas;
    protected List<BPlayer> players;

    public AbstractStorage() {
        this.arenas = new ArrayList<>();
        this.players = new ArrayList<>();
    }

    /**
     * Get a copy of the arenas list.
     *
     * @return a copy of the arenas list
     */
    public List<BArena> getArenas() {
        return new ArrayList<>(arenas);
    }

    /**
     * Get a copy of the players list.
     *
     * @return a copy of the players list
     */
    public List<BPlayer> getPlayers() {
        return new ArrayList<>(players);
    }

    /**
     * Deletes all data from disk.
     *
     * @return true if successful, false otherwise
     */
    public abstract boolean delete();

    /**
     * Saves all data currently stored to disk.
     *
     * @return true if successful, false otherwise
     */
    public abstract boolean save();

    /**
     * Loads the arena into storage from disk.
     *
     * @param name the arena's name
     * @return the new arena instance or null if unsuccessful
     */
    public abstract BArena loadArena(String name);

    /**
     * Loads all arenas into storage from disk.s
     *
     * @return a list of new arena instances that have been loaded or an empty list if unsuccessful
     */
    public abstract List<BArena> loadArenas();

    /**
     * Loads the arenas into storage from disk.
     *
     * @param names the arena names
     * @return a list of new arena instances or an empty list if unsuccessful
     */
    public abstract List<BArena> loadArenas(List<String> names);

    /**
     * Loads the player into storage from disk and updates their name if required.
     *
     * @param uuid the player's UUID
     * @param name the player's name
     * @return the new player instance or null if unsuccessful
     */
    public abstract BPlayer loadPlayer(UUID uuid, String name);

    /**
     * Loads the players into storage from disk that are currently online.
     *
     * @return a list of new player instances or an empty list if unsuccessful
     */
    public abstract List<BPlayer> loadPlayers();

    /**
     * Loads the players into storage from disk and updates their names if required.
     *
     * @param information hash map of each player's information (uuid and names)
     * @return a list of new player instances or an empty list if unsuccessful
     */
    public abstract List<BPlayer> loadPlayers(HashMap<UUID, String> information);

    /**
     * Unloads the player from storage and saves them to disk.
     *
     * @param player the player instance
     * @return true if successful, false otherwise
     */
    public abstract boolean unloadPlayer(BPlayer player);

}
