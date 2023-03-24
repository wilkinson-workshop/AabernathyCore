package org.ww.adt.warp;

import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.ww.adt.AabernathyComponent;

public abstract class WarpManagerBase extends AabernathyComponent implements WarpManagerAPI
{
    public static boolean warpIsEntityAccessible(@NotNull Entity entity, WarpMeta warp)
    {
        return (entityIsOwner(entity, warp) || warpIsPublic(warp));
    }

    public static boolean warpIsPublic(WarpMeta warp)
    {
        return (warp.Access() == WarpAccess.PUBLIC);
    }

    public static boolean serverIsOwner(WarpMeta warp)
    {
        return (warp.Type() == WarpType.SERVER_OWNED);
    }

    public static boolean entityIsOwner(@NotNull Entity entity, WarpMeta warp)
    {
        return (warp.OwnerUUID() == entity.getUniqueId());
    }

}
