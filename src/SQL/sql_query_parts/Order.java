package SQL.sql_query_parts;

import java.util.ArrayList;

public class Order {
    private String table;
    private String attribute;
    private String sort;

    public Order(String table, String attribute, String sort) {
        this.table = table;
        this.attribute = attribute;
        this.sort = sort;
    }

    public static ArrayList<Order> parseOrder(ArrayList<String> orderList){
        String table = null;
        String attribute;
        String sort;
        ArrayList<Order> orders = new ArrayList<>();

        for (int i = 0; i < orderList.size(); i++) {
            String tableAttr = orderList.get(i);
            sort = orderList.get(i+1);
            if(tableAttr.contains(".")) {
                table = tableAttr.split("\\.")[0];
                attribute = tableAttr.split("\\.")[1];
            }
            else
                attribute = tableAttr;

            orders.add(new Order(table, attribute, sort));
            table = null;
            i++;
        }
        return orders;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("");
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
