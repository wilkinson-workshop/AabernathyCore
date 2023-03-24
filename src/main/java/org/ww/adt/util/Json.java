package org.ww.adt.util;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import org.ww.adt.AabernathyComponent;

import java.io.*;
import java.lang.reflect.Type;

/**
 * Utility wrapper around basic JSON operations where we use the Gson parser.
 */
public class Json extends AabernathyComponent
{
    private final static Gson gson = new Gson();

    /**
     * Loads an object of the given type from disk, parsing it as JSON.
     * @param filePath
     * @param typeOfT
     * @return
     * @param <T>
     * @throws JsonIOException
     * @throws JsonSyntaxException
     */
    public static <T> T load(File filePath, Type typeOfT)
    {
        T data = null;
        Reader reader = null;

        try {
            reader = new FileReader(filePath);
            data = gson.fromJson(reader, typeOfT);
        } catch (FileNotFoundException error)
        {
            getApiInstance().getLogger().warning("Could not locate file: " + filePath.getPath());
        } finally {
            if (!closeReader(reader))
                getApiInstance().getLogger().warning("Could not properly close JSON reader.");
        }

        return data;
    }

    /**
     * Saves the given data as serialized JSON to the target path.
     * @param filePath
     * @param data
     */
    public static void dump(File filePath, Object data)
    {
        Writer writer = null;

        try {
            filePath.createNewFile();
            writer = new FileWriter(filePath, false);
            gson.toJson(data, writer);
        } catch (IOException error)
        {
            getApiInstance().getLogger().warning("Failed to dump into file: " + filePath.getPath());
            getApiInstance().getLogger().warning(error.getMessage());
        } finally {
            if (!closeWriter(writer, true))
                getApiInstance().getLogger().warning("Could not properly close JSON writer.");
        }
    }

    private static boolean closeWriter(Writer writer, boolean flushStream)
    {
        try {
            writer.flush();
        } catch (IOException error)
        {
            getApiInstance().getLogger().warning(error.getMessage());
        }

        try {
            writer.close();
            return true;
        } catch (IOException error)
        {
            getApiInstance().getLogger().warning(error.getMessage());
            return false;
        }
    }

    private static boolean closeReader(Reader reader)
    {
        try {
            reader.close();
            return true;
        } catch (IOException error)
        {
            getApiInstance().getLogger().warning(error.getMessage());
            return false;
        }
    }
}
