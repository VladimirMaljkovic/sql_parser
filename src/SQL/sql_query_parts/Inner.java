package SQL.sql_query_parts;

import java.util.ArrayList;

public class Inner {
    private String connector;
    private String database1;
    private String table1;
    private String attribute1;
    private String database2;
    private String table2;
    private String attribute2;

    public Inner(String connector, String database1, String table1, String attribute1, String database2, String table2, String attribute2) {
        this.connector = connector;
        this.database1 = database1;
        this.table1 = table1;
        this.attribute1 = attribute1;
        this.database2 = database2;
        this.table2 = table2;
        this.attribute2 = attribute2;
    }

    public static ArrayList<Inner> parseInner(ArrayList<String> innerList) {

        for (String current: innerList) {

        }

        return null;
    }


    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
    }

    public String getDatabase1() {
        return database1;
    }

    public void setDatabase1(String database1) {
        this.database1 = database1;
    }

    public String getTable1() {
        return table1;
    }

    public void setTable1(String table1) {
        this.table1 = table1;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getDatabase2() {
        return database2;
    }

    public void setDatabase2(String database2) {
        this.database2 = database2;
    }

    public String getTable2() {
        return table2;
    }

    public void setTable2(String table2) {
        this.table2 = table2;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }
}
