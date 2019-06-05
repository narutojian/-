package Action;

import Entity.Business;
import Entity.Customer;

import java.util.Scanner;

public class MenuPage {

    private Scanner scanner;

    public void Menu(){
        System.out.println("        1. Manage personal information");
        System.out.println("        2. Order food");
        System.out.println("        3. View orders");
        System.out.println("        4. exit");
    }

    public void handleMenu(Customer customer){

        scanner = new Scanner(System.in);
        String choice;
        boolean flag = true;
        CustomerManagePage customerManagePage;
        Business business = new Business();
        BusinessPage businessPage;

        while (flag){
            Menu();
            System.out.print("please input: ");
            choice = scanner.next();
            switch (choice){
                case "1":
                    //                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    customerManagePage = new CustomerManagePage();
                    customerManagePage.handleMenu(customer);

                    break;
                case "2":
                    businessPage = new BusinessPage();
                    businessPage.setBName();
                    businessPage.handleMenu(business);
                    break;
                case "3":
                    OrderDisplayPage orderDisplayPage = new OrderDisplayPage();
                    orderDisplayPage.showOrder();
                    break;
                case "4":
                    flag = false;
                    break;
            }
        }

        scanner.close();
    }
}
