package SQL;

import SQL.sql_query_parts.*;

import java.util.ArrayList;

public class SQLQuery {
    private ArrayList<Select> selects;
    private From from;
    private Where where = null;
    private ArrayList<Cross> crosses;
    private ArrayList<Inner> inners;
    private ArrayList<Left> lefts;
    private ArrayList<Right> rights;
    private ArrayList<Full> fulls;
    private ArrayList<Group> groups;
    private ArrayList<Order> orders;



    public SQLQuery() {
        selects = new ArrayList<>();
        crosses = new ArrayList<>();
        inners = new ArrayList<>();
        lefts = new ArrayList<>();
        rights = new ArrayList<>();
        fulls = new ArrayList<>();
        groups = new ArrayList<>();
        orders = new ArrayList<>();
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

        if(!inners.isEmpty()) {
            for(Inner i: inners) {
                stringBuilder.append(" ").append(i);
            }
        }

        if(!lefts.isEmpty()) {
            for(Left l: lefts) {
                stringBuilder.append(" ").append(l);
            }
        }

        if(!rights.isEmpty()) {
            for(Right r: rights) {
                stringBuilder.append(" ").append(r);
            }
        }

        if(!fulls.isEmpty()) {
            for(Full f: fulls) {
                stringBuilder.append(" ").append(f);
            }
        }

        if(!where.getConditions().isEmpty()) {
            stringBuilder.append(" ").append(where);
        }

        if(!orders.isEmpty()) {
            stringBuilder.append(" ORDER BY");
            for(Order o: orders){
                stringBuilder.append(" ").append(o);
            }
        }

        if(!groups.isEmpty()) {
            stringBuilder.append(" GROUP BY");
            for(Group g: groups){
                stringBuilder.append(" ").append(g);
            }
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

    public ArrayList<Left> getLefts() {
        return lefts;
    }

    public ArrayList<Right> getRights() {
        return rights;
    }

    public ArrayList<Full> getFulls() {
        return fulls;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }
}
