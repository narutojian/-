import Action.LoginPage;
import Action.SignInPage;
import Entity.Customer;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void loginSignIn(){
        System.out.println("        1. Login");
        System.out.println("        2. Sign In");
        System.out.print("请输入:");
    }

    public static void foodDisplay(){

        System.out.println("        1. Display food information by Business");
        System.out.println("        2. Display food information by category of food");
        System.out.println("        3. Display food information by supply time of food");
        System.out.println("        4. exit");
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);
        String choice;
        boolean flag;
        LoginPage loginPage = null;
        SignInPage signInPage = null;
        Customer customer = null;

        System.out.println("Welcome to Canteen Management System!");
        flag = true;
        while (flag){
            loginSignIn();
            choice = scanner.next();

            switch (choice){
                case "1":
                    loginPage = new LoginPage();
                    System.out.print("please input your account:");
                    loginPage.setCid(scanner.nextInt());
                    System.out.print("please input your password:");
                    loginPage.setPwd(scanner.next());
                    if (loginPage.checkId()){
                        flag = false;
                        System.out.println(loginPage.getCid()+" login successfully!");
                    }
                    else
                        System.out.println("Worry account or password!");
                    break;
                case "2":
                    customer = new Customer();
                    System.out.print("please input your name:");
                    customer.setCname(scanner.next());
                    System.out.print("please input your telephone:");
                    customer.setTelephone(scanner.next());
                    System.out.print("please input your password:");
                    customer.setPwd(scanner.next());

                    customer.addCustomer();
                    signInPage = new SignInPage();
                    customer.setVip(false);

                    signInPage.addLoginData(customer.getCid(),customer.getPwd());

                    System.out.println("Sign In successfully!");
                    System.out.println("your account is "+customer.getCid());

//                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    break;
            }
        }

//        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

        foodDisplay();


    }
}
