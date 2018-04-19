package net.kyuzi.bedwars.entity;

public class BPlayerStats {

    private int deaths;
    private int kills;
    private int played;
    private int wins;

    public BPlayerStats(int deaths, int kills, int played, int wins) {
        this.deaths = deaths;
        this.kills = kills;
        this.played = played;
        this.wins = wins;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getPlayed() {
        return played;
    }

    public void setPlayed(int played) {
        this.played = played;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

}
