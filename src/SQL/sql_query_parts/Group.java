package SQL.sql_query_parts;

import java.util.ArrayList;

public class Group {
    private String table;
    private String attribute;
    private String sort;

    public Group(String table, String attribute, String sort) {
        this.table = table;
        this.attribute = attribute;
        this.sort = sort;
    }

    public static ArrayList<Group> parseGroup(ArrayList<String> groupList){
        String table = null;
        String attribute;
        String sort;
        ArrayList<Group> groups = new ArrayList<>();

        for (int i = 0; i < groupList.size(); i++) {
            String tableAttr = groupList.get(i);
            sort = groupList.get(i+1);
            if(tableAttr.contains(".")) {
                table = tableAttr.split("\\.")[0];
                attribute = tableAttr.split("\\.")[1];
            }
            else
                attribute = tableAttr;

            groups.add(new Group(table, attribute, sort));
            table = null;
            i++;

        }
        return groups;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if(table!=null)
            builder.append(table).append(".");
        builder.append(attribute).append(" ").append(sort);
        return builder.toString();
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
