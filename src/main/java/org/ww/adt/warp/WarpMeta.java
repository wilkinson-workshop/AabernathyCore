package org.ww.adt.warp;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@jakarta.persistence.Entity
@Table(name = "warps")
public class WarpMeta {
    private String name;
    private String worldName;
    private WarpType warpType;

    @Id
    private UUID ownerUUID;

    private float positionX;
    private float positionZ;
    private float positionY;

    private float pitch;
    private float yaw;

    private WarpAccess access;

    public WarpMeta(
            String name,
            String worldName,
            WarpType warpType,
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
        this.warpType = warpType;
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
            WarpType warpType,
            UUID ownerUUID,
            WarpPoint point,
            WarpAccess access)
    {
        this.name = name;
        this.worldName = worldName;
        this.warpType = warpType;
        this.ownerUUID = ownerUUID;

        this.positionX = point.getPositionX();
        this.positionZ = point.getPositionZ();
        this.positionY = point.getPositionY();

        this.pitch = point.getPitch();
        this.yaw   = point.getYaw();

        this.access = access;
    }

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
        WarpType vWarpType;

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
            vWarpType = WarpType.PLAYER_OWNED;
        else
            vWarpType = WarpType.SERVER_OWNED;

        return new WarpMeta(
                name,
                worldName,
                vWarpType,
                ownerUUID,
                point,
                access);
    }
}
