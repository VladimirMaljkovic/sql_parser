package SQL.sql_query_parts;

import java.util.ArrayList;

public class From {
    private String table;
    private String database;
    private String alias;

    public From(String database, String table, String alias) {
        this.database = database;
        this.table = table;
        this.alias = alias;
    }

    public static From parseFrom(ArrayList<String> fromList) {
        String table;
        String database = null;
        String alias = null;
        if (fromList.size() > 1) {
            if (fromList.get(0).contains(".")) {  // has database.table
                String[] databaseTable = fromList.get(0).split("\\.");
                database = databaseTable[0];
                table = databaseTable[1];
            }
            else {
                table = fromList.get(0);
            }

            alias = fromList.get(1);
        }
        else
            table = fromList.get(0);

        From from = new From(database, table, alias);
        return from;
    }

    @Override
    public String toString() {
        if (alias != null) {
            if(database != null)
                return database + "." + table + " " + alias;
            else
                return table + " " + alias;
        }
        else {
            if(database != null) {
                return database + "." + table;
            }
            return table;
        }
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
