package SQL.sql_query_parts;

import java.util.ArrayList;

public class Cross {

    private String database;
    private String table;

    public Cross(String database, String table) {
        this.database = database;
        this.table = table;
    }

    public static ArrayList<Cross> parseCross(ArrayList<String> crossList) {
        ArrayList<Cross> crosses = new ArrayList<>();

        for (String s : crossList) {
            crosses.add(new Cross(s.split("\\.")[0], s.split("\\.")[1]));
        }
        return crosses;
    }

    @Override
    public String toString() {
        return "CROSS JOIN " + database + "." + table;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
}
