package org.ww.adt.warp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.ww.adt.AabernathyComponent;
import org.ww.adt.AabernathyAPI;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class WarpManager extends AabernathyComponent
{
    private final static Gson gson = new Gson();

    private final Map<String, WarpMeta> warpRecords;

    public WarpManager()
    {
        super();
        warpRecords = new LinkedHashMap<>();
    }

    public WarpManager(Map<String, WarpMeta> warpRecords)
    {
        super();
        this.warpRecords = warpRecords;
    }

    /**
     * Load a WarpManager object from the target filePath
     * @param filePath
     * @return a WarpManager object.
     */
    public WarpManager loadJSON(File filePath)
    {
        Map<String, WarpMeta> data;
        Type serialType = new TypeToken<LinkedHashMap<String, WarpMeta>>() {}.getType();

        try {
            Reader reader = new FileReader(filePath);
            data = gson.fromJson(reader, serialType);

            reader.close();
        } catch (IOException error) {
            getApiInstance().getLogger().warning(error.getMessage());
            return null;
        }
        return new WarpManager(data);
    }

    /**
     * Saves WarpManager records to a target filePath on disk.
     * @param filePath
     */
    public void dumpJSON(File filePath)
    {
        filePath.getParentFile().mkdirs();

        try {
            filePath.createNewFile();
            Writer writer = new FileWriter(filePath, false);
            gson.toJson(warpRecords, writer);

            writer.flush();
            writer.close();
        } catch (IOException error) {
            getApiInstance().getLogger().warning(error.getMessage());
        }
    }

    /**
     * Creates a new WarpMeta object and adds it to the warp records. A record
     * which describes the data of a warp spot.
     * @param entity
     * @param name
     */
    public WarpMeta addEntityRecord(@NotNull Entity entity, String name, WarpAccess access)
    {
        WarpMeta data = WarpMeta.fromEntity(entity, name, access);
        warpRecords.put(makeEntityKey(entity, name), data);
        return data;
    }

    public WarpMeta addEntityRecord(@NotNull Entity entity, String name)
    {
        return addEntityRecord(entity, name, WarpAccess.PRIVATE);
    }

    /**
     * Removes the given WarpMeta object from warp records and returns it.
     * @param entity
     * @return object describing some warp location.
     */
    public WarpMeta popEntityRecord(@NotNull Entity entity, String name)
    {
        return warpRecords.remove(makeEntityKey(entity, name));
    }

    /**
     * Retrieve a WarpMeta record object.
     * @param entity
     * @param name
     * @return object describing some warp location.
     */
    public WarpMeta getEntityRecord(@NotNull Entity entity, String name)
    {
        return warpRecords.get(makeEntityKey(entity, name));
    }

    /**
     * Retrieve all WarpMeta records that are accessible to the entity.
     * @param entity
     * @return
     */
    public List<WarpMeta> getEntityRecords(@NotNull Entity entity)
    {
        LinkedList<WarpMeta> data = new LinkedList<>();
        for (WarpMeta warp : warpRecords.values())
        {
            if (warp.OwnerUUID() == entity.getUniqueId() || warp.Access() == WarpAccess.PUBLIC)
                data.add(warp);
        }
        return data;
    }

    /**
     * Retrieve all WarpMeta records related to an entity.
     * @param entity
     * @return
     */
    public List<WarpMeta> getEntityOwnedRecords(@NotNull Entity entity)
    {
        LinkedList<WarpMeta> data = new LinkedList<>();
        for (String key : warpRecords.keySet())
        {
            if (key.contains(entity.getUniqueId().toString()))
                data.add(warpRecords.get(key));
        }
        return data;
    }

    private static String makeEntityKey(@NotNull Entity entity, String name)
    {
        return String.format("%s::%s", entity.getUniqueId(), name);
    }
}
