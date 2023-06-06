package SQL.sql_query_parts;

import java.util.ArrayList;

public class Full {
    private String table1;
    private String table2;
    private String attribute1;
    private String attribute2;

    public Full(String table1, String attribute1, String table2, String attribute2) {
        this.table1 = table1;
        this.table2 = table2;
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
    }

    public static ArrayList<Full> parseFull(ArrayList<String> fullList){
        ArrayList<Full> fulls = new ArrayList<>();
        String table1;
        String table2;
        String attribute1;
        String attribute2;
        for (int i = 0; i < fullList.size(); i++) {
            String[] parts1 = fullList.get(i+2).split("\\.");
            String[] parts2 = fullList.get(i+4).split("\\.");
            table1 = parts1[0];
            attribute1 = parts1[1];
            table2 = parts2[0];
            attribute2 = parts2[1];

            fulls.add(new Full(table1, attribute1, table2, attribute2));
            i+=4;
        }
        return fulls;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("FULL OUTER JOIN ");
        builder.append(table2);
        builder.append(" ON ");
        builder.append(table1).append(".");
        builder.append(attribute1).append(" = ");
        builder.append(table2).append(".");
        builder.append(attribute2);

        return builder.toString();
    }

    public String getTable1() {
        return table1;
    }

    public void setTable1(String table1) {
        this.table1 = table1;
    }

    public String getTable2() {
        return table2;
    }

    public void setTable2(String table2) {
        this.table2 = table2;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }
}
