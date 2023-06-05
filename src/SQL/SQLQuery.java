package SQL;

import SQL.sql_query_parts.From;
import SQL.sql_query_parts.Select;
import SQL.sql_query_parts.Where;

import java.util.ArrayList;

public class SQLQuery {
    private ArrayList<Select> selects;
    private From from;
    private Where where;


    public SQLQuery() {
        selects = new ArrayList<>();
    }

    public ArrayList<Select> getSelects() {
        return selects;
    }

    public void setFrom(From from)
    {
        this.from = from;
    }

    public From getFrom() {
        return from;
    }

    public Where getWhere() {
        return where;
    }

    public void setWhere(Where where) {
        this.where = where;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("SELECT ");

        for (int i = 0; i < selects.size(); i++) {
            stringBuilder.append(selects.get(i));
            if(i != selects.size()-1)
                stringBuilder.append(", ");
        }

        stringBuilder.append(" FROM " + from + " ");

        String finalString = stringBuilder.toString();
        return finalString;
    }
}
