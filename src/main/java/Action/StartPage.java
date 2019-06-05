package Action;

import Entity.Customer;
import Util.Cached;

import java.util.Scanner;

public class StartPage {

    public void Menu(){
        System.out.println("        1. Login");
        System.out.println("        2. Register");
    }

    public void handleMenu(Customer customer){

        Scanner scanner = new Scanner(System.in);
        String choice;
        boolean flag = true;
        LoginPage loginPage;
        RegisterPage registerPage;

        while (flag){

            Menu();
            System.out.print("please input: ");
            choice = scanner.next();

            switch (choice){

                case "1":
                    loginPage = new LoginPage();
                    System.out.print("please input your account:");
                    loginPage.setCid(scanner.nextInt());
                    System.out.print("please input your password:");
                    loginPage.setPwd(scanner.next());

                    if (loginPage.checkId()){
                        customer.setCid(loginPage.getCid());
                        Cached.setCid(customer.getCid());
                        flag = false;
                        System.out.println(loginPage.getCid()+" login successfully!");
                        //        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    }
                    else
                        System.out.println("Worry account or password!");
                    break;
                case "2":
                    System.out.print("please input your name:");
                    customer.setCname(scanner.next());
                    System.out.print("please input your telephone:");
                    customer.setTelephone(scanner.next());
                    System.out.print("please input your password:");
                    customer.setPwd(scanner.next());

                    customer.addCustomer();
                    registerPage = new RegisterPage();
                    customer.setVip(false);

                    registerPage.addLoginData(customer.getCid(),customer.getPwd());

                    System.out.println("Sign In successfully!");
                    System.out.println("your account is "+customer.getCid());
                    flag = false;
//                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    break;
            }
        }
    }
}
