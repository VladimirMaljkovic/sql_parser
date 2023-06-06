package SQL.sql_query_parts;

import SQL.SQLQuery;

import java.util.ArrayList;

public class Condition {

    private String relation;
    private String operator;
    private String attribute;
    private String table;
    private ArrayList<String> values;
    private SQLQuery sqlQuery;

    public Condition(String relation, String table, String attribute, String operator) {
        this.relation = relation;
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

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public SQLQuery getSqlQuery() {
        return sqlQuery;
    }

    public void setSqlQuery(SQLQuery sqlQuery) {
        this.sqlQuery = sqlQuery;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("");

        if(table != null) {
            stringBuilder.append(table).append(".");
        }
        stringBuilder.append(attribute);
        stringBuilder.append(" " + operator + " ");
        if(!values.isEmpty())
            stringBuilder.append(values);
        else {
            stringBuilder.append(sqlQuery);
        }
        if(relation != null)
            stringBuilder.append(" " + relation);


        return stringBuilder.toString();
    }
}
