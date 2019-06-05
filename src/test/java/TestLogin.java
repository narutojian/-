import Action.LoginPage;
import DAO.MysqlDataBase;
import Entity.Customer;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import java.sql.Connection;
import java.util.Random;

public class TestLogin {

    @Test
    public void Login(){

//        Scanner cin = new Scanner(System.in);
//        int cid;
//        String pwd;
//        System.out.print("id:");
//        cid = cin.nextInt();
//        System.out.print("pwd:");
//        pwd = cin.next();

        LoginPage loginPage = new LoginPage(1,"123456");
        System.out.println(loginPage.checkId());
    }

    @Test
    public void check(){
//        Customer customer = new Customer();
//        customer.setCid(1);
//        customer.setPwd("123456");
//
//        customer.Display();
//        System.out.println(String.format("%-15s%-15d%-15s%-15.2f%-15d%-15s","",1,"rice",2.5,10,"7-9,11-13,17-20"));
    }
}
