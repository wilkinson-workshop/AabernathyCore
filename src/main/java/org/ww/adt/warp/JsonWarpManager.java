package org.ww.adt.warp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JsonWarpManager extends WarpManagerBase
{
    private final static Gson gson = new Gson();
    private final static String formatKey = "%s:%s"; // <UUID>:<warpName>

    private final Map<String, WarpMeta> warpRecords;

    public JsonWarpManager()
    {
        super();
        warpRecords = new LinkedHashMap<>();
    }

    public JsonWarpManager(Map<String, WarpMeta> warpRecords)
    {
        super();
        this.warpRecords = warpRecords;
    }

    /**
     * Load a WarpManager object from the target filePath
     * @param filePath
     * @return a WarpManager object.
     */
    public static JsonWarpManager loadJSON(File filePath)
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
        return new JsonWarpManager(data);
    }

    /**
     * Saves WarpManager records to a target filePath on disk.
     * @param filePath
     */
    public static void dumpJSON(JsonWarpManager manager, File filePath)
    {
        filePath.getParentFile().mkdirs();

        try {
            filePath.createNewFile();
            Writer writer = new FileWriter(filePath, false);
            gson.toJson(manager.warpRecords, writer);

            writer.flush();
            writer.close();
        } catch (IOException error) {
            getApiInstance().getLogger().warning(error.getMessage());
        }
    }

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

    public WarpMeta popEntityRecord(@NotNull Entity entity, String name)
    {
        return warpRecords.remove(makeEntityKey(entity, name));
    }

    public List<WarpMeta> getEntityRecord(@NotNull Entity entity)
    {
        LinkedList<WarpMeta> warps = new LinkedList<>();
        for (WarpMeta warp : warpRecords.values())
        {
            if (warpIsEntityAccessible(entity, warp))
                warps.add(warp);
        }
        return warps;
    }

    public List<WarpMeta> getEntityRecord(@NotNull Entity entity, WarpType type)
    {
        LinkedList<WarpMeta> warps = new LinkedList<>();
        for (WarpMeta warp : warpRecords.values())
        {
            if (!warpIsEntityAccessible(entity, warp))
                continue;
            if (entityIsOwner(entity, warp) && type == WarpType.PLAYER_OWNED)
                warps.add(warp);
            else if (serverIsOwner(warp) && type == WarpType.SERVER_OWNED)
                warps.add(warp);
            else if (!serverIsOwner(warp) && type == WarpType.ENTITY_OWNED)
                warps.add(warp);
        }
        return warps;
    }

    public WarpMeta getEntityRecord(@NotNull Entity entity, String name)
    {
        return warpRecords.get(makeEntityKey(entity, name));
    }

    private static String makeEntityKey(@NotNull Entity entity, String name)
    {
        return String.format(formatKey, entity.getUniqueId(), name);
    }
}
