import SQL.SQLQuery;
import SQL.SQLParser.SqlParser;
import SQL.sql_query_parts.Condition;
import SQL.sql_query_parts.From;
import SQL.sql_query_parts.Select;
import SQL.sql_query_parts.Where;

public class Main {

    public static void main(String[] args) {
        SqlParser sqlParser = new SqlParser();
        SQLQuery sqlQuery;

        //ovaj deo ide u handler koji se poziva kad se klikne dugme

        String SQLString = "select employees.name, departments.department_name, COUNT(*) AS total_count, just_an_attribute_without_a_table_specified from employees full outer join departments ON employees.department_id = departments.department_id where employees.salary > 50000 group by employees.name, departments.department_name order by total_count DESC";
        String SQLString2 = "select customers.customer_id, customers.customer_name, count(orders.order_id) as total_orders from database.customers c inner join orders using (customer_id) inner join order_items on orders.order_id = order_items.order_id where orders.order_date >= '2023-01-01' group by customers.customer_id, customers.customer_name having total_orders > 5 order by total_orders desc";
        String SQLString3 = "select customers.customer_id from database.customers c where WHERE column1 IN (SELECT column1 FROM table2 WHERE column2 = 'value')";
        String SQLString4 = "select first_name, last_name, salary from hr.employees where salary = (select max(salary) from hr.employees)";
        String SQLString6 = "select first_name, last_name, salary from hr.employees where name = 'Ana' or salary >= 12 or salary = (select max(salary) from hr.employees) or hr.name in ('a', 'b', 'c', 'd') and city in (select cities from country where name like '%ad' or name like 'be%')";
        String SQLString5 = "select first_name, last_name, salary from hr.employees where users.name = 'ana' or country = 'serbia' and hr.salary >= 1200 or table1.name3 like '%peepee%'";
        String SQLString7 = "select first_name, last_name, salary from hr.employees cross join db.table12 cross join db2.table12345";
        String SQLString8 = "select first_name, last_name, salary from hr.employees inner join on db1.table1.attr1 = db2.table2.attr2 inner join using(jobs.id)";
        String SQLString9 = "select first_name, last_name, salary from hr.employees inner join using(jobs.id)";


        sqlParser.parseSql(SQLString8);

        sqlQuery = sqlParser.createSQLquery();


    }
}
