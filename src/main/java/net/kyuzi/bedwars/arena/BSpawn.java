package net.kyuzi.bedwars.arena;

public class BSpawn {

    private int team;
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;

    public BSpawn(int team, double x, double y, double z, float yaw, float pitch) {
        this.team = team;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public int getTeam() {
        return team;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

}
