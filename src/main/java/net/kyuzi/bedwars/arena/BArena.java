package net.kyuzi.bedwars.arena;

import java.util.ArrayList;
import java.util.List;

public class BArena {

    private String name;
    private List<BSpawn> spawns;

    public BArena(String name) {
        this(name, new ArrayList<>());
    }

    public BArena(String name, List<BSpawn> spawns) {
        this.name = name;
        this.spawns = spawns;
    }

    public String getName() {
        return name;
    }

    public List<BSpawn> getSpawns() {
        return new ArrayList<>(spawns);
    }

    public List<BSpawn> getSpawns(int team) {
        List<BSpawn> spawns = new ArrayList<>();

        if (!this.spawns.isEmpty()) {
            for (BSpawn spawn : this.spawns) {
                if (spawn.getTeam() == team) {
                    spawns.add(spawn);
                }
            }
        }

        return spawns;
    }

    public void addSpawn(BSpawn spawn) {
        spawns.add(spawn);
    }

    public void removeSpawn(BSpawn spawn) {
        spawns.remove(spawn);
    }

}
