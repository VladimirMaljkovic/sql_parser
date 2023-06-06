package SQL.sql_query_parts;

import java.util.ArrayList;

public class Left {
    private String connector;
    private String database1;
    private String table1;
    private String attribute1;
    private String database2;
    private String table2;
    private String attribute2;

    public Left(String connector, String database1, String table1, String attribute1, String database2, String table2, String attribute2) {
        this.connector = connector;
        this.database1 = database1;
        this.table1 = table1;
        this.attribute1 = attribute1;
        this.database2 = database2;
        this.table2 = table2;
        this.attribute2 = attribute2;
    }

    public static ArrayList<Left> parseLeft(ArrayList<String> leftList) {
        ArrayList<Left> lefts = new ArrayList<>();
        String connector;
        String database1 = null;
        String table1;
        String attribute1;
        String database2 = null;
        String table2 = null;
        String attribute2 = null;

        for (int i = 0; i < leftList.size(); i++) {
            String current = leftList.get(i);
            if(current.equals("on")) {
                connector = current;
                String[] parts1 = leftList.get(i+1).split("\\.");
                String[] parts2 = leftList.get(i+3).split("\\.");
                if(parts1.length == 3) {
                    database1 = parts1[0];
                    database2 = parts2[0];
                    table1 = parts1[1];
                    table2 = parts2[1];
                    attribute1 = parts1[2];
                    attribute2 = parts2[2];
                }
                else {
                    table1 = parts1[0];
                    table2 = parts2[0];
                    attribute1 = parts1[1];
                    attribute2 = parts2[1];
                }
                i+=3;
            }
            else {
                connector = "using";
                String[] parts = current.substring(6, current.length() - 1).split("\\.");
                if(parts.length == 3) {
                    database1 = parts[0];
                    table1 = parts[1];
                    attribute1 = parts[2];
                }
                else {
                    table1 = parts[0];
                    attribute1 = parts[1];
                }

            }

            lefts.add(new Left(connector, database1, table1, attribute1, database2, table2, attribute2));
        }

        return lefts;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("LEFT JOIN");
        if(connector.equals("on")) {
            builder.append(" ON ");
            if(database1 != null) {
                builder.append(database1).append(".").append(table1).append(".").append(attribute1)
                        .append(" = ").append(database2).append(".").append(table2).append(".").append(attribute2);
            }
            else
                builder.append(table1).append(".").append(attribute1)
                        .append(" = ").append(table2).append(".").append(attribute2);
        }
        else {
            builder.append(" USING(");
            if(database1 != null)
                builder.append(database1).append(".").append(table1).append(".").append(attribute1);
            else
                builder.append(table1).append(".").append(attribute1);
            builder.append(")");
        }

        return builder.toString();
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
