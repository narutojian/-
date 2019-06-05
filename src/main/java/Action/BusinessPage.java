package Action;

import DAO.MysqlDataBase;
import Entity.Business;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BusinessPage {

    private String[] bname;

    public void setBName() {
        MysqlDataBase dataBase = new MysqlDataBase();
        Connection conn = dataBase.getConn();
        QueryRunner runner = new QueryRunner();

        try {
            List<Object[]> objects = runner.query(conn,"call showBusiness()",new ArrayListHandler());
            bname = new String[objects.size()];
            for (int i = 0;i<objects.size();i++)
                bname[i] = objects.get(i)[0].toString();
            DbUtils.close(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Menu(){
        int i=1;
        System.out.println("List of all Businesses: ");
        for (;i<=bname.length;i++)
            System.out.println("        "+i+". "+ bname[i-1]);
        System.out.println("        "+i+". back");
    }

    public void handleMenu(Business business){

        Scanner scanner = new Scanner(System.in);
        String choice;

        while (true){
            Menu();
            System.out.print("please input: ");
            choice = scanner.next();

            if (Integer.parseInt(choice) < bname.length+1){
                business.setBname(bname[Integer.parseInt(choice)-1]);
                business.setBid();
                business.setFid();
                FoodPage foodPage = new FoodPage();
                foodPage.init(business);
                foodPage.showFoodInfo();
                FoodOrderPage foodOrderPage = new FoodOrderPage();
                foodOrderPage.handleMenu(foodPage.getFoods());
            }
            else if (Integer.parseInt(choice) == bname.length+1) break;
        }
    }
}
