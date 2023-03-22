package org.ww.adt.warp;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.UUID;

public class WarpMeta {
    private long id;
    private String name;
    private String worldName;
    private WarpType type;
    private UUID ownerUUID;

    private float positionX;
    private float positionZ;
    private float positionY;

    private float pitch;
    private float yaw;

    public WarpMeta(
            long id,
            String name,
            String worldName,
            WarpType type,
            UUID ownerUUID,
            float x,
            float z,
            float y,
            float pitch,
            float yaw)
    {
        this.id = id;
        this.name = name;
        this.worldName = worldName;
        this.type = type;
        this.ownerUUID = ownerUUID;

        this.positionX = x;
        this.positionZ = z;
        this.positionY = y;

        this.pitch = pitch;
        this.yaw   = yaw;
    }

    public WarpMeta(
            long id,
            String name,
            String worldName,
            WarpType type,
            UUID ownerUUID,
            WarpCoordinates coordinates)
    {
        this.id = id;
        this.name = name;
        this.worldName = worldName;
        this.type = type;
        this.ownerUUID = ownerUUID;

        this.positionX = coordinates.PositionX();
        this.positionZ = coordinates.PositionZ();
        this.positionY = coordinates.PositionY();

        this.pitch = coordinates.Pitch();
        this.yaw   = coordinates.Yaw();
    }

    public long Id() { return this.id; }
    public String Name() { return this.name; }
    public String WorldName() { return this.worldName; }
    public WarpType Type() { return this.type; }
    public UUID OwnerUUID() { return this.ownerUUID; }

    public WarpCoordinates Coordinates()
    {
        return new WarpCoordinates(
                this.positionX,
                this.positionZ,
                this.positionY,
                this.pitch,
                this.yaw);
    }

    public static WarpMeta fromEntity(Entity entity, String name, long id)
    {
        WarpType type;

        Location loc = entity.getLocation();
        WarpCoordinates coord = new WarpCoordinates(
                loc.getBlockX(),
                loc.getBlockZ(),
                loc.getBlockY(),
                loc.getPitch(),
                loc.getYaw());
        String worldName = entity.getWorld().getName();
        UUID ownerUUID = entity.getUniqueId();

        if (entity instanceof Player)
            type = WarpType.PLAYER_OWNED;
        else
            type = WarpType.SERVER_OWNED;

        return new WarpMeta(
                id,
                name,
                worldName,
                type,
                ownerUUID,
                coord);
    }
}
