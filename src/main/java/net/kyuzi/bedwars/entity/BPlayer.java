package net.kyuzi.bedwars.entity;

import java.util.UUID;

public class BPlayer {

    private UUID uuid;
    private String name;
    private BPlayerStats statistics;

    public BPlayer(UUID uuid, String name, BPlayerStats statistics) {
        this.uuid = uuid;
        this.name = name;
        this.statistics = statistics;
    }

    public UUID getUUID() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public BPlayerStats getStatistics() {
        return statistics;
    }

}
