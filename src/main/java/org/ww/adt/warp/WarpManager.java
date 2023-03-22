package org.ww.adt.warp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.ww.adt.AabernathyComponent;
import org.ww.adt.AabernathyI;

import java.io.*;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

public class WarpManager extends AabernathyComponent
{
    private static Gson gson = new Gson();

    private Map<String, WarpMeta> warpRecords;

    public WarpManager(AabernathyI apiInstance)
    {
        super(apiInstance);
        warpRecords = new LinkedHashMap<>();
    }

    public WarpManager(AabernathyI apiInstance, Map<String, WarpMeta> warpRecords)
    {
        super(apiInstance);
        this.warpRecords = warpRecords;
    }

    /**
     * Load a WarpManager object from the target filePath
     * @param apiInstance
     * @param filePath
     * @return a WarpManager object.
     */
    public static WarpManager loadJSON(AabernathyI apiInstance, File filePath)
    {
        Map<String, WarpMeta> data;
        Type serialType = new TypeToken<LinkedHashMap<String, WarpMeta>>() {}.getType();

        try {
            Reader reader = new FileReader(filePath);
            data = gson.fromJson(reader, serialType);
        } catch (IOException error) {
            apiInstance.getLogger().warning(error.getMessage());
            return null;
        }
        return new WarpManager(apiInstance, data);
    }

    /**
     * Saves WarpManager records to a target filePath on disk.
     * @param manager
     * @param filePath
     */
    public static void dumpJSON(WarpManager manager, File filePath)
    {
        filePath.getParentFile().mkdirs();

        try {
            filePath.createNewFile();
            Writer writer = new FileWriter(filePath, false);
            gson.toJson(manager.warpRecords, writer);

            writer.flush();
            writer.close();
        } catch (IOException error) {
            manager.getApiInstance().getLogger().warning(error.getMessage());
        }
    }

    /**
     * Creates a new WarpMeta object and adds it to the warp records. A record
     * which describes the data of a warp spot.
     * @param entity
     * @param name
     */
    public WarpMeta addEntityRecord(@NotNull Entity entity, String name)
    {
        WarpMeta data = WarpMeta.fromEntity(entity, name, warpRecords.size());
        warpRecords.put(makeEntityKey(entity, name), data);
        return data;
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

    private static String makeEntityKey(@NotNull Entity entity, String name)
    {
        return String.format("%s::%s", entity.getUniqueId(), name);
    }
}
