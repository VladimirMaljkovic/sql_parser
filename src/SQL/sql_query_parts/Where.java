package SQL.sql_query_parts;

import SQL.SQLQuery;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Where {
    private ArrayList<Condition> conditions;

    public Where () {
        conditions = new ArrayList<>();
    }

    public Where parseWhere(ArrayList<String> whereList) {

        String current = null;
        String tableAttr = null;
        String table = null;
        String attribute = null;
        String operator = null;
        String valuesString = null;
        ArrayList<String> values = new ArrayList<>();

        for (int i = 0; i < whereList.size(); i++) {
            Condition condition;
            current = whereList.get(i);
            if(current.contains("<=")) {
                tableAttr = current.substring(0, current.indexOf('<'));
                operator = "<=";
                valuesString = current.substring(current.indexOf('=')+1);
            }
            else if (current.contains(">=")) {
                tableAttr = current.substring(0, current.indexOf('>'));
                operator = ">=";
                valuesString = current.substring(current.indexOf('=')+1);
            }
            else if (current.contains("=")) {
                tableAttr = current.substring(0, current.indexOf('='));
                operator = "=";
                valuesString = current.substring(current.indexOf('=')+1);
            }
            else if (current.contains("<")) {
                tableAttr = current.substring(0, current.indexOf('<'));
                operator = "<";
                valuesString = current.substring(current.indexOf('<')+1);
            }
            else if (current.contains(">")) {
                tableAttr = current.substring(0, current.indexOf('>'));
                operator = ">";
                valuesString = current.substring(current.indexOf('>')+1);
            }
            else //its a 'like' or 'in' statement
            {
                tableAttr = current;
                if (whereList.get(i+1).equals("like")) {
                    operator = whereList.get(i+1);
                    valuesString = whereList.get(i+2);
                    i+=3;
                }
            }

            if(tableAttr.contains(".")) {
                String[] parts = tableAttr.split("\\.");
                table = parts[0];
                attribute = parts[1];
            }
            else
                attribute = tableAttr;

            condition = new Condition(null, table, attribute, operator);
            condition.getValues().add(valuesString);  //todo fix for multiple values
            conditions.add(condition);
            System.out.println(condition);
            current = null;
            tableAttr = null;
            table = null;
            attribute = null;
            operator = null;
            valuesString = null;

        }

        return null;
    }

    public ArrayList<Condition> getConditions() {
        return conditions;
    }
}
