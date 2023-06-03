package SQL.SQLParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SqlParser {

    private ArrayList<String> select;
    private ArrayList<String> from;
    private ArrayList<String> where;
    private ArrayList<String> inner;
    private ArrayList<String> full;
    private ArrayList<String> right;
    private ArrayList<String> left;
    private ArrayList<String> order;
    private ArrayList<String> group;
    private ArrayList<String> having;

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
        having = new ArrayList<>();
    }

    public void parseSql(String SQLString) {
        String[] words = SQLString.split(" ");
        String currentKeyword = "none";
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
                currentKeyword = word;
            } else {
                switch (currentKeyword) {
                    case "select" -> select.add(word.replaceAll(",", ""));
                    case "from" -> from.add(word);
                    case "where" -> where.add(word.replaceAll(",", ""));
                    case "inner" -> inner.add(word.replaceAll(",", ""));
                    case "left" -> left.add(word.replaceAll(",", ""));
                    case "right" -> right.add(word.replaceAll(",", ""));
                    case "full" -> full.add(word.replaceAll(",", ""));
                    case "order" -> order.add(word.replaceAll(",", ""));
                    case "group" -> group.add(word.replaceAll(",", ""));
                    case "having" -> having.add(word.replaceAll(",", ""));
                }
            }
        }

//        System.out.println("select: " + select);
        System.out.println("from: " + from);
//        System.out.println("where: " + where);
//        System.out.println("inner: " + inner);
//        System.out.println("left: " + left);
//        System.out.println("right: " + right);
//        System.out.println("full: " + full);
//        System.out.println("order: " + order);
//        System.out.println("group: " + group);
//        System.out.println("having: " + having);
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

    public ArrayList<String> getHaving() {
        return having;
    }
}
