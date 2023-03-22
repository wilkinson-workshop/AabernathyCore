package org.ww.adt.data;

import org.ww.adt.AabernathyComponent;
import org.ww.adt.AabernathyI;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database extends AabernathyComponent
{

    /**
     * Location of database file on disk.
     */
    private final File databaseFile;

    class DbConnection implements AutoCloseable
    {
        /**
         * Connection string used to connect to the database.
         */
        private final String connectionUri;

        /**
         * Internal connection object.
         */
        private final Connection connection;

        public DbConnection(String connectionUri) throws SQLException
        {
            this.connectionUri = connectionUri;
            this.connection    = DriverManager.getConnection(this.connectionUri);
        }

        @Override
        public void close()
        {
            try {
                if (this.connection != null)
                    this.connection.close();
            } catch (SQLException error) {
                System.out.println(error.getMessage());
            }
        }
    }

    public Database(AabernathyI apiInstance, String databasePath)
    {
        super(apiInstance);
        this.databaseFile = new File(databasePath);
    }

    public DbConnection connect() throws SQLException
    {
        return new DbConnection("jdbc:sqlite:{}".replace("{}", databaseFile.getAbsolutePath()));
    }
}
