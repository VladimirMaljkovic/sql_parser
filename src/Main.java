import SQL.SQLQuery;
import SQL.SQLParser.SqlParser;
import SQL.sql_query_parts.From;
import SQL.sql_query_parts.Select;

public class Main {

    public static void main(String[] args) {
        SqlParser softParsed = new SqlParser();
        SQLQuery sqlQuery = new SQLQuery();

        //ovaj deo ide u handler koji se poziva kad se klikne dugme

        String SQLString = "select employees.name, departments.department_name, COUNT(*) AS total_count, just_an_attribute_without_a_table_specified from employees full outer join departments ON employees.department_id = departments.department_id where employees.salary > 50000 group by employees.name, departments.department_name order by total_count DESC";
        String SQLString2 = "select customers.customer_id, customers.customer_name, count(orders.order_id) as total_orders from database.customers c inner join orders using (customer_id) inner join order_items on orders.order_id = order_items.order_id where orders.order_date >= '2023-01-01' group by customers.customer_id, customers.customer_name having total_orders > 5 order by total_orders desc";

        softParsed.parseSql(SQLString2);
        sqlQuery.getSelects().addAll(Select.parseSelect(softParsed.getSelect()));
        sqlQuery.setFrom(From.parseFrom(softParsed.getFrom()));


        System.out.println(sqlQuery);

    }
}
