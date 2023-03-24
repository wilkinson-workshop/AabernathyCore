package org.ww.adt.warp;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.UUID;

@jakarta.persistence.Entity
@Table(name = "warps")
@Getter
@Setter
public class WarpMeta {
    private String name;
    private String worldName;
    private WarpType type;
    private UUID ownerUUID;

    private float positionX;
    private float positionZ;
    private float positionY;

    private float pitch;
    private float yaw;

    private WarpAccess access;

    public WarpMeta() {}

    public WarpMeta(
            String name,
            String worldName,
            WarpType type,
            UUID ownerUUID,
            float x,
            float z,
            float y,
            float pitch,
            float yaw,
            WarpAccess access)
    {
        this.name = name;
        this.worldName = worldName;
        this.type = type;
        this.ownerUUID = ownerUUID;

        this.positionX = x;
        this.positionZ = z;
        this.positionY = y;

        this.pitch = pitch;
        this.yaw   = yaw;

        this.access = access;
    }

    public WarpMeta(
            String name,
            String worldName,
            WarpType type,
            UUID ownerUUID,
            WarpPoint point,
            WarpAccess access)
    {
        this.name = name;
        this.worldName = worldName;
        this.type = type;
        this.ownerUUID = ownerUUID;

        this.positionX = point.PositionX();
        this.positionZ = point.PositionZ();
        this.positionY = point.PositionY();

        this.pitch = point.Pitch();
        this.yaw   = point.Yaw();

        this.access = access;
    }

    public String Name() { return this.name; }
    public String WorldName() { return this.worldName; }
    public WarpType Type() { return this.type; }
    public UUID OwnerUUID() { return this.ownerUUID; }

    public WarpAccess Access() { return this.access; }

    public WarpPoint Point()
    {
        return new WarpPoint(
                this.positionX,
                this.positionZ,
                this.positionY,
                this.pitch,
                this.yaw);
    }

    public static WarpMeta fromEntity(Entity entity, String name, WarpAccess access)
    {
        WarpType type;

        Location loc = entity.getLocation();
        WarpPoint point = new WarpPoint(
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
                name,
                worldName,
                type,
                ownerUUID,
                point,
                access);
    }
}
