package Action;

import DAO.MysqlDataBase;
import Util.Cached;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class OrderDisplayPage {

    private List<Object[]> orders;
    private List<Object[]> orderDetails;

    public void showOrder(){

        MysqlDataBase dataBase = new MysqlDataBase();
        Connection conn = dataBase.getConn();
        QueryRunner runner = new QueryRunner();

        try {
            orders = runner.query(conn,"call showOrder(?)",new ArrayListHandler(), Cached.getCid());

            for (int i = 0; i< orders.size(); i++){
                String dateTime = orders.get(i)[3].toString();
                dateTime = dateTime.substring(0,dateTime.lastIndexOf("."));
                System.out.println("orderID: "+ orders.get(i)[0]+"\tBusiness Name: "+ orders.get(i)[1]+"\tWay of pay: "+ orders.get(i)[2]+"\tDateTime of order: "+ dateTime+"\tCost: "+ orders.get(i)[4]);
                orderDetails = runner.query(conn,"call showOrderDetail(?)",new ArrayListHandler(),orders.get(i)[0]);

                for (int j = 0;j<orderDetails.size();j++)
                    System.out.println("                "+orderDetails.get(j)[0]+"              "+orderDetails.get(j)[1]+"份");
            }
            DbUtils.close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("please input 'q' to back...");

        Scanner scanner = new Scanner(System.in);

        while (true){
            if (scanner.next().equals("q"))
                break;
        }
        //                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }


}
