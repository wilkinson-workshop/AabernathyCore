package org.ww.adt.warp;

import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface WarpManagerAPI
{
    /**
     * Creates a new WarpMeta object and adds it to the warp records. A record
     * which describes the data of a warp point.
     * @param entity
     * @param name
     * @param access
     * @return WarpMeta object that was created.
     */
    WarpMeta addEntityRecord(@NotNull Entity entity, String name, WarpAccess access);

    /**
     * Creates a new WarpMeta object and adds it to the warp records. A record
     * which describes the data of a warp point.
     * @param entity
     * @param name
     * @return WarpMeta object that was created.
     */
    WarpMeta addEntityRecord(@NotNull Entity entity, String name);

    /**
     * Removes the given WarpMeta object from warp records and returns it.
     * @param entity
     * @return object describing some warp location.
     */
    WarpMeta popEntityRecord(@NotNull Entity entity, String name);

    /**
     * Retrieve all WarpMeta record objects that are accessible for the entity.
     * This includes all records owned by the entity and records that are public.
     * @param entity
     * @return List of WarpMeta objects.
     */
    List<WarpMeta> getEntityRecord(@NotNull Entity entity);

    /**
     * Retrieve all WarpMeta record objects that are accessible for the entity.
     * This filters for only objects that are of a specific type; owned by the player,
     * owned by the server and public, or owned by anyone and public.
     * @param entity
     * @param type
     * @return List of WarpMeta objects.
     */
    List<WarpMeta> getEntityRecord(@NotNull Entity entity, WarpType type);

    /**
     * Retrieve a WarpMeta record object if it exists and is owned by the
     * entity or is public.
     * @param entity
     * @param name
     * @return object describing some warp location.
     */
    WarpMeta getEntityRecord(@NotNull Entity entity, String name);
}
