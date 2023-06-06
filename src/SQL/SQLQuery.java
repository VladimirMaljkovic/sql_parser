package SQL;

import SQL.sql_query_parts.*;

import java.util.ArrayList;

public class SQLQuery {
    private ArrayList<Select> selects;
    private From from;
    private Where where = null;
    private ArrayList<Cross> crosses;
    private ArrayList<Inner> inners;



    public SQLQuery() {
        selects = new ArrayList<>();
        crosses = new ArrayList<>();
        inners = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("SELECT ");

        for (int i = 0; i < selects.size(); i++) {
            stringBuilder.append(selects.get(i));
            if(i != selects.size()-1)
                stringBuilder.append(", ");
        }

        stringBuilder.append(" FROM ").append(from);

        if(!crosses.isEmpty()) {
            for(Cross c: crosses) {
                stringBuilder.append(" ").append(c);
            }
        }

//        if(inner != null && inner.getConnector()!=null){
//            stringBuilder.append(" ").append(inner);
//        }

        if(!where.getConditions().isEmpty()) {
            stringBuilder.append(" ").append(where);
        }

        return stringBuilder.toString();
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

    public ArrayList<Cross> getCrosses() {
        return crosses;
    }

    public ArrayList<Inner> getInners() {
        return inners;
    }
}
