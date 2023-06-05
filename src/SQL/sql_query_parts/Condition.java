package SQL.sql_query_parts;

import java.util.ArrayList;

public class Condition {

    private String relation;
    private String operator;
    private String attribute;
    private String table;
    private ArrayList<String> values;
    private Select select;

    public Condition(String relation, String table, String attribute, String operator) {
        this.table = table;
        this.attribute = attribute;
        this.operator = operator;
        values = new ArrayList<>();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public ArrayList<String> getValues() {
        return values;
    }

    public Select getSelect() {
        return select;
    }

    public void setSelect(Select select) {
        this.select = select;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("");

        if(table != null) {
            stringBuilder.append(table).append(".");
        }
        stringBuilder.append(attribute);
        stringBuilder.append(" " + operator + " ");
        stringBuilder.append(values);


        return stringBuilder.toString();
    }
}
