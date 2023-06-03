package SQL.sql_query_parts;

import java.util.ArrayList;

public class Select {

    // vector of table, attribute, alias, function
    private String table;
    private String attribute;
    private String alias;
    private String function;

    public Select(String table, String attribute, String alias, String function) {
        this.table = table;
        this.attribute = attribute;
        this.alias = alias;
        this.function = function;
    }

    public static ArrayList<Select> parseSelect(ArrayList<String> selectList){
        ArrayList<Select> selects = new ArrayList<>();
        String func = null;
        String table = null;
        String attribute;
        String alias = null;
        String current;
        for (int i = 0; i < selectList.size(); i++){
            current = selectList.get(i);
            if (current.contains("(")) { //is a function
                if (current.contains(".")) { //func + table
                    String[] parts = current.split("[.()]");
                    func = parts[0];
                    table = parts[1];
                    attribute = parts[2];

                }
                else { // fun + no table
                    String[] parts = current.split("[()]");
                    func = parts[0];
                    attribute = parts[1];
                }
            }
            else { //not a function
                if (current.contains(".")) { //no func + table
                    String[] parts = current.split("[.]");
                    table = parts[0];
                    attribute = parts[1];

                }
                else { // no fun + no table
                    attribute = current;
                }
            }
            if(i != selectList.size() - 1 && selectList.get(i+1).equals("as")) { //is it an alias
                alias = selectList.get(i+2);
                i = i+2;
            }
            selects.add(new Select(table, attribute, alias, func));
            table = null;
            alias = null;
            func = null;
        }

        return selects;
    }

    public String getTable() {
        return table;
    }

    public String getAttribute() {
        return attribute;
    }

    public String getAlias() {
        return alias;
    }

    public String getFunction() {
        return function;
    }

    @Override
    public String toString() {
        String finalString;
        if (function != null) {
            if(table != null)
                finalString = function + "(" + table + "." + attribute + ")";
            else
                finalString =  function + "(" + attribute + ")";
        }
        else { //function is null
            if (table != null)
                finalString = table + "." + attribute;
            else
                finalString = attribute;
        }
        if(alias!=null) {
            finalString = finalString + " as " + alias;
        }
        return finalString;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}
