package SQL.sql_query_parts;

import SQL.SQLParser.SqlParser;
import SQL.SQLQuery;

import java.util.ArrayList;


public class Where {
    private ArrayList<Condition> conditions;

    public Where () {
        conditions = new ArrayList<>();
    }

    public void parseWhere(ArrayList<String> whereList) {

        String current;
        String tableAttr = null;
        String table = null;
        String relation = null;
        String attribute;
        String operator = null;
        ArrayList<String> values = new ArrayList<>();
        String sqlQuery;
        boolean select = false;

        for (int i = 0; i < whereList.size(); i++) {

            current = whereList.get(i);


            StringBuilder builder = new StringBuilder();
            if(!current.equals("or") && !current.equals("and")) {
                String[] parts = current.split(" ");
                tableAttr = parts[0];
                operator = parts[1];

                if(parts[2].equals("(select")){
                    select = true;
                }
                for(int j = 2; j < parts.length; j++){

                    if(select){
                        builder.append(parts[j]).append(" ");
                    }
                    else {
                        values.add(parts[j].replaceAll("[, ()]", ""));
                    }
                }
            }

            sqlQuery = builder.toString().strip();


            if(i+1 < whereList.size()) {
                relation = whereList.get(i + 1);
                i++;
            }
            if(tableAttr.contains(".")) {
                table = tableAttr.split("\\.")[0];
                attribute = tableAttr.split("\\.")[1];
            }
            else
                attribute = tableAttr;
            Condition condition = new Condition(relation, table, attribute, operator);

            if(sqlQuery.length() > 1) {
                sqlQuery = sqlQuery.substring(1, sqlQuery.length() - 1);  //ovo je string
                SqlParser sqlParser = new SqlParser();
                sqlParser.parseSql(sqlQuery);
                SQLQuery sql = sqlParser.createSQLquery();
                condition.setSqlQuery(sql);
            }

            condition.getValues().addAll(values);
            conditions.add(condition);



            tableAttr = null;
            table = null;
            relation = null;
            attribute = null;
            operator = null;
            values = new ArrayList<>();
            sqlQuery = null;
            select = false;

        }
    }

    public ArrayList<Condition> getConditions() {
        return conditions;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("WHERE");
        for (Condition c: conditions) {
            builder.append(" " + c );
        }
        return builder.toString();
    }
}
