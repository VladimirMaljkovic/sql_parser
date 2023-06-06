package SQL.SQLParser;

import SQL.SQLQuery;
import SQL.sql_query_parts.Cross;
import SQL.sql_query_parts.From;
import SQL.sql_query_parts.Select;
import SQL.sql_query_parts.Where;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlParser {

    private final ArrayList<String> select;
    private final ArrayList<String> from;
    private final ArrayList<String> where;
    private final ArrayList<String> inner;
    private final ArrayList<String> full;
    private final ArrayList<String> right;
    private final ArrayList<String> cross;
    private final ArrayList<String> left;
    private final ArrayList<String> order;
    private final ArrayList<String> group;

    private final List<String> keyWords = Arrays.asList(
            "select",
            "from",
            "where",
            "inner",
            "join",
            "outer",
            "left",
            "right",
            "full",
            "group",
            "order",
            "cross",
            "having",
            "by");

    public SqlParser() {
        select = new ArrayList<>();
        from = new ArrayList<>();
        where = new ArrayList<>();
        order = new ArrayList<>();
        group = new ArrayList<>();
        inner = new ArrayList<>();
        full = new ArrayList<>();
        left = new ArrayList<>();
        right = new ArrayList<>();
        cross = new ArrayList<>();
    }

    public ArrayList<String> splitWhere(String input) {
        ArrayList<String> parts = new ArrayList<>();

        StringBuilder builder = new StringBuilder();
        int parenthesesCount = 0;

        Pattern pattern = Pattern.compile("\\b(OR|AND)\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);

        int lastIndex = 0;
        while (matcher.find()) {
            String match = matcher.group();

            if (parenthesesCount == 0 && (match.equalsIgnoreCase("OR") || match.equalsIgnoreCase("AND"))) {
                parts.add(builder.toString().trim());
                builder = new StringBuilder();
            }

            builder.append(input, lastIndex, matcher.end());
            lastIndex = matcher.end();

            if (match.equalsIgnoreCase("(")) {
                parenthesesCount++;
            } else if (match.equalsIgnoreCase(")")) {
                parenthesesCount--;
            }
        }

        if (lastIndex < input.length()) {
            builder.append(input, lastIndex, input.length());
        }

        parts.add(builder.toString().trim());

        return parts;
    }

    public void parseSql(String SQLString) {
        System.out.println("input -> " + SQLString);
        String[] words = SQLString.split(" ");
        String currentKeyword = "none";

        // PARSING WHERE
        Pattern pattern = Pattern.compile("where (.*?)\\s*(?:order by|group by|$)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(SQLString);
        if(matcher.find()) {
            String whereGroup = matcher.group(1);
            ArrayList<String> whereBad = new ArrayList<>(splitWhere(whereGroup));
            whereBad.remove(0);

            for (String condition: whereBad) {
                String[] parts = condition.split(" ");
                StringBuilder builder = new StringBuilder(parts[0]);
                if(parts[parts.length-1].equals("or") || parts[parts.length-1].equals("OR")) {
                    for (int i = 1; i < parts.length-1; i++) {
                        builder.append(" ").append(parts[i]);
                    }
                    where.add(builder.toString());
                    where.add("or");
                }
                else if(parts[parts.length-1].equals("and") || parts[parts.length-1].equals("AND")) {
                    for (int i = 1; i < parts.length-1; i++) {
                        builder.append(" ").append(parts[i]);
                    }
                    where.add(builder.toString());
                    where.add("and");
                }
                else
                    where.add(condition);
            }
        }



        for (String word : words) {
            if ((currentKeyword.equals("order") || currentKeyword.equals("group")) && word.equals("by")) {
                continue;
            }
            if (keyWords.contains(word)) {
                // when there is inner join
                if(currentKeyword.equals("inner") && word.equals("join")) {
                    currentKeyword = "inner";
                    continue;
                }
                // when there is cross join
                if(currentKeyword.equals("cross") && word.equals("join")) {
                    currentKeyword = "cross";
                    continue;
                }
                // when there is only join (without inner)
                if(!currentKeyword.equals("left") && !currentKeyword.equals("right") &&!currentKeyword.equals("full") && word.equals("join")) {
                    currentKeyword = "inner";
                    continue;
                }
                // when there is left (outer) join
                if(currentKeyword.equals("left") && (word.equals("join") || word.equals("outer"))) {
                    currentKeyword = "left";
                    continue;
                }
                // when there is right (outer) join
                if(currentKeyword.equals("right") && (word.equals("join") || word.equals("outer"))) {
                    currentKeyword = "right";
                    continue;
                }
                // when there is full (outer) join
                if(currentKeyword.equals("full") && (word.equals("join") || word.equals("outer"))) {
                    currentKeyword = "full";
                    continue;
                }
                if(currentKeyword.equals("where") && (word.equals("select") || word.equals("from")))
                    continue;
                currentKeyword = word;
            } else {
                switch (currentKeyword) {
                    case "select" -> select.add(word.replaceAll(",", ""));
                    case "from" -> from.add(word);
                    case "where" -> {}
                    case "inner" -> inner.add(word.replaceAll(",", ""));
                    case "left" -> left.add(word.replaceAll(",", ""));
                    case "right" -> right.add(word.replaceAll(",", ""));
                    case "full" -> full.add(word.replaceAll(",", ""));
                    case "order" -> order.add(word.replaceAll(",", ""));
                    case "group" -> group.add(word.replaceAll(",", ""));
                    case "cross" -> cross.add(word.replaceAll(",", ""));
                }
            }
        }

//        System.out.println("select: " + select);
//        System.out.println("from: " + from);
//        System.out.println("where: " + where);
        System.out.println("inner: " + inner);
        System.out.println("left: " + left);
        System.out.println("right: " + right);
        System.out.println("full: " + full);
        System.out.println("cross: " + cross);
//        System.out.println("order: " + order);
//        System.out.println("group: " + group);
    }


    public SQLQuery createSQLquery() {
        SQLQuery sqlQuery = new SQLQuery();
        //SELECT
        sqlQuery.getSelects().addAll(Select.parseSelect(getSelect()));

        //FROM
        sqlQuery.setFrom(From.parseFrom(getFrom()));

        //WHERE
        Where where = new Where();
        where.parseWhere(getWhere());
        sqlQuery.setWhere(where);

        //CROSS
        sqlQuery.getCrosses().addAll(Cross.parseCross(getCross()));



        System.out.println("Output -> " + sqlQuery);
        return sqlQuery;
    }

    public ArrayList<String> getSelect() {
        return select;
    }

    public ArrayList<String> getFrom() {
        return from;
    }

    public ArrayList<String> getWhere() {
        return where;
    }

    public ArrayList<String> getInner() {
        return inner;
    }

    public ArrayList<String> getFull() {
        return full;
    }

    public ArrayList<String> getRight() {
        return right;
    }

    public ArrayList<String> getLeft() {
        return left;
    }

    public ArrayList<String> getOrder() {
        return order;
    }

    public ArrayList<String> getGroup() {
        return group;
    }

    public ArrayList<String> getCross() {
        return cross;
    }
}
